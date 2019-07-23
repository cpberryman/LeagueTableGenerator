package com.berryman.leaguetable.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chris berryman.
 */
public class LeagueTable {

    private static final int POINTS_FOR_WIN = 3;

    private List<Match> matches;

    public LeagueTable(final List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return the ordered list of league table entries
     */
    public List<LeagueTableEntry> getTableEntries() {

        Set<String> teamNames = extractTeamNames(matches);
        Map<String, List<Match>> teamNamesToMatchesMap = mapTeamNamesToMatchesPlayed(teamNames, matches);
        final List<LeagueTableEntry> entries = new ArrayList<>();

        teamNames.forEach(teamName -> {
            final List<Match> playedMatches = teamNamesToMatchesMap.get(teamName);
            final LeagueTableEntry leagueTableEntry = createLeagueTableEntry(playedMatches, teamName);
            entries.add(leagueTableEntry);
        });

        sortLeagueTableEntries(entries);

        return entries;
    }

    /**
     * Creates a league table entry.
     *
     * @param playedMatches the matches played for a team.
     * @param teamName the name of the team that played the matches.
     *
     * @return a league table entry.
     */
    private LeagueTableEntry createLeagueTableEntry(List<Match> playedMatches, String teamName) {

        int played = playedMatches.size();
        int won = getWonMatches(playedMatches, teamName);
        int drawn = getDrawnMatches(playedMatches, teamName);
        int lost = getLostMatches(playedMatches, teamName);
        int goalsFor = getGoalsFor(playedMatches, teamName);
        int goalsAgainst = getGoalsAgainst(playedMatches, teamName);
        int goalDifference = goalsAgainst - goalsFor;

        // 3 points for win, 1 for draw, 0 for loss
        int points = won * POINTS_FOR_WIN + drawn;

        return new LeagueTableEntry(
                teamName,
                played,
                won, drawn,
                lost,
                goalsFor,
                goalsAgainst,
                goalDifference,
                points);
    }

    /**
     * Map a given collection of team names to matches played in given collection of matches.
     *
     * @param teamNames the team names to map the matches to.
     * @param matches the matches to map to the team names.
     *
     * @return a map containing the team names and their respective matches.
     */
    private Map<String, List<Match>> mapTeamNamesToMatchesPlayed(Set<String> teamNames, List<Match> matches) {

        final Map<String, List<Match>> teamNamesToMatchesMap = new HashMap<>();

        teamNames.forEach(teamName -> {

            List<Match> playedMatches = matches
                    .stream()
                    .filter(m -> Objects.equals(m.getHomeTeam(), teamName)
                            || Objects.equals(m.getAwayTeam(), teamName))
                    .collect(Collectors.toList());

            teamNamesToMatchesMap.put(teamName, playedMatches);
        });

        return teamNamesToMatchesMap;

    }

    /**
     * Extracts the team names from the list of matches.
     *
     * @param matches the matches to extract the team names from.
     *
     * @return the list of team names.
     */
    private Set<String> extractTeamNames(List<Match> matches) {

        final Set<String> teamNames = new HashSet<>();

        matches.forEach(match -> {
            teamNames.add(match.getHomeTeam());
            teamNames.add(match.getAwayTeam());
        });

        return teamNames;
    }

    /**
     * Counts the won matches for given team and matches the team has played.
     *
     * @param playedMatches the matches played.
     * @param teamName the team name to retrieve the count for.
     *
     * @return the won matches.
     */
    private int getWonMatches(List<Match> playedMatches, String teamName) {
        return (int) playedMatches
                .stream()
                .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() > m.getAwayScore()
                        || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() > m.getHomeScore())
                .count();
    }

    /**
     * Counts the drawn matches for given team and matches the team has played.
     *
     * @param playedMatches the matches played.
     * @param teamName the team name to retrieve the count for.
     *
     * @return the drawn matches.
     */
    private int getDrawnMatches(List<Match> playedMatches, String teamName) {
        return (int) playedMatches
                .stream()
                .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() == m.getAwayScore()
                        || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() == m.getHomeScore())
                .count();
    }

    /**
     * Counts the lost matches for given team and matches the team has played.
     *
     * @param playedMatches the matches played.
     * @param teamName the team name to retrieve the count for.
     *
     * @return the lost matches.
     */
    private int getLostMatches(List<Match> playedMatches, String teamName) {
        return (int) playedMatches
                .stream()
                .filter(m -> Objects.equals(m.getHomeTeam(), teamName) && m.getHomeScore() < m.getAwayScore()
                        || Objects.equals(m.getAwayTeam(), teamName) && m.getAwayScore() < m.getHomeScore())
                .count();
    }

    /**
     * Retrieves goals for for given team and the matches they have played.
     *
     * @param playedMatches the matches played.
     * @param teamName the team name to retrieve the count for.
     *
     * @return the goals for.
     */
    private int getGoalsFor(List<Match> playedMatches, String teamName) {
        int goalsFor = 0;

        for (Match m : playedMatches) {

            if (m.getHomeTeam().equalsIgnoreCase(teamName)) {
                goalsFor += m.getHomeScore();
            }

            if (m.getAwayTeam().equalsIgnoreCase(teamName)) {
                goalsFor += m.getAwayScore();
            }

        }
        return goalsFor;
    }

    /**
     * Retrieves goals against for given team and the matches they have played.
     *
     * @param playedMatches the matches played.
     * @param teamName the team name to retrieve the count for.
     *
     * @return the goals against.
     */
    private int getGoalsAgainst(List<Match> playedMatches, String teamName) {
        int goalsAgainst = 0;

        for (Match m : playedMatches) {

            if (m.getHomeTeam().equalsIgnoreCase(teamName)) {
                goalsAgainst += m.getAwayScore();
            }

            if (m.getAwayTeam().equalsIgnoreCase(teamName)) {
                goalsAgainst += m.getHomeScore();
            }

        }
        return goalsAgainst;
    }

    /**
     * Sorts a given list of league table entries by points, goal difference and goals for in descending order,
     * then sorts by team name in ascending order.
     *
     * @param entries the league table entries to sort.
     */
    private void sortLeagueTableEntries(List<LeagueTableEntry> entries) {
        entries.sort(
                Comparator.comparing(LeagueTableEntry::getPoints)
                        .thenComparingInt(LeagueTableEntry::getGoalDifference)
                        .thenComparingInt(LeagueTableEntry::getGoalsFor)
                        .reversed()
                        .thenComparing(LeagueTableEntry::getTeamName)
        );
    }

}
