package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.InningType;
import lombok.Getter;

import java.util.List;

@Getter
public class OverOutcome {
    private InningType inningType;
    private int over;
    private List<BallOutcome> ballOutcomes;

    public OverOutcome(InningType inningType, int over, List<BallOutcome> ballOutcomes) {
        this.inningType = inningType;
        this.over = over;
        this.ballOutcomes = ballOutcomes;
    }

}
