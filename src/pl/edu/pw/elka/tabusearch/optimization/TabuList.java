package pl.edu.pw.elka.tabusearch.optimization;

import pl.edu.pw.elka.tabusearch.domain.Move;

import java.util.*;

/**
 * Container for storing moves done during tabu search.
 */
class TabuList {
    private final Integer tabuListMaxSize;
    private Integer size;

    private final Set<Move> movesSet;

    public TabuList(final Integer tabuListMaxSize) {
        this.tabuListMaxSize = tabuListMaxSize;
        this.movesSet = new LinkedHashSet<>(tabuListMaxSize);
        this.size = 0;
    }

    public void add(final Move move) {
        //move can be reinserted despite being on the tabu list when aspiration criterion is satisfied
        if (movesSet.contains(move)) {
            movesSet.remove(move);
            --size;
        }

        movesSet.add(move);

        if (size.equals(tabuListMaxSize + 1)) {
            removeEldestMove();
        } else {
            ++size;
        }
    }

    public Boolean contains(final Move move) {
        return movesSet.contains(move);
    }

    private void removeEldestMove() {
        //position iterator at first (eldest) move
        final Iterator<Move> iterator = movesSet.iterator();
        iterator.next();

        //and remove it
        iterator.remove();
    }
}
