package pl.edu.pw.elka.tabusearch.optimization.neighbourhood;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Solution;

/**
 * Class representing a {@link Move} and {@link Solution}.
 */
public class SolutionMove {
    private final Move move;
    private final Solution solution;

    public SolutionMove(final Move move, final Solution solution) {
        this.move = move;
        this.solution = solution;
    }

    public Move getMove() {
        return move;
    }

    public Solution getSolution() {
        return solution;
    }
}
