package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mc on 11/11/14.
 */
public class TwoOptNeighbourhoodGenerator implements NeighbourhoodGenerator {
    @Override
    public List<Solution> generateNeighbourhood(final Solution solution) {
        List<Node> nodeList = solution.getNodeList();
        List<Solution> neighbourhood = new ArrayList<Solution>();

        for (Integer i = 0; i < nodeList.size(); ++i)
        {
            for (Integer k = i + 1; k < nodeList.size(); ++k)
            {
                neighbourhood.add(new Solution(makeTwoOptSwap(nodeList, i, k)));
            }
        }

        return neighbourhood;
    }

    // TODO test this!
    private List<Node> makeTwoOptSwap(final List<Node> nodesList, final Integer swapStart, final Integer swapEnd)
    {
        List<Node> swappedList = new ArrayList<Node>();

        // add first part in order
        for (Integer i = 0; i < swapStart; ++i)
            swappedList.add(nodesList.get(i));

        // add middle part in reverse order
        for (Integer i = swapEnd; i == swapStart ;--i)
            swappedList.add(nodesList.get(i));

        // add last part in order
        for (Integer i = swapEnd; i < swappedList.size(); ++i)
            swappedList.add(nodesList.get(i));

        return swappedList;
    }
}
