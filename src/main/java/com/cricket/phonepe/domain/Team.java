package com.cricket.phonepe.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private List<Player> players;
    private final Batsmen batsmen;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.batsmen = new Batsmen(players.stream()
                .map((Player player) -> new Batsman(player.getName()))
                .collect(Collectors.toList())
        );
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public List<Bowler> getBowlers() {
        return players.stream()
                .map((Player player) -> new Bowler(player.getName()))
                .collect(Collectors.toList());
    }

    //    public Team(String name, List<Player> players, BattingOrder battingOrder) {
//        this.name = name;
//        this.players = players;
//        this.battingOrder = battingOrder;
//    }

    public Batsmen getBattingOrder() {
        return batsmen;
    }

    public boolean areAllOut() {
        return batsmen.noMoreBatsman();
    }

}
