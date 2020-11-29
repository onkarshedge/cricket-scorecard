package com.cricket.phonepe.domain;

import com.cricket.phonepe.domain.event.*;
import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {

    @Test
    void shouldUpdateOpeningBatsmenRunsAfterFirstOver() {
        Tuple2<Team, Team> teams = TeamUtil.getTeams(5);
        Match match = new Match(teams._1, teams._2, 2);

        List<BallOutcome> firstOverBallOutComes = List.of(
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.DOUBLE)
        );
        InningType firstInning = InningType.FIRST;
        OverOutcome overOutcomes = new OverOutcome(firstInning, 1, firstOverBallOutComes);

        match.updateScorecard(overOutcomes);

        Scorecard scoreCard = match.getScoreCard(firstInning);
        assertThat(scoreCard.getBatsmen().getValue())
                .extracting("playerName", "score", "fours", "sixes", "balls")
                .contains(
                        tuple("T1P1", 3, 0, 0, 3),
                        tuple("T1P2", 4, 0, 0, 3),
                        tuple("T1P3", 0, 0, 0, 0),
                        tuple("T1P4", 0, 0, 0, 0),
                        tuple("T1P5", 0, 0, 0, 0)
                );
        assertEquals(7, scoreCard.totalScore());
        assertEquals(0, scoreCard.wickets());
        assertEquals(1, scoreCard.overs());
        assertEquals(0, scoreCard.extras());
        assertEquals("T1P1", scoreCard.getBatsmanOnStrike().getPlayerName());
        assertEquals("T1P2", scoreCard.getBatsmanOnNonStrike().getPlayerName());

    }

    @Test
    void shouldUpdateScoreBoardAfterSecondOver() {
        Tuple2<Team, Team> teams = TeamUtil.getTeams(5);
        Match match = new Match(teams._1, teams._2, 2);

        List<BallOutcome> firstOverBallOutComes = List.of(
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.DOUBLE)
        );
        List<BallOutcome> secondOverBallOutComes = List.of(
                new Wicket(),
                new RunOutcome(Run.FOUR),
                new RunOutcome(Run.FOUR),
                new Wide(),
                new Wicket(),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SIX)
        );
        InningType firstInning = InningType.FIRST;
        OverOutcome firstOverOutcomes = new OverOutcome(firstInning, 1, firstOverBallOutComes);
        OverOutcome secondOverOutcomes = new OverOutcome(firstInning, 1, secondOverBallOutComes);

        match.updateScorecard(firstOverOutcomes);
        match.updateScorecard(secondOverOutcomes);

        Scorecard scoreCard = match.getScoreCard(firstInning);
        assertThat(scoreCard.getBatsmen().getValue())
                .extracting("playerName", "score", "fours", "sixes", "balls")
                .contains(
                        tuple("T1P1", 3, 0, 0, 4),
                        tuple("T1P2", 10, 0, 1, 4),
                        tuple("T1P3", 8, 2, 0, 3),
                        tuple("T1P4", 1, 0, 0, 1),
                        tuple("T1P5", 0, 0, 0, 0)
                );
        assertEquals(23, scoreCard.totalScore());
        assertEquals(2, scoreCard.wickets());
        assertEquals(2, scoreCard.overs());
        assertEquals(1, scoreCard.extras());
        assertEquals("T1P4", scoreCard.getBatsmanOnStrike().getPlayerName());
        assertEquals("T1P2", scoreCard.getBatsmanOnNonStrike().getPlayerName());
    }

}