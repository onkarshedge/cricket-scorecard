package com.cricket.phonepe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ScorecardUtil {
    public static Scorecard getScorecardForTwo(String firstPlayerName, String secondPlayerName) {
        List<Batsman> batsmen = new ArrayList<>();
        batsmen.add(new Batsman(firstPlayerName));
        batsmen.add(new Batsman(secondPlayerName));

        Batsmen battingOrder = new Batsmen(batsmen);
        return new Scorecard(battingOrder, getBowlers(), Optional.empty());
    }

    public static Scorecard getScorecardForTwo() {
        List<Batsman> batsmen = new ArrayList<>();
        batsmen.add(new Batsman("Player 1"));
        batsmen.add(new Batsman("Player 2"));

        Batsmen battingOrder = new Batsmen(batsmen);
        return new Scorecard(battingOrder, getBowlers(), Optional.empty());
    }

    public static Scorecard getScorecardForThree(String firstPlayerName, String secondPlayerName, String thirdPlayerName) {
        List<Batsman> batsmen = new ArrayList<>();
        batsmen.add(new Batsman(firstPlayerName));
        batsmen.add(new Batsman(secondPlayerName));
        batsmen.add(new Batsman(thirdPlayerName));

        Batsmen battingOrder = new Batsmen(batsmen);
        return new Scorecard(battingOrder, getBowlers(), Optional.empty());
    }

    private static List<Bowler> getBowlers() {
        return List.of(
                new Bowler("bowler1"),
                new Bowler("bowler2"),
                new Bowler("bowler3")
        );
    }
}
