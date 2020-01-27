package com.steps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BaseURLStep implements Step {
    private CategoryStep categoryStep;

    public static boolean isResponsible(String url) throws IOException {
        Elements el = Jsoup.connect(url).get().select("section#searchmain-container");
        return !el.isEmpty();
    }

    public void parse(String url) throws IOException{
        Document document = Jsoup.connect(url).get();

        Elements linksOnCategories = document.select("section#searchmain-container")
                .select("div.subcategories-list.clr")
                .select("li.fleft")
                .select("a[href]");

        for (Element el : linksOnCategories)
            getCategoryStep().parse(el.attr("href"));
    }

    private CategoryStep getCategoryStep(){
        if (categoryStep == null)
            categoryStep = new CategoryStep();
        return categoryStep;
    }
}