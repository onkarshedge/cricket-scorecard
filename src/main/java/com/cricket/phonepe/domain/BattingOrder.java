package com.cricket.phonepe.domain;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class BattingOrder {
    private List<Batsman> value;
    private int upcomingPlayerIndex = 0;

    public BattingOrder(List<Batsman> value) {
        this.value = value;
    }

    public Optional<Batsman> nextBatsman() {
        Optional<Batsman> nextBatsmanToPlay = Optional.empty();
        if (upcomingPlayerIndex >= value.size()) {
            return nextBatsmanToPlay;
        }
        nextBatsmanToPlay = Optional.of(value.get(upcomingPlayerIndex));
        upcomingPlayerIndex++;
        return nextBatsmanToPlay;
    }

    public boolean noMoreBatsman() {
        return upcomingPlayerIndex >= value.size();
    }

    public int numberOfBatsmen() {
        return value.size();
    }

    public int getTotalScore() {
        return value.stream().map(Batsman::getScore).reduce(0, Integer::sum);
    }
}
