package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.List;

/**
 * Created by mc on 11/11/14.
 */
public interface BestSolutionFinder {
    Solution getBestSolution(List<Solution> neighbourhood, List<Solution> tabuList, Integer threshold);
}
