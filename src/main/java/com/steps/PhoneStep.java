package com.steps;

import com.Account;
import com.utils.DAO;
import com.utils.ExcelDAO;
import com.utils.Utils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PhoneStep implements Step {
    private Account account;
    private Document document;
    private DAO dao;

    public PhoneStep(Account account, Document document) {
        this.account = account;
        this.document = document;
    }

    public static boolean isResponsible(Document document) {
        String body = document.body().text();
        return body.startsWith("{") && body.contains("\"value\"");
    }

    @Override
    public void run() {

    }

    @Override
    public void parse() {
        JSONObject object = new JSONObject(document.body().text());
        String value = object.get("value").toString().replace(" ", "");

        String regex = "[^\\d]+";
        String[] numbers = value.split(regex);

        for (String number : numbers) {
            getDao().save(new Account(account, number));
        }
    }

    private DAO getDao() {
        if (dao == null)
            dao = new ExcelDAO();
        return dao;
    }
}