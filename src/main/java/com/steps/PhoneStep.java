package com.steps;

import com.Account;
import com.utils.DAO;
import com.utils.Utils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PhoneStep implements Step {
    private Account account;
    private DAO dao;

    public PhoneStep(Account account) {
        this.account = account;
    }

    public static boolean isResponsible(Document document) {
        String body = document.body().text();
        return body.startsWith("{") && body.contains("\"value\"");
    }

    @Override
    public void parse(Document document) {
        JSONObject object = new JSONObject(document.body().text());
        String value = object.get("value").toString().replace(" ", "");

        String regex = "[^\\d]+";
        String[] numbers = value.split(regex);

        for (String number : numbers) {
            dao.save(new Account(account, number));
        }
    }
}