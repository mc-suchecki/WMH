package pl.edu.pw.elka.tabusearch.domain;

/**
 * Class representing move that converted one Solution to another.
 * @author mc
 */
public class Move {

    /** Parameters that define the move - conversion from one solution to another. */
    private final Integer swapStart, swapEnd;

    public Move(final Integer swapStart, final Integer swapEnd) {
        this.swapStart = swapStart;
        this.swapEnd = swapEnd;
    }

    // TODO implement - Jacek
}
