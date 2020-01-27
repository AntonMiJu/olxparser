package com.steps;

import java.io.IOException;

public interface Step {
    static boolean isResponsible(String url) {
        return false;
    }

    void parse(String url) throws IOException;
}
