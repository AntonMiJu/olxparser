package com.steps;

public interface Step extends Runnable {
    @Override
    void run();

    void parse();
}
