package com.berryman.leaguetable.test.utils;

import com.berryman.leaguetable.model.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with test data
 *
 * @author chris berryman.
 */
public final class Fixtures {

    private static final Match match = new Match("Aston Villa", "Derby County", 2, 1);
    private static final Match match1 = new Match("Leeds United", "Derby County", 2, 4);
    private static final Match match2 = new Match("West Bromwich Albion", "Aston Villa", 1, 0);
    private static final Match match3 = new Match("Derby County", "Leeds United", 0, 1);
    private static final Match match4 = new Match("Aston Villa", "Norwich City", 1, 2);
    private static final Match match5 = new Match("Brentford", "Preston North End", 3, 0);
    private static final Match match6 = new Match("Derby County", "West Bromwich Albion", 3, 1);
    private static final Match match7 = new Match("Hull City", "Bristol City", 1, 1);
    private static final Match match8 = new Match("Ipswich Town", "Leeds United", 3, 2);

    public static List<Match> testMatches() {

        final List<Match> testMatches = new ArrayList<>();

        testMatches.add(match);
        testMatches.add(match1);
        testMatches.add(match2);
        testMatches.add(match3);
        testMatches.add(match4);
        testMatches.add(match5);
        testMatches.add(match6);
        testMatches.add(match7);
        testMatches.add(match8);

        return testMatches;
    }

}
