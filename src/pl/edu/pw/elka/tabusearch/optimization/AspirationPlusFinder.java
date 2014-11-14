package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.List;

/**
 * Created by mc on 11/11/14.
 */
public class AspirationPlusFinder implements BestSolutionFinder {

    // parameters that define algorithm flow
    private final Integer minParameter;
    private final Integer maxParameter;
    private final Integer plusParameter;

    public AspirationPlusFinder(final Integer min, final Integer max, final Integer plus) {
        this.minParameter = min;
        this.maxParameter = max;
        this.plusParameter = plus;
    }

    // TODO test this
    @Override
    public Solution getBestSolution(final List<Solution> neighbourhood, final List<Solution> tabuList,
                                    final Integer aspiration) {
        Solution bestSolution = neighbourhood.get(0);
        Solution currentSolution = neighbourhood.get(1);
        Integer solutionsChecked = 1;
        Integer solutionsSinceAspirationSatisfied = 0;

        // TODO what with tabuList?
        do {
            if (currentSolution.getDistance() > bestSolution.getDistance()) {
                bestSolution = currentSolution;
            }
            if (currentSolution.getDistance() > aspiration) {
                ++solutionsSinceAspirationSatisfied;
            }
            currentSolution = neighbourhood.get(solutionsChecked);
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
}
