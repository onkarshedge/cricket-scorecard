package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;

public class Wicket extends BallOutcome {
    @Override
    public Run getPlayerRuns() {
        return Run.ZERO;
    }

    @Override
    public Run getExtras() {
        return Run.ZERO;
    }

    @Override
    public void processScorecard(Scorecard scorecard) {
        commonProcessing(scorecard);
        scorecard.handleWicket();
    }
}
