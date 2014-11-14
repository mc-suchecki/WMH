package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Neighbourhood;
import pl.edu.pw.elka.tabusearch.domain.Solution;

/**
 * Class implementing Aspiration Plus strategy to find best solution in the neighbourhood.
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

    // TODO test this - Maciek
    @Override
    public Solution getBestSolution(final Neighbourhood neighbourhood, final TabuList tabuList,
                                    final Integer aspiration) {
        Solution bestSolution = neighbourhood.getNextSolution();
        this.bestMove = neighbourhood.getLastMove();
        Solution currentSolution = neighbourhood.getNextSolution();
        Integer solutionsChecked = 1;
        Integer solutionsSinceAspirationSatisfied = 0;

        do {
            if (currentSolution.getDistance() > bestSolution.getDistance()) {
                // TODO use tabuList - Maciek
                if (!tabuList.contains(neighbourhood.getLastMove())) {
                    bestSolution = currentSolution;
                    this.bestMove = neighbourhood.getLastMove();
                }
            }
            if (currentSolution.getDistance() > aspiration) {
                ++solutionsSinceAspirationSatisfied;
            }
            currentSolution = neighbourhood.getNextSolution();
            ++solutionsChecked;
        } while (!enoughSolutionsChecked(neighbourhood.getSize(), solutionsChecked, solutionsSinceAspirationSatisfied));

        return bestSolution;
    }

    private Boolean enoughSolutionsChecked(final Integer neighbourhoodSize, final Integer solutionsChecked,
                                           final Integer solutionsSinceAspirationSatisfied) {
        if (neighbourhoodSize <= solutionsChecked) {
            return true;
        } else if (solutionsChecked < minParameter) {
            return false;
        } else if (solutionsChecked > maxParameter) {
            return true;
        } else if (solutionsChecked < solutionsSinceAspirationSatisfied + plusParameter) {
            return false;
        }
        return true;
    }

    @Override
    public Move getLastMove() {
        return this.bestMove;
    }

}
