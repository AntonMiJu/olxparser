package com.steps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class PhoneGetter {
    private static String URLQuery = "https://www.olx.ua/ajax/misc/contact/phone/FcLQp/?pt=";

    public static String getPhone(String phoneToken) throws IOException {
//        URLQuery += phoneToken;
//        Document document = Jsoup.connect(URLQuery).get();
        return phoneToken;
    }
}