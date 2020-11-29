package com.cricket.phonepe.domain.inning;

import com.cricket.phonepe.domain.Scorecard;
import com.cricket.phonepe.domain.Team;
import com.cricket.phonepe.domain.event.OverOutcome;
import com.cricket.phonepe.domain.exception.InvalidInningEventException;

public abstract class Inning {
    protected Team battingTeam;
    protected Team bowlingTeam;
    protected final Scorecard scorecard;
    protected final int totalOvers;


    public Inning(Team battingTeam, Team bowlingTeam, int totalOvers) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.scorecard = new Scorecard(battingTeam.getBattingOrder(), bowlingTeam.getBowlers());
        this.totalOvers = totalOvers;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    protected abstract boolean isComplete();

    public void updateScorecard(OverOutcome overOutcomes) {
        if (isComplete())
            throw new InvalidInningEventException("Inning is already complete");
        scorecard.update(overOutcomes);
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }
}
