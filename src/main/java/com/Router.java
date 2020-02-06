package com;

import com.steps.AdStep;
import com.steps.BaseURLStep;
import com.steps.CategoryStep;
import com.steps.PhoneStep;
import com.steps.Step;
import com.utils.Utils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
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
        try {
            Connection.Response response = connection.execute();

            if (isNotValidResponse(response))
                return;

            String responseBody = response.body();
            Document document = Jsoup.parse(responseBody, response.url().toString());

            Step step = createStep(responseBody, document);
            if (step == null)
                return;

            new Thread(step).start();
            Thread.sleep(450);
            /*uncomment line below and comment two lines above to work without multithreading way*/
//            step.run();
        } catch (IOException e) {
            System.err.println("Exception in router");
        } catch (InterruptedException e) {
            System.err.println("Thread exception");
        }
    }

    private Step createStep(String responseBody, Document document) {
        if (BaseURLStep.isResponsible(document)) {
            return new BaseURLStep(document);
        } else if (CategoryStep.isResponsible(document)) {
            return new CategoryStep(document);
        } else if (AdStep.isResponsible(document)) {
            return new AdStep(connection.response().cookie("PHPSESSID"), document);
        } else if (PhoneStep.isResponsible(responseBody)) {
            return new PhoneStep(utils.getAccount(), responseBody);
        } else {
            System.err.println("500: Can't find step for that URL");
            return null;
        }
    }

    private boolean isNotValidResponse(Connection.Response response) {
        return response.statusCode() == 404;
    }
}