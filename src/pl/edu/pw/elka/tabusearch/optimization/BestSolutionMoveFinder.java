package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.SolutionMove;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.TwoOptNeighbourhood;

/**
 * Interface representing all classes that are able to find best solution in the neighbourhood.
 *
 * @author mc
 */
public interface BestSolutionMoveFinder {
    SolutionMove getBestNeighbour(final TwoOptNeighbourhood neighbourhood,
                                  final TabuList tabuList,
                                  final Integer threshold,
                                  final Solution globallyBestSolution);
}
