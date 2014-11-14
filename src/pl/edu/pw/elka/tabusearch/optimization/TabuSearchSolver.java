/**
 * @author <a href="mailto:jacek.witkowski@gmail.com">Jacek Witkowski</a>
 * Created on 2014-11-2014.
 */
package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.io.Config;

import java.util.ArrayList;
import java.util.List;

public class TabuSearchSolver implements Solver {
    private final InitialSolutionGenerator solutionGenerator = new InitialSolutionGenerator();
    private final NeighbourhoodGenerator neighbourhoodGenerator = new TwoOptNeighbourhoodGenerator();
    private final BestSolutionFinder bestSolutionFinder;

    private final Config config;
    private TabuList tabuList;

    public TabuSearchSolver(final Config config) {
        this.config = config;
        this.tabuList = new TabuList(config.getTabuListSize());
        this.bestSolutionFinder = new AspirationPlusFinder(
                config.getMinParameter(), config.getMaxParameter(), config.getPlusParameter());
    }

    @Override
    public Solution findSolution(final Graph graph) {
        Solution currentSolution = solutionGenerator.generateInitialSolution(graph);
        Solution bestSolution = currentSolution;
        List<Solution> neighbourhood;
        Integer aspiration = 0; // TODO initialize this properly - Maciek

        // TODO define stop criterion - iterations limit or couple iterations without improvement - Maciek
        while (currentSolution.getDistance() > 5)  {
            neighbourhood = neighbourhoodGenerator.generateNeighbourhood(currentSolution);
            currentSolution = bestSolutionFinder.getBestSolution(neighbourhood, tabuList, aspiration);
            tabuList.add(neighbourhoodGenerator.getLastMove());
            if (currentSolution.getDistance() > bestSolution.getDistance()) {
                bestSolution = currentSolution;
            }
        }

        return bestSolution;
    }
}
