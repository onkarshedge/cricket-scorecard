package com.cricket.phonepe.domain.result;

import io.vavr.control.Either;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Victory {
    String winningTeam;
    Either<ByRuns, ByWickets> by;

    public Victory(String winningTeam, Either<ByRuns, ByWickets> by) {
        this.winningTeam = winningTeam;
        this.by = by;
    }

    @EqualsAndHashCode
    public static final class ByRuns {
        int numberOfRuns;

        public ByRuns(int numberOfRuns) {
            this.numberOfRuns = numberOfRuns;
        }

        @Override
        public String toString() {
            return String.format("%s run", numberOfRuns);
        }
    }

    @EqualsAndHashCode
    public static final class ByWickets {
        int numberOfWickets;

        public ByWickets(int numberOfWickets) {
            this.numberOfWickets = numberOfWickets;
        }

        @Override
        public String toString() {
            return String.format("%s wicket", numberOfWickets);
        }

    }

    @Override
    public String toString() {
        return String.format("%s has won by %s", winningTeam, by.isLeft() ? by.getLeft() : by.get());
    }
}
