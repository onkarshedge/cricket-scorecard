package com.cricket.phonepe.domain;


import com.cricket.phonepe.Result;
import com.cricket.phonepe.Tie;
import com.cricket.phonepe.domain.event.OverOutcome;
import com.cricket.phonepe.domain.inning.FirstInning;
import com.cricket.phonepe.domain.inning.InningType;
import com.cricket.phonepe.domain.inning.SecondInning;
import io.vavr.control.Either;

import java.util.Optional;

public class Match {
    private Team team1;
    private Team team2;
    private int numberOfOvers;
    private FirstInning firstInning;
    private SecondInning secondInning;
    private InningType currentInning;

    public Match(Team team1, Team team2, int numberOfOvers) {
        this(team1, team2, numberOfOvers, true);
    }

    public Match(Team team1, Team team2, int numberOfOvers, boolean isFirstTeamPlayingFirst) {
        this.team1 = team1;
        this.team2 = team2;
        this.numberOfOvers = numberOfOvers;
        firstInning = new FirstInning(
                isFirstTeamPlayingFirst ? team1 : team2,
                !isFirstTeamPlayingFirst ? team1 : team2,
                numberOfOvers
        );
        currentInning = InningType.FIRST;
    }

    public void updateScorecard(OverOutcome overOutcomes) {
        if (!overOutcomes.getInningType().equals(currentInning)) {
            throw new InvalidInningEventException("Invalid Inning");
        }
        if (overOutcomes.getInningType().equals(InningType.FIRST)) {
            firstInning.updateScorecard(overOutcomes);
            if (firstInning.isComplete() && getSecondInning().isEmpty()) {
                startSecondInning(getRunsToChase());
            }
        } else {
            secondInning.updateScorecard(overOutcomes);
        }
    }

    private int getRunsToChase() {
        return firstInning.getScorecard().totalScore() + 1;
    }

    public boolean isComplete() {
        return firstInning.isComplete() && secondInning.isComplete();
    }

    public Optional<Result> getResult() {
        if (!isComplete()) {
            return Optional.empty();
        }
        int runsRemainingToWin = secondInning.getRunsRemainingToWin();
        if (runsRemainingToWin > 1) {
            return Optional.of(
                    new Result(
                            Either.left(
                                    new Victory(
                                            firstInning.getBattingTeam().getName(),
                                            Either.left(new Victory.ByRuns(runsRemainingToWin - 1))
                                    )
                            )
                    )
            );
        } else if (runsRemainingToWin < 1) {
            int wonByNumberOfWickets = secondInning.getWicketsRemaining();
            return Optional.of(
                    new Result(
                            Either.left(
                                    new Victory(
                                            secondInning.getBattingTeam().getName(),
                                            Either.right(new Victory.ByWickets(wonByNumberOfWickets))
                                    )
                            )
                    )
            );
        }
        return Optional.of(new Result(Either.right(new Tie())));
    }

    private void startSecondInning(int runsToChase) {
        currentInning = InningType.SECOND;
        secondInning = new SecondInning(
                firstInning.getBowlingTeam(),
                firstInning.getBattingTeam(),
                numberOfOvers,
                runsToChase
        );
    }

    public Scorecard getScoreCard(InningType inningType) {
        if (inningType.equals(InningType.FIRST))
            return firstInning.getScorecard();

        return secondInning.getScorecard();
    }

    public Optional<SecondInning> getSecondInning() {
        return Optional.ofNullable(secondInning);
    }

}
