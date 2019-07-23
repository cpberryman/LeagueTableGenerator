package com.berryman.leaguetable.comparison;

import com.berryman.leaguetable.model.LeagueTableEntry;

import java.util.Comparator;

/**
 * @author chris berryman.
 */
public class LeagueEntryComparator implements Comparator<LeagueTableEntry> {

    @Override
    public int compare(LeagueTableEntry o1, LeagueTableEntry o2) {

//        return o1.getPoints() > o2.getPoints() ? 1 : 0;
        return 0;
    }

}
