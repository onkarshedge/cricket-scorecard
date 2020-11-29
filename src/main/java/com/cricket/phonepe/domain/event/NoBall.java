package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;

public class NoBall extends BallOutcome {
    @Override
    public Run getPlayerRuns() {
        return Run.ZERO;
    }

    @Override
    public Run getExtras() {
        return Run.SINGLE;
    }

    @Override
    public void processScorecard(Scorecard scorecard) {
        commonProcessing(scorecard);
        scorecard.getCurrentBowler().incrementNoBall();
    }
}

