package com.berryman.leaguetable.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author chris berryman.
 */
public class LeagueTableTest {

    @Test
    public void getTableEntriesShouldReturnTheOrderedListOfLeagueTableEntriesForGivenLeagueTable() {

        // Given
        Match match = new Match("Aston Villa", "Derby County", 2, 1);
        Match match1 = new Match("Leeds United", "Derby County", 2, 4);
        Match match2 = new Match("West Bromwich Albion", "Aston Villa", 1, 0);
        Match match3 = new Match("Derby County", "Leeds United", 0, 1);
        Match match4 = new Match("Aston Villa", "Norwich City", 1, 2);
        Match match5 = new Match("Brentford", "Preston North End", 3, 0);
        Match match6 = new Match("Derby County", "West Bromwich Albion", 3, 1);
        Match match7 = new Match("Hull City", "Bristol City", 1, 1);
        Match match8 = new Match("Ipswich Town", "Leeds United", 3, 2);

        List<Match> testMatches = new ArrayList<>();
        testMatches.add(match);
        testMatches.add(match1);
        testMatches.add(match2);
        testMatches.add(match3);
        testMatches.add(match4);
        testMatches.add(match5);
        testMatches.add(match6);
        testMatches.add(match7);
        testMatches.add(match8);

        LeagueTableEntry entry = new LeagueTableEntry("Aston Villa", 3, 1, 0, 2, 3, 4, 1, 3);
        LeagueTableEntry entry1 = new LeagueTableEntry("Leeds United", 3, 1, 0, 2, 5, 7, 2, 3);
        LeagueTableEntry entry2 = new LeagueTableEntry("West Bromwich Albion", 2, 1, 0, 1, 2, 3, 1, 3);
        LeagueTableEntry entry3 = new LeagueTableEntry("Derby County", 4, 2, 0, 2, 8, 6, -2, 6);
        LeagueTableEntry entry4 = new LeagueTableEntry("Brentford", 1, 1, 0, 0, 3, 0, -3, 3);
        LeagueTableEntry entry5 = new LeagueTableEntry("Hull City", 1, 0, 1, 0, 1, 1, 0, 1);
        LeagueTableEntry entry6 = new LeagueTableEntry("Ipswich Town", 1, 1, 0, 0, 3, 2, -1, 3);

        LeagueTable leagueTable = new LeagueTable(testMatches);

        // When
        List<LeagueTableEntry> result = leagueTable.getTableEntries();

        // Then
//        assertThat(result.get(0), equalTo(entry3));
        result.forEach(r -> System.out.println(r.toString()));

    }
}
