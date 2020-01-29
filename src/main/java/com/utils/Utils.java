package com.utils;

import com.Account;
import org.jsoup.Connection;
import org.jsoup.Jsoup;


public class Utils {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public Utils setAccount(Account account) {
        this.account = account;
        return this;
    }

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
