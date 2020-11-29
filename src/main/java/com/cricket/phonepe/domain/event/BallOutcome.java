package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Batsman;
import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;

public abstract class BallOutcome {
    public abstract Run getPlayerRuns();

    public abstract Run getExtras();

    public int incrementBallsFaced() {
        return 1;
    }

    public Run getRuns() {
        return getPlayerRuns().add(getExtras());
    }

    protected void commonProcessing(Scorecard scorecard) {
        scorecard.addExtras(this.getExtras());
        Batsman batsmanOnStrike = scorecard.getBatsmanOnStrike();
        batsmanOnStrike.addRuns(this.getPlayerRuns());
        batsmanOnStrike.addBallsFaced(this.incrementBallsFaced());
    }

    public abstract void processScorecard(Scorecard scorecard);
}

//
//
//public interface BallOutcome {
//    Run getPlayerRuns();
//
//    Run getTeamRuns();
//
//    default int incrementBallsFaced() {
//        return 1;
//    }
//
//    default Run getRuns() {
//        return getPlayerRuns().add(getTeamRuns());
//    }
//
//    abstract processScorecard(Scorecard scorecard);
//}


