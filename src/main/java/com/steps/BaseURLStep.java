package com.steps;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class BaseURLStep implements Step {
    private Document document;

    public BaseURLStep(Document document) {
        this.document = document;
    }

    public static boolean isResponsible(Document document) {
        Elements el = document.select("section#searchmain-container");
        return !el.isEmpty();
    }

    public void parse() {
        Elements linksOnCategories = document.select("section#searchmain-container")
                .select("div.subcategories-list.clr")
                .select("li.fleft")
                .select("a[href]");
    }
}