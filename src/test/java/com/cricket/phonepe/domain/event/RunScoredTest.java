package com.cricket.phonepe.domain.event;

import com.cricket.phonepe.domain.Run;
import com.cricket.phonepe.domain.Scorecard;
import org.junit.jupiter.api.Test;

import static com.cricket.phonepe.domain.ScorecardUtil.getScorecardForTwo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RunScoredTest {
    @Test
    void shouldNotIncrementExtraRuns() {
        Scorecard scorecardForTwo = getScorecardForTwo();
        RunScored runsScored = new RunScored(Run.THREE);

        runsScored.processScorecard(scorecardForTwo);

        assertEquals(0, scorecardForTwo.extras());
    }

    @Test
    void shouldNotChangeStrikeForEvenRunsScored() {
        Scorecard scorecardForTwo = getScorecardForTwo("P1", "P2");
        assertEquals("P1", scorecardForTwo.getBatsmanOnStrike().getName());

        RunScored runsScored = new RunScored(Run.DOUBLE);
        runsScored.processScorecard(scorecardForTwo);

        assertEquals("P1", scorecardForTwo.getBatsmanOnStrike().getName());
        assertEquals(2, scorecardForTwo.getBatsmanOnStrike().getScore());
    }

    @Test
    void shouldChangeStrikeForOddRunsScored() {
        Scorecard scorecardForTwo = getScorecardForTwo("P1", "P2");
        assertEquals("P1", scorecardForTwo.getBatsmanOnStrike().getName());

        RunScored runsScored = new RunScored(Run.THREE);
        runsScored.processScorecard(scorecardForTwo);

        assertEquals("P2", scorecardForTwo.getBatsmanOnStrike().getName());
        assertEquals(3, scorecardForTwo.getBatsmanOnNonStrike().getScore());
    }

    @Test
    void shouldIncrementNumberOfTotalBallsPlayed() {
        Scorecard scorecardForTwo = getScorecardForTwo();
        RunScored runsScored = new RunScored(Run.THREE);

        runsScored.processScorecard(scorecardForTwo);

        assertEquals(1, scorecardForTwo.getNumberOfBalls());
        assertEquals(1, scorecardForTwo.getBatsmanOnNonStrike().getBallsFaced());
        assertEquals(0, scorecardForTwo.getBatsmanOnStrike().getBallsFaced());
    }
}