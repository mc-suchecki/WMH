package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.ArrayList;
import java.util.List;

public class TwoOptNeighbourhoodGenerator implements NeighbourhoodGenerator {
    @Override
    //TODO change result to iterable collection - Jacek
    public List<Solution> generateNeighbourhood(final Solution solution) {
        final List<Node> nodeList = solution.getNodesList();
        List<Solution> neighbourhood = new ArrayList<>();

        for (Integer i = 0; i < nodeList.size() - 1; ++i) {
            for (Integer k = i + 1; k < nodeList.size(); ++k) {
                neighbourhood.add(new Solution(makeTwoOptSwap(nodeList, i, k)));
            }
        }

        return neighbourhood;
    }

    @Override
    public Move getLastMove() {
        // TODO implement - Maciek
        return null;
    }

    private List<Node> makeTwoOptSwap(final List<Node> nodesList, final Integer swapStart, final Integer swapEnd) {
        List<Node> swappedList = new ArrayList<>();

        // add first part in order
        for (Integer i = 0; i < swapStart; ++i) {
            swappedList.add(nodesList.get(i));
        }

        // add middle part in reverse order
        for (Integer i = swapEnd; !i.equals(swapStart - 1); --i) {
            swappedList.add(nodesList.get(i));
        }

        // add last part in order
        for (Integer i = swapEnd + 1; i < nodesList.size(); ++i) {
            swappedList.add(nodesList.get(i));
        }

        return swappedList;
    }
}
