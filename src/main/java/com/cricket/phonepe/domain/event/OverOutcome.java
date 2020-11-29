package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.inning.InningType;
import lombok.Getter;

import java.util.List;


public class OverOutcome {
    private final String bowlerName;
    private final InningType inningType;
    private final int over;
    private final List<BallOutcome> ballOutcomes;


    public OverOutcome(String bowlerName, InningType inningType, int over, List<BallOutcome> ballOutcomes) {
        this.bowlerName = bowlerName;
        this.inningType = inningType;
        this.over = over;
        this.ballOutcomes = ballOutcomes;
    }

    public boolean isMaidenOver() {
        return ballOutcomes.stream().allMatch(ballOutcome -> ballOutcome.getRuns().equals(Run.ZERO));
    }

    public String getBowlerName() {
        return bowlerName;
    }

    public List<BallOutcome> getBallOutcomes() {
        return ballOutcomes;
    }

    public InningType getInningType() {
        return inningType;
    }
}
