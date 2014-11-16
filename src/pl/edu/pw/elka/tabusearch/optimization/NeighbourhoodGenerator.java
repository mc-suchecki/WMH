package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Neighbourhood;
import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.List;

/**
 * Interface representing all classes that are able to generate Solution's neighbourhood.
 * @author mc
 */
public interface NeighbourhoodGenerator {
    Neighbourhood generateNeighbourhood(Solution currentSolution);
}
