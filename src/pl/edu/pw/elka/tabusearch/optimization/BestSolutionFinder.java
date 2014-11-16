package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.TwoOptNeighbourhood;

/**
 * Interface representing all classes that are able to find best solution in the neighbourhood.
 *
 * @author mc
 */
public interface BestSolutionFinder {
    Solution getBestSolution(TwoOptNeighbourhood neighbourhood, TabuList tabuList, Integer threshold);

    Move getLastMove();
}
