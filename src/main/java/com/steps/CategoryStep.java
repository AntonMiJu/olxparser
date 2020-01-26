package com.steps;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CategoryStep implements Step {
    private Document document;

    public CategoryStep(Document document) {
        this.document = document;
    }

    public static boolean isResponsible(Document document) {
        Elements el = document.select("div.pager.rel.clr");
        return !el.isEmpty();
    }

    public void parse() {
        Elements links = document.select("div.offer-wrapper")
                .select("div.space.rel")
                .select("a[href]");
    }
}