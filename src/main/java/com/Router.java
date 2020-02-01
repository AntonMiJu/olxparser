package com;

import com.steps.AdStep;
import com.steps.BaseURLStep;
import com.steps.CategoryStep;
import com.steps.PhoneStep;
import com.steps.Step;
import com.utils.Utils;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Router {
    private Connection connection;
    private Utils utils;

    public Router(Connection connection) {
        this.connection = connection;
    }

    public Router(Connection connection, Utils utils) {
        this.connection = connection;
        this.utils = utils;
    }

    public void route() {
        Step step;

        try {
            Document document = connection.get();

            if (BaseURLStep.isResponsible(document)) {
                step = new BaseURLStep(document);
            } else if (CategoryStep.isResponsible(document)) {
                step = new CategoryStep(document);
            } else if (AdStep.isResponsible(document)) {
                step = new AdStep(connection.response().cookie("PHPSESSID"), document);
            } else if (PhoneStep.isResponsible(document)) {
                step = new PhoneStep(utils.getAccount(), document);
            } else {
                System.out.println("500: Can't find step for that URL");
                //TODO some mechanism for processing this situations without interrupting of program
                return;
            }

            step.run();
        } catch (IOException e) {
            System.err.println("Exception in router");
        }
    }
}
