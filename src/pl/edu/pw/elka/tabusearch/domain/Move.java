package pl.edu.pw.elka.tabusearch.domain;

public class Move {

    // parameters that define the move - conversion from one solution to another
    private final Integer swapStart;
    private final Integer swapEnd;

    public Move(final Integer swapStart, final Integer swapEnd) {
        this.swapStart = swapStart;
        this.swapEnd = swapEnd;
    }

    // TODO implement - Jacek
}
