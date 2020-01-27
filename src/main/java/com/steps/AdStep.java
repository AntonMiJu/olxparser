package com.steps;

import com.Account;
import com.utils.DAO;
import com.utils.ExcelDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AdStep implements Step {
    private DAO dao;

    public static boolean isResponsible(String url) throws IOException {
        Elements el = Jsoup.connect(url).get().select("div.detailpage.t-new_sidebar");
        return !el.isEmpty();
    }

    public void parse(String url) throws IOException{
        System.out.println(url);
        Document document = Jsoup.connect(url).get();

        if (!document.select("div#offer_removed_by_user").isEmpty() || !document.select("div#offer_outdated").isEmpty())
            return;

        String address = document.select("address")
                .select("p")
                .first()
                .text();

        Element nameElement = document.select("div.offer-user__details")
                .select("h4")
                .first();
        if (nameElement.html().contains("<a>"))
            nameElement = nameElement.select("a").first();

        String name = nameElement.text();

        String dateRegistered = document.select("div.offer-user__details")
                .select("span.user-since")
                .first()
                .text();

        String htmlPhoneToken = document.select("section#body-container")
                .select("script")
                .first()
                .html();

        String phoneToken = htmlPhoneToken.substring(htmlPhoneToken.indexOf("'")+1, htmlPhoneToken.lastIndexOf("'"));

        getDao().save(new Account(PhoneGetter.getPhone(phoneToken), name, address, dateRegistered));
    }

    private DAO getDao(){
        if (dao==null)
            dao = new ExcelDAO();
        return dao;
    }
}
