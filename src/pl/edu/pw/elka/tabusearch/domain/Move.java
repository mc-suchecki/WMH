package pl.edu.pw.elka.tabusearch.domain;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(swapStart, swapEnd);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        return Objects.equals(this.swapStart, other.swapStart) && Objects.equals(this.swapEnd, other.swapEnd);
    }
}
