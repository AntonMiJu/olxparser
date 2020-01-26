package com.steps;

import org.jsoup.nodes.Document;

public interface Step {
    static boolean isResponsible(Document document) {
        return false;
    }

    void parse();
}
