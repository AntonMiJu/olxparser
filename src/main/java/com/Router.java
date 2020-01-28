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

    public Router(Connection connection) {
        this.connection = connection;
    }

    public void route() throws IOException {
        Step step;

        Document document = connection.get();

        if (BaseURLStep.isResponsible(document)) {
            step = new BaseURLStep();
        } else if (CategoryStep.isResponsible(document)) {
            step = new CategoryStep();
        } else if (AdStep.isResponsible(document)) {
            step = new AdStep(connection.response().cookie("PHPSESSID"));
        } else if (PhoneStep.isResponsible(document)) {
            step = new PhoneStep();
        } else {
            System.out.println("500: Can't find step for that URL");
            //TODO some mechanism for processing this situations without interrupt program
            return;
        }

        step.parse(document);
    }
}
