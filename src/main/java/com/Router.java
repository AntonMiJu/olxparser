package com;

import com.steps.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Router {
    private String url;
    private Step step;

    public Router(String url) {
        this.url = url;
    }

    private void route() throws IOException {
        Document document = Jsoup.connect(url).get();
        if (BaseURLStep.isResponsible(document)) {
            this.step = new BaseURLStep(document);
        } else if (CategoryStep.isResponsible(document)) {
            this.step = new CategoryStep(document);
        } else if (AdStep.isResponsible(document)) {
            this.step = new AdStep(document);
        } else {
            System.out.println("500: Can't find step for that URL");
        }

        step.parse();
    }
}
