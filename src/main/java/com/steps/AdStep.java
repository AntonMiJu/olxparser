package com.steps;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AdStep implements Step {
    private Document document;

    public AdStep(Document document) {
        this.document = document;
    }

    public static boolean isResponsible(Document document) {
        Elements el = document.select("div.detailpage.t-new_sidebar");
        return !el.isEmpty();
    }

    public void parse() {
        String location = document.select("address")
                .select("p")
                .first()
                .text();

        String name = document.select("div.offer-user__details")
                .select("h4")
                .select("a")
                .first()
                .text();

        String dateRegistered = document.select("div.offer-user__details")
                .select("span.user-since")
                .first()
                .text();

        String htmlPhoneToken = document.select("section#body-container")
                .select("script")
                .first()
                .html();

        String phoneToken = htmlPhoneToken.substring(htmlPhoneToken.indexOf("'")+1, htmlPhoneToken.lastIndexOf("'"));
    }
}
