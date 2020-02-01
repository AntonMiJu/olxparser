package com.steps;

import com.Router;
import com.utils.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

    @Override
    public void run() {
        parse();
    }

    public void parse() {
        Elements linksOnCategories = document.select("section#searchmain-container")
                .select("div.subcategories-list.clr")
                .select("li.fleft")
                .select("a[href]");

        for (Element el : linksOnCategories)
            new Router(Utils.defaultStep(el.attr("href"))).route();
    }
}