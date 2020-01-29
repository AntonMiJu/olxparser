package com.steps;

import com.Router;
import com.utils.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BaseURLStep implements Step {
    public static boolean isResponsible(Document document) {
        Elements el = document.select("section#searchmain-container");
        return !el.isEmpty();
    }

    public void parse(Document document) throws IOException {
        Elements linksOnCategories = document.select("section#searchmain-container")
                .select("div.subcategories-list.clr")
                .select("li.fleft")
                .select("a[href]");

        for (Element el : linksOnCategories)
            new Router(Utils.defaultStep(el.attr("href"))).route();
    }
}