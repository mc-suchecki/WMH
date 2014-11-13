package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.List;

public interface BestSolutionFinder {
    Solution getBestSolution(List<Solution> neighbourhood, List<Solution> tabuList, Integer threshold);
}
