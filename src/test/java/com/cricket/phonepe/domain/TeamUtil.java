package com.cricket.phonepe.domain;

import io.vavr.Tuple2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TeamUtil {
    static Tuple2<Team, Team>  getTeams(int numberOfPlayers) {
        List<Player> playersInFirstTeam = IntStream.range(1, numberOfPlayers + 1).mapToObj(playerNumber -> new Player("T1P" + playerNumber)).collect(Collectors.toList());
        List<Player> playersInSecondTeam = IntStream.range(1, numberOfPlayers + 1).mapToObj(playerNumber -> new Player("T2P" + playerNumber)).collect(Collectors.toList());
        return new Tuple2<>(new Team("Team 1", playersInFirstTeam), new Team("Team 2", playersInSecondTeam));
    }
}
