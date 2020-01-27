package com;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Router router = new Router("https://www.olx.ua/nedvizhimost/garazhy-parkovki/");
        router.route();
    }
}
