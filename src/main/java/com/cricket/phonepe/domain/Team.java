package com.cricket.phonepe.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private List<Player> players;
    private final BattingOrder battingOrder;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.battingOrder = new BattingOrder(players.stream()
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

    public BattingOrder getBattingOrder() {
        return battingOrder;
    }

    public boolean areAllOut() {
        return battingOrder.noMoreBatsman();
    }

}
