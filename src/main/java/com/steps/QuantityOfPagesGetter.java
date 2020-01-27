package com.steps;

import org.jsoup.Jsoup;

import java.io.IOException;

public class QuantityOfPagesGetter {

    public static int getQuantityOfPages(String url) throws IOException {
        String lastPage = Jsoup.connect(url).get()
                .select("a[data-cy='page-link-last']")
                .select("span")
                .first()
                .text();
        return Integer.parseInt(lastPage);
    }
}
