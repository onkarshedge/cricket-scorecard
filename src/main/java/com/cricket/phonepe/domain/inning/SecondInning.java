package com.cricket.phonepe.domain.inning;

import com.cricket.phonepe.domain.Team;
import lombok.Getter;

@Getter
public class SecondInning extends Inning {
    private final int runsToChase;

    public SecondInning(Team battingTeam, Team bowlingTeam, int totalOvers, int runsToChase) {
        super(battingTeam, bowlingTeam, totalOvers);
        this.runsToChase = runsToChase;
    }

    public boolean isComplete() {
        return scorecard.overs() == totalOvers || battingTeam.areAllOut() || scorecard.totalScore() >= runsToChase;
    }

    public int getRunsRemainingToWin() {
        return runsToChase - this.getScorecard().totalScore();
    }

    public int getWicketsRemaining() {
        return scorecard.wicketsRemaining();
    }
}
