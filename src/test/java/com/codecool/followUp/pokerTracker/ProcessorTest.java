package com.codecool.followUp.pokerTracker;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * com.codecool.followUp.pokerTracker by miki on 2016.11.30..
 */
@RunWith(Parameterized.class)
public class ProcessorTest {
    private static Processor processor;
    private String testSample;
    private enum Type {GETHHS, SEPARATE}

    private Type type;

    @BeforeClass
    public static void setUp() throws Exception {
        processor = new Processor();
    }

    public ProcessorTest(Type type, String testSample) {
        this.type = type;
        this.testSample = testSample;
    }

    @Parameterized.Parameters
    public static Collection testTxt() {
        String regex = ".+Hand\\s#\\d{11,13}:" + // handID
                ".+Level\\s[I*V?X?]{1,5}\\s\\(\\d{2,3}/\\d{2,3}\\)" + // blind levels
                ".+\\*{3}\\sHOLE\\sCARDS\\s\\*{3}" + // hole cards section
                ".+\\*{3}\\sSUMMARY\\s\\*{3}.+";
        return Arrays.asList(new Object[][]{
                {Type.GETHHS, "#160106436726: Tournament #1700461369"},
                {Type.GETHHS, "Table '1700461369 1' 3-max Seat #2 is the button"},
                {Type.GETHHS, "giannistsaga: raises 250 to 270 and is all-in"},
                {Type.GETHHS, "Seat 2: giannistsaga (button) (small blind) showed [7s 7d] and lost with a pair of Sevens"},
                {Type.SEPARATE, regex}
        });
    }

    @Test
    public void testGetHHs() throws Exception {
        Assume.assumeTrue(type == Type.GETHHS);
        System.out.println("input file contains the sample text");
        assertTrue(processor.getHHs().toString().contains(testSample));
    }

    @Test
    public void testSeparate() throws Exception {
        Assume.assumeTrue(type == Type.SEPARATE);
        for (String hh : processor.separateHHs()) {
            System.out.println("hh contains the necessary information");
            assertTrue(hh.matches(testSample));
        }
    }
}