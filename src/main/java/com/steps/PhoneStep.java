package com.steps;

import com.utils.DAO;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PhoneStep implements Step {
    private DAO dao;

    public static boolean isResponsible(Document document) {
        String body = document.body().text();
        return body.startsWith("{") && body.contains("\"value\"");
    }

    @Override
    public void parse(Document document) {
        JSONObject object = new JSONObject(document.body().text());
        String value = object.get("value").toString().replace(" ", "");

        //TODO extract numbers
        if (value.matches("\\+\\d+|\\d+")) {
            //single number

//            account.setPhone(value);
//            dao.save(account);
        } else {
            //multiple numbers
            Document document1 = Jsoup.parse(value);
        }
    }
}