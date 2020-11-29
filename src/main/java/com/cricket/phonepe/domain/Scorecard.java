package com.cricket.phonepe.domain;

import com.cricket.phonepe.domain.event.OverOutcome;
import com.cricket.phonepe.domain.exception.MinimumPlayerException;
import lombok.Getter;

import java.util.Optional;

@Getter
public class Scorecard {
    private final BattingOrder batsmen;
    private Batsman batsmanOnStrike;
    private Batsman batsmanOnNonStrike;
    private int extras;
    private double overs;
    private double numberOfBalls;
    private int wickets;

    public Scorecard(BattingOrder battingOrder) {
        this.batsmen = battingOrder;
        batsmanOnStrike = batsmen.nextBatsman().orElseThrow(MinimumPlayerException::new);
        batsmanOnStrike.setStatus(BatsmenStatus.CURRENTLY_PLAYING_ON_STRIKE);
        batsmanOnNonStrike = batsmen.nextBatsman().orElseThrow(MinimumPlayerException::new);
        batsmanOnNonStrike.setStatus(BatsmenStatus.CURRENTLY_PLAYING_NON_STRIKE);
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
        overOutcomes.getBallOutcomes().forEach(ballOutcome -> ballOutcome.processScorecard(this));
        rotateStrike();
    }

    public void incrementNumberOfBalls() {
        this.numberOfBalls += 1;
    }

    public void rotateStrike() {
        Batsman temp = batsmanOnStrike;
        batsmanOnStrike = batsmanOnNonStrike;
        batsmanOnNonStrike = temp;

        batsmanOnStrike.setStatus(BatsmenStatus.CURRENTLY_PLAYING_ON_STRIKE);
        batsmanOnNonStrike.setStatus(BatsmenStatus.CURRENTLY_PLAYING_NON_STRIKE);
    }

    public void addExtras(Run runs) {
        this.extras += runs.getValue();
    }

    public void handleWicket() {
        batsmanOnStrike.setStatus(BatsmenStatus.OUT);
        this.wickets += 1;
        Optional<Batsman> batsman = this.batsmen.nextBatsman();
        if (batsman.isPresent()) {
            batsmanOnStrike = batsman.get();
            batsmanOnStrike.setStatus(BatsmenStatus.CURRENTLY_PLAYING_ON_STRIKE);
        }
    }
}
