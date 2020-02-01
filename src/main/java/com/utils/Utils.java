package com.utils;

import com.Account;
import org.jsoup.Connection;
import org.jsoup.Jsoup;


public class Utils {
    private Account account;

    //TODO add more userAgent values
    private String[] userAgentArray = {"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36"};

    public Account getAccount() {
        return account;
    }

    public Utils setAccount(Account account) {
        this.account = account;
        return this;
    }

    //TODO implement userAgent setting on random base
    public static Connection defaultStep(String url) {
        return Jsoup.connect(url)/*.userAgent()*/;
    }

    public static Connection phoneStep(String url, String referrer, String cookie) {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .referrer(referrer)
                .cookie("PHPSESSID", cookie)
                /*.userAgent()*/;
    }
}
