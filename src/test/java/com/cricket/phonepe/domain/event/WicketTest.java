package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Scorecard;
import org.junit.jupiter.api.Test;

import static com.cricket.phonepe.domain.ScorecardUtil.getScorecardForThree;
import static com.cricket.phonepe.domain.ScorecardUtil.getScorecardForTwo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WicketTest {
    @Test
    void shouldNotIncrementExtraRuns() {
        Scorecard scorecardForTwo = getScorecardForTwo();
        Wicket wicketEvent = new Wicket();

        wicketEvent.processScorecard(scorecardForTwo);

        assertEquals(0, scorecardForTwo.extras());
    }

    @Test
    void shouldNotIncrementBatsmanRuns() {
        Scorecard scorecardForTwo = getScorecardForTwo();
        Wicket wicketEvent = new Wicket();

        wicketEvent.processScorecard(scorecardForTwo);

        assertEquals(0, scorecardForTwo.getBatsmanOnStrike().getScore());
    }

    @Test
    void shouldIncrementNumberOfTotalBallsPlayed() {
        Scorecard scorecardForTwo = getScorecardForTwo();
        Wicket wicketEvent = new Wicket();

        wicketEvent.processScorecard(scorecardForTwo);

        assertEquals(1, scorecardForTwo.getNumberOfBalls());
    }

    @Test
    void shouldIncrementWicketsTaken(){
        Scorecard scorecardForTwo = getScorecardForTwo();
        Wicket wicketEvent = new Wicket();

        wicketEvent.processScorecard(scorecardForTwo);

        assertEquals(1, scorecardForTwo.wickets());
    }

    @Test
    void shouldChangeBatsmanOnStrike(){
        Scorecard scorecardForTwo = getScorecardForThree("P1","P2","P3");
        Wicket wicketEvent = new Wicket();

        wicketEvent.processScorecard(scorecardForTwo);

        assertNotEquals("P3", scorecardForTwo.getBatsmanOnStrike());
    }
}