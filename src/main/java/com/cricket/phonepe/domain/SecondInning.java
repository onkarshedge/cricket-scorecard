package com.cricket.phonepe.domain;

import com.cricket.phonepe.domain.event.OverOutcome;

public class SecondInning {
    private Team battingTeam;
    private Team bowlingTeam;
    private final Scorecard scorecard;
    private final int totalOvers;
    private final int runsToChase;


    public SecondInning(Team battingTeam, Team bowlingTeam, int totalOvers, int runsToChase) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.scorecard = new Scorecard(battingTeam.getBattingOrder());
        this.totalOvers = totalOvers;
        this.runsToChase = runsToChase;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public void updateScorecard(OverOutcome overOutcomes) {
        if (isComplete())
            throw new InvalidInningEventException("Second Inning is already complete");
    }


    public boolean isComplete() {
        return scorecard.overs() == totalOvers || battingTeam.areAllOut() || scorecard.totalScore() >= runsToChase;
    }
}
