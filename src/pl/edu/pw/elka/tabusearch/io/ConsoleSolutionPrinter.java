package pl.edu.pw.elka.tabusearch.io;

import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;

public class ConsoleSolutionPrinter implements SolutionPrinter {
    @Override
    public void print(final Solution solution) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Solution found: ");
        sb.append(solution.toString());
        sb.append("=> distance = " + solution.getDistance());
        System.out.println(sb);
    }
}
