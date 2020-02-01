package com.steps;

import java.io.IOException;

public interface Step extends Runnable{
    @Override
    void run();

    void parse() throws IOException;
}
