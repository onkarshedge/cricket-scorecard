package com.cricket.phonepe.domain;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VictoryTest {
    @Test
    void shouldReturnResultAsTeamOneWonBy4run() {
        Either<Victory.ByRuns, Victory.ByWickets> by = Either.left(new Victory.ByRuns(4));
        assertEquals("Team1 has won by 4 run", new Victory("Team1", by).toString());
    }

    @Test
    void shouldReturnResultAsTeamOneWonBy2wickets() {
        Either<Victory.ByRuns, Victory.ByWickets> by = Either.right(new Victory.ByWickets(2));
        assertEquals("Team1 has won by 2 wicket", new Victory("Team1", by).toString());
    }
}