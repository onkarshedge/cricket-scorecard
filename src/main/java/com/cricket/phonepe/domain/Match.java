package com.cricket.phonepe.domain;


import com.cricket.phonepe.domain.event.OverOutcome;

import java.util.Optional;

public class Match {
    private Team team1;
    private Team team2;
    private int numberOfOvers;
    private FirstInning firstInning;
    private SecondInning secondInning;
    private InningType currentInning;

    public Match(Team team1, Team team2, int numberOfOvers) {
        this(team1, team2, numberOfOvers, true);
    }

    public Match(Team team1, Team team2, int numberOfOvers, boolean isFirstTeamPlayingFirst) {
        this.team1 = team1;
        this.team2 = team2;
        this.numberOfOvers = numberOfOvers;
        firstInning = new FirstInning(
                isFirstTeamPlayingFirst ? team1 : team2,
                !isFirstTeamPlayingFirst ? team1 : team2,
                numberOfOvers
        );
        currentInning = InningType.FIRST;
    }

    public void updateScorecard(OverOutcome overOutcomes) {
        if (!overOutcomes.getInningType().equals(currentInning)) {
            throw new InvalidInningEventException("Invalid Inning");
        }
        if (overOutcomes.getInningType().equals(InningType.FIRST)) {
            firstInning.updateScorecard(overOutcomes);
        } else {
            if (getSecondInning().isEmpty() && firstInning.isComplete()) {
                startSecondInning(firstInning.getScorecard().totalScore());
            }
            secondInning.updateScorecard(overOutcomes);
        }
    }

//    public boolean isComplete() {
//        return firstInning.isComplete() && secondInning.isComplete(firstInning.getScorecard().totalScore());
//    }

    private void startSecondInning(int runsToChase) {
        currentInning = InningType.SECOND;
        secondInning = new SecondInning(
                firstInning.getBowlingTeam(),
                firstInning.getBattingTeam(),
                numberOfOvers,
                runsToChase
        );
    }

    public Scorecard getScoreCard(InningType inningType) {
        return firstInning.getScorecard();
    }

    public Optional<SecondInning> getSecondInning() {
        return Optional.ofNullable(secondInning);
    }

}
