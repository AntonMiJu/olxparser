package com;

import com.utils.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Router router = new Router(Utils.defaultStep("https://www.olx.ua/uk/obyavlenie/bolshaya-2-k-kvartira-58m2-v-zhk-terracotta-ot-perfect-group-m-vyrlitsa-IDGDOPA.html"));
        router.route();
    }
}
