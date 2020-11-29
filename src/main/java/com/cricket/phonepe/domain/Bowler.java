package com.cricket.phonepe.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Bowler {
    private final String playerName;
    private int wickets;
    private int maidenOvers;
    private int numberOfOvers;
    private int runsConceded;
    private int wides;
    private int noBalls;

    private final Map<Run, Integer> runMap;

    public Bowler(String playerName) {
        this.playerName = playerName;
        this.runMap = new HashMap<>();
    }

    public void incrementNoBall() {
        this.noBalls += 1;
    }

    public void incrementWickets() {
        this.wickets += 1;
    }

    public void incrementMaidenOvers() {
        this.maidenOvers += 1;
    }

    public int getDotBalls() {
        return this.runMap.getOrDefault(Run.ZERO, 0);
    }

    public void addRunsConceded(Run runs) {
        this.runsConceded += runs.getValue();
        this.runMap.put(runs, this.runMap.getOrDefault(runs, 0) + 1);
    }

    public void incrementWides() {
        this.wides += 1;
    }

    public void incrementNumberOfOvers() {
        this.numberOfOvers += 1;
    }

}
