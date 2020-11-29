package com.cricket.phonepe.domain;

import com.cricket.phonepe.domain.result.Result;
import com.cricket.phonepe.domain.event.*;
import com.cricket.phonepe.domain.inning.InningType;
import com.cricket.phonepe.domain.result.Victory;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        OverOutcome overOutcomes = new OverOutcome("T2P1", firstInning, 1, firstOverBallOutComes);

        match.updateScorecard(overOutcomes);

        Scorecard scoreCard = match.getScoreCard(firstInning);
        assertThat(scoreCard.getBatsmen().getValue())
                .extracting("playerName", "score", "fours", "sixes", "ballsFaced")
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
        playFirstInnings(match);

        InningType firstInning = InningType.FIRST;

        Scorecard scoreCard = match.getScoreCard(firstInning);
        assertThat(scoreCard.getBatsmen().getValue())
                .extracting("playerName", "score", "fours", "sixes", "ballsFaced")
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

    @Test
    void shouldUpdateScoreBoardFor2ndInnings() {
        Tuple2<Team, Team> teams = TeamUtil.getTeams(5);
        Match match = new Match(teams._1, teams._2, 2);

        playFirstInnings(match);
        InningType secondInning = InningType.SECOND;

        List<BallOutcome> firstOverBallOutComes = List.of(
                new RunOutcome(Run.FOUR),
                new RunOutcome(Run.SIX),
                new Wicket(),
                new Wicket(),
                new RunOutcome(Run.SINGLE),
                new RunOutcome(Run.SINGLE)
        );
        List<BallOutcome> secondOverBallOutComes = List.of(
                new RunOutcome(Run.SIX),
                new RunOutcome(Run.SINGLE),
                new Wicket(),
                new Wicket()
        );

        OverOutcome firstOverOutcomes = new OverOutcome("T1P3", secondInning, 1, firstOverBallOutComes);
        OverOutcome secondOverOutcomes = new OverOutcome("T1P5", secondInning, 2, secondOverBallOutComes);

        match.updateScorecard(firstOverOutcomes);
        match.updateScorecard(secondOverOutcomes);

        Scorecard scoreCard = match.getScoreCard(secondInning);
        assertThat(scoreCard.getBatsmen().getValue())
                .extracting("playerName", "score", "fours", "sixes", "ballsFaced")
                .contains(
                        tuple("T2P1", 10, 1, 1, 3),
                        tuple("T2P2", 8, 0, 1, 3),
                        tuple("T2P3", 0, 0, 0, 1),
                        tuple("T2P4", 1, 0, 0, 2),
                        tuple("T2P5", 0, 0, 0, 1)
                );
        assertEquals(19, scoreCard.totalScore());
        assertEquals(4, scoreCard.wickets());
        assertEquals(1.4, scoreCard.overs());
        assertEquals(0, scoreCard.extras());
        assertEquals("T2P2", scoreCard.getBatsmanOnStrike().getPlayerName());
        assertEquals("T2P5", scoreCard.getBatsmanOnNonStrike().getPlayerName());

        Optional<Result> optionalResult = match.getResult();
        assertTrue(optionalResult.isPresent());

        Result actualResult = optionalResult.get();
        assertEquals(new Victory(teams._1.getName(), Either.left(new Victory.ByRuns(4))), actualResult.getValue().getLeft());
    }

    private void playFirstInnings(Match match) {
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

        OverOutcome firstOverOutcomes = new OverOutcome("T2P3", InningType.FIRST, 1, firstOverBallOutComes);
        OverOutcome secondOverOutcomes = new OverOutcome("T2P4", InningType.FIRST, 2, secondOverBallOutComes);

        match.updateScorecard(firstOverOutcomes);
        match.updateScorecard(secondOverOutcomes);

    }

}