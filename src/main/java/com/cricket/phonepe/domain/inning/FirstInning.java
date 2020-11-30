package com.cricket.phonepe.domain.inning;

import com.cricket.phonepe.domain.Scorecard;
import com.cricket.phonepe.domain.Team;
import lombok.Getter;

import java.util.Optional;

@Getter
public class FirstInning extends Inning {
    Scorecard scorecard;

    public FirstInning(Team battingTeam, Team bowlingTeam, int totalOvers) {
        super(battingTeam, bowlingTeam, totalOvers);
        this.scorecard = new Scorecard(battingTeam.getBattingOrder(), bowlingTeam.getBowlers(), Optional.empty());
    }

    public boolean isComplete() {
        return scorecard.overs() == totalOvers || battingTeam.areAllOut();
    }

}
