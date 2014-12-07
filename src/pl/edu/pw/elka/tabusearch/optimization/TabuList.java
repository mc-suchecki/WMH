package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Container for storing moves done during tabu search.
 */
class TabuList {
    private final Integer tabuListMaxSize;
    private Integer size;

    private final Set<Move> movesSet;
    private final LinkedList<Move> movesList;

    public TabuList(final Integer tabuListMaxSize) {
        this.tabuListMaxSize = tabuListMaxSize;
        this.movesSet = new HashSet<>(tabuListMaxSize);
        this.movesList = new LinkedList<>();
        this.size = 0;
    }

    public void add(final Move lastMove) {
        movesSet.add(lastMove);
        movesList.addFirst(lastMove);

        if(size.equals(tabuListMaxSize))
        {
            final Move moveToDelete = movesList.getLast();
            movesList.removeLast();
            movesSet.remove(moveToDelete);
        }
        else
        {
            ++size;
        }
    }

    public Boolean contains(final Move move) {
        return movesSet.contains(move);
    }
}
