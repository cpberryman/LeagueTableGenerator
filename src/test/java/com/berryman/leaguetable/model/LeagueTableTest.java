package com.berryman.leaguetable.model;

import com.berryman.leaguetable.test.utils.Fixtures;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author chris berryman.
 */
public class LeagueTableTest {

    @Test
    public void getTableEntriesShouldReturnTheOrderedListOfLeagueTableEntriesForGivenLeagueTable() {

        // Given
        List<Match> testMatches = Fixtures.testMatches();
        LeagueTable leagueTable = new LeagueTable(testMatches);

        // When
        List<LeagueTableEntry> result = leagueTable.getTableEntries();

        // Then

        // the league table entries are sorted by points, goal difference, goals for and team names
        assertThat(result.get(0), equalTo(new LeagueTableEntry("Derby County", 4, 2, 0, 2, 8, 6, -2, 6)));
        assertThat(result.get(1), equalTo(new LeagueTableEntry("Leeds United", 3, 1, 0, 2, 5, 7, 2, 3)));
        assertThat(result.get(2), equalTo(new LeagueTableEntry("Aston Villa", 3, 1, 0, 2, 3, 4, 1, 3)));
        assertThat(result.get(3), equalTo(new LeagueTableEntry("West Bromwich Albion", 2, 1, 0, 1, 2, 3, 1, 3)));
        assertThat(result.get(4), equalTo(new LeagueTableEntry("Ipswich Town", 1, 1, 0, 0, 3, 2, -1, 3)));
        assertThat(result.get(5), equalTo(new LeagueTableEntry("Norwich City", 1, 1, 0, 0, 2, 1, -1, 3)));
        assertThat(result.get(6), equalTo(new LeagueTableEntry("Brentford", 1, 1, 0, 0, 3, 0, -3, 3)));
        assertThat(result.get(7), equalTo(new LeagueTableEntry("Bristol City", 1, 0, 1, 0, 1, 1, 0, 1)));
        assertThat(result.get(8), equalTo(new LeagueTableEntry("Hull City", 1, 0, 1, 0, 1, 1, 0, 1)));
        assertThat(result.get(9), equalTo(new LeagueTableEntry("Preston North End", 1, 0, 0, 1, 0, 3, 3, 0)));


        result.forEach(r -> System.out.println(r.toString()));

    }
}
