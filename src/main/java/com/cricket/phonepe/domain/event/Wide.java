package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;

public class Wide extends BallOutcome {
    @Override
    public Run getPlayerRuns() {
        return Run.ZERO;
    }

    @Override
    public Run getExtras() {
        return Run.SINGLE;
    }

    @Override
    public int incrementBallsFaced() {
        return 0;
    }

    @Override
    public void processScorecard(Scorecard scorecard) {
        commonProcessing(scorecard);
    }
}

//wd+lg(1)+w
//-> wide, legBye(1), wicket()
//-> 1run, wicket()
//-> legBye(1)
//team runs = wide + no_ball, legbye
//individual = normal
//wicket
