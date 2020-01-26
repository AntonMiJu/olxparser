package com.steps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class QuantityOfPagesGetter {

    public static String getQuantityOfPages(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.select("a[data-cy='page-link-last']")
                .select("span")
                .first()
                .text();
    }
}
