package com;

import com.utils.Utils;

public class Main {
    public static void main(String[] args) {
        Router router = new Router(Utils.defaultStep("https://www.olx.ua/rabota/vinnitsa/?search%5Boffer_seek%5D=seek"));
        router.route();
    }
}
