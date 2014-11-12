package pl.edu.pw.elka.tabusearch.io;

import pl.edu.pw.elka.tabusearch.domain.Node;
import pl.edu.pw.elka.tabusearch.domain.Solution;

public class ConsoleSolutionPrinter implements SolutionPrinter {
    @Override
    public void print(final Solution graph) {
        final StringBuilder sb = new StringBuilder();
        for (final Node node : graph.getNodeList()) {
            sb.append(node.getLabel());
            sb.append(" ");
        }
    }
}
