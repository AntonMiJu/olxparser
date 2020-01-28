package com.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;


public class Utils {
    public static Connection defaultStep(String url) {
        return Jsoup.connect(url);
    }

    public static Connection phoneStep(String url, String referrer, String cookie) {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .referrer(referrer)
                .cookie("PHPSESSID", cookie);
    }
}
