package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Scorecard;
import org.junit.jupiter.api.Test;

import static com.cricket.phonepe.domain.ScorecardUtil.getScorecardForTwo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NoBallTest {
    @Test
    void shouldIncrementExtraRunsByOne() {
        NoBall noBallEvent = new NoBall();
        Scorecard scorecardForTwo = getScorecardForTwo();
        noBallEvent.processScorecard(scorecardForTwo);

        assertEquals(1, scorecardForTwo.extras());
    }

    @Test
    void shouldNotIncrementBatsmanRuns() {
        Scorecard scorecardForTwo = getScorecardForTwo();

        NoBall noBallEvent = new NoBall();
        noBallEvent.processScorecard(scorecardForTwo);

        assertEquals(0, scorecardForTwo.getBatsmanOnStrike().getScore());
    }
}