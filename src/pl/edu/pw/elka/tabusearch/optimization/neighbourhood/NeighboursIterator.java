/**
 * @author <a href="mailto:jacek.witkowski@gmail.com">Jacek Witkowski</a>
 * Created on 2014-11-2014.
 */
package pl.edu.pw.elka.tabusearch.optimization.neighbourhood;

import pl.edu.pw.elka.tabusearch.domain.Move;
import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeighboursIterator implements Iterator<SolutionMove> {
    private final List<Node> nodesList;
    private int i = 0;
    private int k = 1;
    private SolutionMove currSolutionMove;

    public NeighboursIterator(final List<Node> nodesList) {
        this.nodesList = nodesList;
    }

    @Override
    public boolean hasNext() {
        return i < nodesList.size() - 1;
    }

    @Override
    public SolutionMove next() {
        final Move move = new Move(i, k);
        final List<Node> nodeListSwapped = makeTwoOptSwap(nodesList, i, k);
        final Solution solution = new Solution(nodeListSwapped);

        currSolutionMove = new SolutionMove(move, solution);
        incrementCounters();

        return currSolutionMove;
    }

    public SolutionMove current() {
        return currSolutionMove;
    }

    private List<Node> makeTwoOptSwap(final List<Node> nodesList, final Integer swapStart, final Integer swapEnd) {
        final List<Node> swappedList = new ArrayList<>();

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

    private void incrementCounters() {
        ++k;
        if (k == nodesList.size()) {
            ++i;
            k = i + 1;
        }
    }
}
