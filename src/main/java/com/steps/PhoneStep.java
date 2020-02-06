package com.steps;

import com.Account;
import com.utils.DAO;
import com.utils.ExcelDAO;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class PhoneStep implements Step {
    private Account account;
    private String responseBody;
    private DAO dao;

    public PhoneStep(Account account, String responseBody) {
        this.account = account;
        this.responseBody = responseBody;
    }

    public static boolean isResponsible(String responseBody) {
        return responseBody.startsWith("{") && responseBody.contains("\"value\"");
    }

    @Override
    public void run() {
        parse();
    }

    @Override
    public void parse() {
        JSONObject object = new JSONObject(responseBody);

        List<String> numbers = new ArrayList<>();
        String value = object.get("value").toString();

        if (StringUtils.contains(value, "span")) {
            Document document = Jsoup.parse(value);
            numbers.addAll(document.select("span.block").eachText());
        } else {
            numbers.add(value);
        }

        for (String number : numbers) {
            System.out.println(new Account(account, validateNumber(number.replaceAll("\\D+", ""))));
            //getDao().save(new Account(account, number));
        }
    }

    private String validateNumber(String number) {
        if (number.startsWith("0"))
            number = "38" + number;
        if (number.startsWith("8"))
            number = "3" + number;
        return number;
    }

    private DAO getDao() {
        if (dao == null)
            dao = new ExcelDAO();
        return dao;
    }
}