package com.berryman.leaguetable.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chris berryman.
 */
public class LeagueTable {

    private List<Match> matches;

    public LeagueTable(final List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return the ordered list of league table entries
     */
    public List<LeagueTableEntry> getTableEntries_() {

        List<LeagueTableEntry> entries = new ArrayList<>();

        Set<String> processed = new HashSet<>();

        for (Match match : matches) {

            String teamName = match.getHomeTeam();

            if (!processed.contains(teamName)) {

                List<Match> playedMatches = matches
                        .stream()
                        .filter(m -> Objects.equals(m.getHomeTeam(), teamName)
                                || Objects.equals(m.getAwayTeam(), teamName))
                        .collect(Collectors.toList());

                int played = playedMatches.size();

                int won = (int) playedMatches
                        .stream()
                        .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() > m.getAwayScore()
                                || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() > m.getHomeScore())
                        .count();

                int drawn = (int) playedMatches
                        .stream()
                        .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() == m.getAwayScore()
                                || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() == m.getHomeScore())
                        .count();

                int lost = (int) playedMatches
                        .stream()
                        .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() < m.getAwayScore()
                                || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() < m.getHomeScore())
                        .count();

                int goalsFor = 0;

                for (Match m : playedMatches) {

                    if (m.getHomeTeam().equalsIgnoreCase(teamName)) {
                        goalsFor += m.getHomeScore();
                    }

                    if (m.getAwayTeam().equalsIgnoreCase(teamName)) {
                        goalsFor += m.getAwayScore();
                    }

                }

                int goalsAgainst = 0;

                for (Match m : playedMatches) {

                    if (m.getHomeTeam().equalsIgnoreCase(teamName)) {
                        goalsAgainst += m.getAwayScore();
                    }

                    if (m.getAwayTeam().equalsIgnoreCase(teamName)) {
                        goalsAgainst += m.getHomeScore();
                    }

                }

                int goalDifference = goalsAgainst - goalsFor;

                // 3 points for win, 1 for draw, 0 for loss
                int points = won * 3 + drawn;

                LeagueTableEntry e = new LeagueTableEntry(teamName, played, won, drawn, lost, goalsFor, goalsAgainst, goalDifference, points);
                entries.add(e);

                processed.add(teamName);
            }
        }

        return entries;
    }

    public List<LeagueTableEntry> getTableEntries() {

        Set<String> teamNames = extractTeamNames(matches);
        Map<String, List> teamNamesToMatchesMap = mapTeamNamesToMatchesPlayed(teamNames, matches);

        List<LeagueTableEntry> entries = new ArrayList<>();

        for (String teamName : teamNames) {

            List<Match> playedMatches = teamNamesToMatchesMap.get(teamName);

            int played = playedMatches.size();

            int won = (int) playedMatches
                    .stream()
                    .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() > m.getAwayScore()
                            || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() > m.getHomeScore())
                    .count();

            int drawn = (int) playedMatches
                    .stream()
                    .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() == m.getAwayScore()
                            || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() == m.getHomeScore())
                    .count();

            int lost = (int) playedMatches
                    .stream()
                    .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() < m.getAwayScore()
                            || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() < m.getHomeScore())
                    .count();

            int goalsFor = 0;

            for (Match m : playedMatches) {

                if (m.getHomeTeam().equalsIgnoreCase(teamName)) {
                    goalsFor += m.getHomeScore();
                }

                if (m.getAwayTeam().equalsIgnoreCase(teamName)) {
                    goalsFor += m.getAwayScore();
                }

            }

            int goalsAgainst = 0;

            for (Match m : playedMatches) {

                if (m.getHomeTeam().equalsIgnoreCase(teamName)) {
                    goalsAgainst += m.getAwayScore();
                }

                if (m.getAwayTeam().equalsIgnoreCase(teamName)) {
                    goalsAgainst += m.getHomeScore();
                }

            }

            int goalDifference = goalsAgainst - goalsFor;

            // 3 points for win, 1 for draw, 0 for loss
            int points = won * 3 + drawn;


            LeagueTableEntry e = new LeagueTableEntry(teamName, played, won, drawn, lost, goalsFor, goalsAgainst, goalDifference, points);
            entries.add(e);
        }

        return entries;
    }

    public Map<String, List> mapTeamNamesToMatchesPlayed(Set<String> teamNames, List<Match> matches) {

        Map<String, List> teamNamesToMatchesMap = new HashMap<>();

        for (String teamName : teamNames) {
            List<Match> playedMatches = matches
                    .stream()
                    .filter(m -> Objects.equals(m.getHomeTeam(), teamName)
                            || Objects.equals(m.getAwayTeam(), teamName))
                    .collect(Collectors.toList());

            teamNamesToMatchesMap.put(teamName, playedMatches);
        }

        return teamNamesToMatchesMap;

    }

    public Set<String> extractTeamNames(List<Match> matches) {

        Set<String> teamNames = new HashSet<>();

        for (Match m : matches) {

            teamNames.add(m.getHomeTeam());
            teamNames.add(m.getAwayTeam());

        }

        return teamNames;
    }

}
