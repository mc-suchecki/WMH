package pl.edu.pw.elka.tabusearch.domain;

import pl.edu.pw.elka.tabusearch.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing Solution's neighbourhood - set of Solutions that are similar to each other.
 * @author mc
 */
//TODO make this lazy loading? - Jacek
public class Neighbourhood {
    private Integer size;
    private Integer index;
    private Move lastMove;

    private final List<Pair<Solution, Move>> solutionsAndMovesList = new ArrayList<>();

    public Neighbourhood() {
        this.index = -1;
        this.size = 0;
    }

    public void add(final Solution solution, final Move move) {
        this.size++;
        this.solutionsAndMovesList.add(new Pair<>(solution, move));
    }

    public Solution getNextSolution() {
        this.index++;
        this.lastMove = this.solutionsAndMovesList.get(this.index).getValue();
        return this.solutionsAndMovesList.get(this.index).getKey();
    }

    public Integer getSize() {
        return this.size;
    }

    public Move getLastMove() {
        return this.lastMove;
    }
}
