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
    private String cookie;

    public AdStep(String cookie) {
        this.cookie = cookie;
    }

    public static boolean isResponsible(Document document) {
        Elements el = document.select("body.detailpage.t-new_sidebar");
        return !el.isEmpty();
    }

    public void parse(Document document) throws IOException {
        System.out.println(document.baseUri());

        if (!document.select("div#offer_removed_by_user").isEmpty() || !document.select("div#offer_outdated").isEmpty())
            return;

        Element addressElement = document.select("address")
                .first();

        if (addressElement.html().contains("</a>"))
            addressElement = addressElement.select("p").first();

        String address = addressElement.ownText();

        Element nameElement = document.select("div.offer-user__details")
                .select("h4")
                .first();

        if (nameElement.html().contains("</a>"))
            nameElement = nameElement.select("a").first();

        String name = nameElement.text();

        String dateRegistered = document.select("div.offer-user__details")
                .select("span.user-since")
                .first()
                .text();

        //TODO create class - temporary container and save account
        Account account = new Account()
                .setAddress(address)
                .setDateRegistered(dateRegistered)
                .setName(name);

        String htmlPhoneToken = document.select("section#body-container")
                .select("script")
                .first()
                .html();

        String phoneToken = StringUtils.substringBetween(htmlPhoneToken, "'");

        String adId = StringUtils.substringBetween(document.baseUri(), "-ID", ".html");
        String phoneUrl = String.format("https://www.olx.ua/uk/ajax/misc/contact/phone/%s/?pt=%s", adId, phoneToken);

        new Router(Utils.phoneStep(phoneUrl, document.baseUri(), cookie)).route();
    }
}
