package com;

import com.utils.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Router router = new Router(Utils.defaultStep("https://www.olx.ua/uk/rabota/belayatserkov/"));
        router.route();
    }
}
