package com.steps;

import com.Account;
import com.Router;
import com.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AdStep implements Step {
    public static final String PHONE_TEMPLATE = "https://www.olx.ua/uk/ajax/misc/contact/phone/%s/?pt=%s";

    private String cookie;
    private Document document;

    public AdStep(String cookie, Document document) {
        this.cookie = cookie;
        this.document = document;
    }

    public static boolean isResponsible(Document document) {
        Elements el = document.select("body.detailpage.t-new_sidebar");
        return !el.isEmpty();
    }

    @Override
    public void run() {

    }

    public void parse() throws IOException {
        System.out.println(document.baseUri());

        if (isNotRelevantAd(document))
            return;

        String address = extractAddress(document);
        String name = extractName(document);
        String dateRegistered = extractDate(document);

        Account account = new Account(name, address, dateRegistered);

        Utils utils = new Utils().setAccount(account);

        String adId = StringUtils.substringBetween(document.baseUri(), "-ID", ".html");
        String phoneUrl = String.format(PHONE_TEMPLATE, adId, extractPhoneToken(document));

        new Router(Utils.phoneStep(phoneUrl, document.baseUri(), cookie), utils).route();
    }

    private boolean isNotRelevantAd(Document document) {
        return !document.select("div#offer_removed_by_user").isEmpty() || !document.select("div#offer_outdated").isEmpty();
    }

    private String extractPhoneToken(Document document) {
        String htmlPhoneToken = document.select("section#body-container")
                .select("script")
                .first()
                .html();

        return StringUtils.substringBetween(htmlPhoneToken, "'");
    }

    private String extractDate(Document document) {
        return document.select("div.offer-user__details")
                    .select("span.user-since")
                    .first()
                    .text();
    }

    private String extractName(Document document) {
        Element nameElement = document.select("div.offer-user__details")
                .select("h4")
                .first();

        if (nameElement.html().contains("</a>"))
            nameElement = nameElement.select("a")
                    .first();

        return nameElement.text();
    }

    private String extractAddress(Document document) {
        Element addressElement = document.select("address")
                .first();

        if (addressElement.html().contains("</a>"))
            addressElement = addressElement.select("p")
                    .first();

        return addressElement.ownText();
    }
}
