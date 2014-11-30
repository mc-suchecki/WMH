package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.SolutionMove;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.TwoOptNeighbourhood;

/**
 * Class implementing Aspiration Plus strategy to find best solution in the neighbourhood.
 *
 * @author mc
 */
public class AspirationPlusFinder implements BestSolutionFinder {

    // parameters that define algorithm flow
    private final Integer minParameter;
    private final Integer maxParameter;
    private final Integer plusParameter;

    // move that resulted in generating the best solution
    private Move bestMove;

    public AspirationPlusFinder(final Integer min, final Integer max, final Integer plus) {
        this.minParameter = min;
        this.maxParameter = max;
        this.plusParameter = plus;
    }

    @Override
    public Solution getBestSolution(final TwoOptNeighbourhood neighbourhood, final TabuList tabuList,
                                    final Integer aspirationThreshold, final Solution globallyBestSolution) {
        SolutionMove bestNeighbour = neighbourhood.iterator().next();
        int solutionChecked = 0;
        int solutionsSinceAspirationSatisfied = 0;
        boolean aspirationThresholdReached = false;
        for (final SolutionMove neighbour : neighbourhood) {
            final Solution currentSolution = neighbour.getSolution();
            final Move currentMove = neighbour.getMove();

            if (isAllowed(tabuList, globallyBestSolution, neighbour)
                    && currentSolution.isBetterThan(bestNeighbour.getSolution())) {
                bestNeighbour = neighbour;
                this.bestMove = currentMove;//TODO zmienić, bo słabe
            }

            aspirationThresholdReached |= (currentSolution.getDistance() <= aspirationThreshold);
            if (aspirationThresholdReached) {
                ++solutionsSinceAspirationSatisfied;
            }
            ++solutionChecked;

            if (enoughSolutionsChecked(solutionChecked, solutionsSinceAspirationSatisfied)) {
                break;
            }
        }

        return bestNeighbour.getSolution();
    }

    private boolean isAllowed(final TabuList tabuList, final Solution globallyBestSolution,
                              final SolutionMove neighbour) {
        final Move move = neighbour.getMove();
        final Solution solution = neighbour.getSolution();
        return !tabuList.contains(move) || solution.isBetterThan(globallyBestSolution);
    }

    private boolean enoughSolutionsChecked(final int solutionsChecked, final int solutionsSinceAspirationSatisfied) {
        if (solutionsChecked < minParameter) {
            return false;
        } else if (solutionsChecked > maxParameter) {
            return true;
        } else if (solutionsSinceAspirationSatisfied < plusParameter) {
            return false;
        }
        return true;
    }

    @Override
    public Move getLastMove() {
        return this.bestMove;
    }

}
