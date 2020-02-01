package com.steps;

import com.Router;
import com.utils.Utils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CategoryStep implements Step {
    private static final String suffixForLink = "?page=";

    private Document document;

    public CategoryStep(Document document) {
        this.document = document;
    }

    public static boolean isResponsible(Document document) {
        Elements el = document.select("div.pager.rel.clr");
        return !el.isEmpty();
    }

    @Override
    public void run() {

    }

    public void parse() throws IOException {
        if (!document.baseUri().contains("?page=")) {
            createNextPageSteps(document);
        }

        Elements links = document.select("div.offer-wrapper")
                .select("div.space.rel")
                .select("a[href]");

        for (Element el : links) {
            new Router(Utils.defaultStep(el.attr("href"))).route();
        }
    }

    private void createNextPageSteps(Document document) throws IOException {
        int lastPageIndex = getQuantityOfPages(document);

        for (int i = 2; i <= lastPageIndex; i++) {
            String pageUrl = document.baseUri() + suffixForLink + i;
            new Router(Utils.defaultStep(pageUrl)).route();
        }
    }

    private int getQuantityOfPages(Document document) {
        String lastPage = document.select("a[data-cy='page-link-last']")
                .select("span")
                .first()
                .text();

        return Integer.parseInt(lastPage);
    }
}