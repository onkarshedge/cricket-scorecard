package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;

public class LegByes extends BallOutcome {
    private final Run runs;

    public LegByes(Run runs) {
        this.runs = runs;
    }

    @Override
    public Run getPlayerRuns() {
        return Run.ZERO;
    }

    @Override
    public Run getExtras() {
        return runs;
    }

    @Override
    public void processScorecard(Scorecard scorecard) {

    }
}
