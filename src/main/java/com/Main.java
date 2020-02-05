package com;

import com.utils.Utils;

public class Main {
    public static void main(String[] args) {
        Router router = new Router(Utils.defaultStep("https://www.olx.ua/uk/rabota/belayatserkov/"));
        router.route();
    }
}
