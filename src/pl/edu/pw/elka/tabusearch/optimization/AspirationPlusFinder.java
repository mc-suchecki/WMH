package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.NeighboursIterator;
import pl.edu.pw.elka.tabusearch.optimization.neighbourhood.TwoOptNeighbourhood;

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

    @Override
    public Solution getBestSolution(final TwoOptNeighbourhood neighbourhood, final TabuList tabuList,
                                    final Integer aspiration) {
        //TODO co jeśli będzie tylko jedno sąsiednie rozwiązanie (przypadek dla dwóch miast)
        //TODO przerobić kod tej metody tak, by nie używał iterator.current()
        final NeighboursIterator iterator = neighbourhood.iterator();

        Solution bestSolution = iterator.next().getSolution();
        this.bestMove = iterator.current().getMove();
        Solution currentSolution = iterator.next().getSolution();
        Integer solutionsChecked = 1;
        Integer solutionsSinceAspirationSatisfied = 0;

        do {
            if (currentSolution.getDistance() > bestSolution.getDistance()) {
                if (!tabuList.contains(iterator.current().getMove()) || (currentSolution.getDistance() >= aspiration)) {
                    bestSolution = currentSolution;
                    this.bestMove = iterator.current().getMove();
                }
            }
            if (bestSolution.getDistance() > aspiration) {
                ++solutionsSinceAspirationSatisfied;
            }
            currentSolution = iterator.next().getSolution();
            ++solutionsChecked;
        } while (!enoughSolutionsChecked(neighbourhood.size(), solutionsChecked, solutionsSinceAspirationSatisfied));

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
