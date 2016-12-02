package com.codecool.followUp.pokerTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * com.codecool.followUp.pokerTracker by miki on 2016.11.29..
 */
class Processor {
    private static final String HERO = "pheal";
    static BigDecimal bi;
    Map<String, Integer> sstacks;
    int sb;
    int bb;

    /*
     * open, then cast contents of a poker hand history txt file to string
     */
    StringBuilder getHHs() throws IOException {
        StringBuilder HHs;
        String txt;
        try (BufferedReader br = new BufferedReader(new FileReader(
                Arrays.asList(new File("./src/test/resources").listFiles()).get(0)))) {
            HHs = new StringBuilder();
            while ((txt = br.readLine()) != null) HHs.append(txt);
            return HHs;
        }
    }

    /*
     * slice up the text into individual HHs
     */
    String[] separateHHs() throws IOException {
        String[] HHArr;
        HHArr = getHHs().toString().replace("PokerStars Hand", "    PokerStars Hand")
                .split("\\s{4}");
        List<String> tempList = new ArrayList<>(Arrays.asList(HHArr));
        tempList.remove(0);
        return tempList.toArray(new String[0]);
    }

}
