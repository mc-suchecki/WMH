package pl.edu.pw.elka.tabusearch;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.io.*;
import pl.edu.pw.elka.tabusearch.optimization.Solver;
import pl.edu.pw.elka.tabusearch.optimization.TabuSearchSolver;

public class TabuSearch {
    public static void main(final String[] args) {
        try {
            final GraphReader graphReader = new GraphReader();
            final Graph graph = graphReader.read();

            final ConfigReader configReader = new ConfigReader();
            final Config config = configReader.read();

            final Solver solver = new TabuSearchSolver(config);
            final Solution solution = solver.findSolution(graph);

            final SolutionPrinter solutionPrinter = new ConsoleSolutionPrinter();
            solutionPrinter.print(solution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
