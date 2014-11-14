package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.List;

public interface NeighbourhoodGenerator {
    List<Solution> generateNeighbourhood(Solution currentSolution);

    Move getLastMove();
}
