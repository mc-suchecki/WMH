package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.io.Config;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.TwoOptNeighbourhood;

import java.util.List;

public class TabuSearchSolver implements Solver {

    private final InitialSolutionGenerator solutionGenerator = new InitialSolutionGenerator();
    private final BestSolutionFinder bestSolutionFinder;

    private final Config config; //TODO Maciek: use parameters from config
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
        TwoOptNeighbourhood neighbourhood;
        Integer iterationsWithoutImprovement = 0;
        final Integer aspiration = generateAspirationLevel(graph);

        while (iterationsWithoutImprovement < 5)  {
            neighbourhood = new TwoOptNeighbourhood(currentSolution);
            currentSolution = bestSolutionFinder.getBestSolution(neighbourhood, tabuList, aspiration);
            if (currentSolution.getDistance() > bestSolution.getDistance()) {
                tabuList.add(bestSolutionFinder.getLastMove());
                iterationsWithoutImprovement = 0;
                bestSolution = currentSolution;
            } else {
                iterationsWithoutImprovement++;
            }
        }

        return bestSolution;
    }

    /**
     * Returns aspiration level - here defined as sum of every node's average distance to another.
     * @param graph graph to analyze
     * @return aspiration level
     */
    private Integer generateAspirationLevel(final Graph graph) {
        final List<Node> nodesList = graph.getNodesList();
        Integer aspirationLevel = 0;
        for (final Node node : nodesList) {
            aspirationLevel += node.getAverageDistance();
        }
        return aspirationLevel;
    }
}
