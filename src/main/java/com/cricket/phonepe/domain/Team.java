package com.cricket.phonepe.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private List<Player> players;
    private BattingOrder battingOrder;

    public Team(List<Player> players) {
        BattingOrder battingOrder = new BattingOrder(players.stream()
                .map((Player player) -> new Batsman(player.getName()))
                .collect(Collectors.toList())
        );
        this.battingOrder = battingOrder;
        this.players = players;
    }

    public Team(List<Player> players, BattingOrder battingOrder) {
        this.players = players;
        this.battingOrder = battingOrder;
    }

    public BattingOrder getBattingOrder() {
        return battingOrder;
    }

    public boolean areAllOut() {
        return battingOrder.noMoreBatsman();
    }

    //    LinkedList<Integer> battingOrder;
//    LinkedList<Integer> bowlingOrder;
//
//    public Team(List<Player> players, LinkedList<Integer> battingOrder, LinkedList<Integer> bowlingOrder) {
//        this.players = players;
//        this.battingOrder = battingOrder;
//        this.bowlingOrder = bowlingOrder;
//    }
}
