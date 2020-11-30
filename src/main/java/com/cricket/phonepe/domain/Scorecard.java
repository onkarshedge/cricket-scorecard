package com.cricket.phonepe.domain;

import com.cricket.phonepe.domain.event.OverOutcome;
import com.cricket.phonepe.domain.exception.InvalidInningEventException;
import com.cricket.phonepe.domain.exception.MinimumPlayerException;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class Scorecard {
    private final Batsmen batsmen;
    private Optional<Integer> runsToChase;
    private Batsman batsmanOnStrike;
    private Batsman batsmanOnNonStrike;
    private Bowler currentBowler;
    private final Map<String, Bowler> bowlers;
    private int extras;
    private double overs;
    private double numberOfBalls;
    private int wickets;

    public Scorecard(Batsmen batsmen, List<Bowler> bowlers, Optional<Integer> runsToChase) {
        this.batsmen = batsmen;
        this.runsToChase = runsToChase;
        batsmanOnStrike = this.batsmen.nextBatsman().orElseThrow(MinimumPlayerException::new);
        batsmanOnNonStrike = this.batsmen.nextBatsman().orElseThrow(MinimumPlayerException::new);

        this.currentBowler = bowlers.get(0);
        this.bowlers = bowlers.stream().collect(Collectors.toMap(Bowler::getName, bowler -> bowler));
    }

    public int totalScore() {
        return this.extras + batsmen.getTotalScore();
    }

    public int extras() {
        return this.extras;
    }

    public double overs() {
        double overNumber = Math.floor(numberOfBalls / Constants.NUMBER_OF_BALLS_IN_AN_OVER);
        double ballsInLastOver = (numberOfBalls % Constants.NUMBER_OF_BALLS_IN_AN_OVER) * 0.1;
        return overNumber + ballsInLastOver;
    }

    public int wickets() {
        return this.wickets;
    }

    public int wicketsRemaining() {
        return batsmen.numberOfBatsmen() - wickets;
    }

    public void update(OverOutcome overOutcomes) {
        this.currentBowler = this.bowlers.get(overOutcomes.getBowlerName());
        overOutcomes.getBallOutcomes().forEach(ballOutcome -> {
            if (isComplete()) {
                throw new InvalidInningEventException("This inning is complete.");
            }
            ballOutcome.processScorecard(this);
        });
        rotateStrike();
        if (overOutcomes.isMaidenOver()) {
            currentBowler.incrementMaidenOvers();
        }
        currentBowler.incrementNumberOfOvers();
    }

    private boolean isComplete() {
        return batsmen.noMoreBatsman() || (runsToChase.isPresent() && totalScore() >= runsToChase.get());
    }

    public void incrementNumberOfBalls() {
        this.numberOfBalls += 1;
    }

    public void rotateStrike() {
        Batsman temp = batsmanOnStrike;
        batsmanOnStrike = batsmanOnNonStrike;
        batsmanOnNonStrike = temp;

    }

    public void addExtras(Run runs) {
        this.extras += runs.getValue();
    }

    public void handleWicket() {
        this.wickets += 1;
        Optional<Batsman> batsman = this.batsmen.nextBatsman();
        batsman.ifPresent(value -> batsmanOnStrike = value);
    }

    public Bowler getBowler(String name) {
        return bowlers.get(name);
    }
}
