package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.io.config.Config;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.SolutionMove;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.TwoOptNeighbourhood;

import java.util.List;

public class TabuSearchSolver implements Solver {

    private final InitialSolutionGenerator solutionGenerator = new InitialSolutionGenerator();
    private final BestSolutionMoveFinder bestSolutionMoveFinder;

    private final TabuList tabuList;

    public TabuSearchSolver(final Config config) {
        this.tabuList = new TabuList(config.getTabuListSize());
        this.bestSolutionMoveFinder = new AspirationPlusFinder(
                config.getMinParameter(), config.getMaxParameter(), config.getPlusParameter());
    }

    @Override
    public Solution findSolution(final Graph graph) {
        final Integer aspiration = generateAspirationLevel(graph);
        Solution currentSolution = solutionGenerator.generateInitialSolution(graph);
        Solution bestSolution = currentSolution;
        Integer iterationsWithoutImprovement = 0;

        while (iterationsWithoutImprovement < 5)  {
            final TwoOptNeighbourhood neighbourhood = new TwoOptNeighbourhood(currentSolution);
            final SolutionMove currentSolutionMove = bestSolutionMoveFinder.getBestSolutionMove(
                    neighbourhood, tabuList, aspiration, bestSolution);
            currentSolution = currentSolutionMove.getSolution();
            final Move currentMove = currentSolutionMove.getMove();
            if (currentSolution.isBetterThan(bestSolution)) {
                tabuList.add(currentMove);
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
