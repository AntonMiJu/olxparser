package com.steps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CategoryStep implements Step {
    private static final String suffixForLink = "?page=";

    private AdStep adStep;

    public static boolean isResponsible(String url) throws IOException {
        Elements el = Jsoup.connect(url).get().select("div.pager.rel.clr");
        return !el.isEmpty();
    }

    public void parse(String url) throws IOException {
        int lastPageIndex = QuantityOfPagesGetter.getQuantityOfPages(url);

        for (int i = 1; i <= lastPageIndex; i++) {
            String pageUrl = url + suffixForLink + i;
            System.out.println(pageUrl);
            Document document = Jsoup.connect(pageUrl).get();

            Elements links = document.select("div.offer-wrapper")
                    .select("div.space.rel")
                    .select("a[href]");

            for (Element el : links) {
                getAdStep().parse(el.attr("href"));
            }
        }
    }

    private AdStep getAdStep() {
        if (adStep == null)
            adStep = new AdStep();
        return adStep;
    }
}