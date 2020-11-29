package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;

public class RunOutcome extends BallOutcome {
    private final Run runs;

    public RunOutcome(Run runs) {
        this.runs = runs;
    }

    @Override
    public Run getPlayerRuns() {
        return this.runs;
    }

    @Override
    public Run getExtras() {
        return Run.ZERO;
    }

    @Override
    public void processScorecard(Scorecard scorecard) {
        commonProcessing(scorecard);
        if (getPlayerRuns().shouldChangeStrike()) scorecard.rotateStrike();
        scorecard.incrementNumberOfBalls();
    }


}
