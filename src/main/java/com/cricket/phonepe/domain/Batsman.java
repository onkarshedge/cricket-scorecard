package com.cricket.phonepe.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Batsman {
    private String name;
    private int score = 0;
    private Map<Run, Integer> runMap;
    private int ballsFaced = 0;

    public Batsman(String name) {
        this.name = name;
        this.runMap = new HashMap<>();
    }

    public void addRuns(Run playerRuns) {
        this.score += playerRuns.getValue();
        this.runMap.put(playerRuns, this.runMap.getOrDefault(playerRuns, 0) + 1);
    }

    public void addBallsFaced(int ballsFaced) {
        this.ballsFaced += ballsFaced;
    }

    public int getFours() {
        return this.runMap.getOrDefault(Run.FOUR, 0);
    }

    public int getSixes() {
        return this.runMap.getOrDefault(Run.SIX, 0);
    }
}
