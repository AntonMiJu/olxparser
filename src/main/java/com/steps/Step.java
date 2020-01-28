package com.steps;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Step {
    void parse(Document document) throws IOException;
}
