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
public class AspirationPlusFinder implements BestSolutionMoveFinder {

    // parameters that define algorithm flow
    private final Integer minParameter;
    private final Integer maxParameter;
    private final Integer plusParameter;

    public AspirationPlusFinder(final Integer min, final Integer max, final Integer plus) {
        this.minParameter = min;
        this.maxParameter = max;
        this.plusParameter = plus;
    }

    @Override
    public SolutionMove getBestNeighbour(final TwoOptNeighbourhood neighbourhood, final TabuList tabuList,
                                         final Integer aspirationThreshold, final Solution globallyBestSolution) {
        SolutionMove bestNeighbour = null;
        int neighboursChecked = 0;
        int neighboursSinceAspirationSatisfied = 0;
        boolean aspirationThresholdReached = false;

        for (final SolutionMove neighbour : neighbourhood) {
            final Solution currentSolution = neighbour.getSolution();

            if (isAllowed(tabuList, globallyBestSolution, neighbour)) {
                if (bestNeighbour == null) {
                    bestNeighbour = neighbour;
                } else if(currentSolution.isBetterThan(bestNeighbour.getSolution())){
                    bestNeighbour = neighbour;
                }
            }

            aspirationThresholdReached |= (currentSolution.getDistance() <= aspirationThreshold);
            if (aspirationThresholdReached) {
                ++neighboursSinceAspirationSatisfied;
            }
            ++neighboursChecked;

            if (enoughSolutionsChecked(neighboursChecked, neighboursSinceAspirationSatisfied)) {
                break;
            }
        }

        return bestNeighbour;
    }

    private boolean isAllowed(final TabuList tabuList, final Solution globallyBestSolution,
                              final SolutionMove neighbour) {
        final Move move = neighbour.getMove();
        final Solution solution = neighbour.getSolution();
        return !tabuList.contains(move) || solution.isBetterThan(globallyBestSolution);
    }

    private boolean enoughSolutionsChecked(final int solutionsChecked, final int solutionsSinceAspirationSatisfied) {
        if (solutionsChecked > maxParameter) {
            return true;
        } else if (solutionsChecked < minParameter) {
            return false;
        } else if (solutionsSinceAspirationSatisfied < plusParameter) {
            return false;
        }
        return true;
    }
}
