package com.codecool.followUp.pokerTracker;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * com.codecool.followUp.pokerTracker by miki on 2016.12.02..
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ProcessorTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
