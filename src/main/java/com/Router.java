package com;

import com.steps.*;

import java.io.IOException;

public class Router {
    private String url;
    private Step step;

    public Router(String url) {
        this.url = url;
    }

    public void route() throws IOException {
        if (BaseURLStep.isResponsible(url)) {
            this.step = new BaseURLStep();
        } else if (CategoryStep.isResponsible(url)) {
            this.step = new CategoryStep();
        } else if (AdStep.isResponsible(url)) {
            this.step = new AdStep();
        } else {
            System.out.println("500: Can't find step for that URL");
        }

        step.parse(url);
    }
}
