package com.berryman.leaguetable.comparison;

import com.berryman.leaguetable.model.LeagueTableEntry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author chris berryman.
 */
public class LeagueEntryComparatorTest {

    @Test
    public void itShouldCompareGivenLeagueTableEntriesCorrectly() {

        LeagueTableEntry entry = new LeagueTableEntry("Aston Villa", 3, 1, 0, 2, 3, 4, 1, 3);
        LeagueTableEntry entry1 = new LeagueTableEntry("Leeds United", 3, 1, 0, 2, 5, 7, 2, 3);
        LeagueTableEntry entry2 = new LeagueTableEntry("West Bromwich Albion", 2, 1, 0, 1, 2, 3, 1, 3);
        LeagueTableEntry entry3 = new LeagueTableEntry("Derby County", 4, 2, 0, 2, 8, 6, -2, 6);
        LeagueTableEntry entry4 = new LeagueTableEntry("Brentford", 1, 1, 0, 0, 3, 0, -3, 3);
        LeagueTableEntry entry5 = new LeagueTableEntry("Hull City", 1, 0, 1, 0, 1, 1, 0, 1);
        LeagueTableEntry entry6 = new LeagueTableEntry("Ipswich Town", 1, 1, 0, 0, 3, 2, -1, 3);
        
        LeagueEntryComparator comparator = new LeagueEntryComparator();

        int result = comparator.compare(entry, entry1);

        assertEquals(1, result);
    }
}
