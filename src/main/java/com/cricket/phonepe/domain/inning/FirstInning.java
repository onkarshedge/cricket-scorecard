package com.cricket.phonepe.domain.inning;

import com.cricket.phonepe.domain.InvalidInningEventException;
import com.cricket.phonepe.domain.Scorecard;
import com.cricket.phonepe.domain.Team;
import com.cricket.phonepe.domain.event.OverOutcome;
import lombok.Getter;

@Getter
public class FirstInning extends Inning {
    public FirstInning(Team battingTeam, Team bowlingTeam, int totalOvers) {
        super(battingTeam, bowlingTeam, totalOvers);
    }

    public boolean isComplete() {
        return scorecard.overs() == totalOvers || battingTeam.areAllOut();
    }

}
