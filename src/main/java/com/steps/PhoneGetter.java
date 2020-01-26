package com.steps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PhoneGetter {
    private String URLQuery = "https://www.olx.ua/ajax/misc/contact/phone/FcLQp/?pt=";

    public PhoneGetter(String phoneToken) {
        this.URLQuery += phoneToken;
    }

    public String getPhone() throws IOException {
        Document document = Jsoup.connect(URLQuery).get();

        return null;
    }
}