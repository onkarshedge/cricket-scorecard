package com.cricket.phonepe.domain;

import com.cricket.phonepe.domain.event.OverOutcome;
import lombok.Getter;

@Getter
public class FirstInning {
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final Scorecard scorecard;
    private final int totalOvers;

    public FirstInning(Team battingTeam, Team bowlingTeam, int totalOvers) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.totalOvers = totalOvers;
        this.scorecard = new Scorecard(battingTeam.getBattingOrder());
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public boolean isComplete() {
        return scorecard.overs() == totalOvers || battingTeam.areAllOut();
    }

    public void updateScorecard(OverOutcome overOutcomes) {
        if (this.isComplete()) {
            throw new InvalidInningEventException("First Inning is already complete");
        }
        scorecard.update(overOutcomes);
    }
}
