package pl.edu.pw.elka.tabusearch;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Solution;
import pl.edu.pw.elka.tabusearch.io.ConsoleSolutionPrinter;
import pl.edu.pw.elka.tabusearch.io.GraphReader;
import pl.edu.pw.elka.tabusearch.io.SolutionPrinter;
import pl.edu.pw.elka.tabusearch.io.config.Config;
import pl.edu.pw.elka.tabusearch.io.config.ConfigArgumentsReader;
import pl.edu.pw.elka.tabusearch.io.config.ConfigFileReader;
import pl.edu.pw.elka.tabusearch.io.config.ConfigReader;
import pl.edu.pw.elka.tabusearch.optimization.Solver;
import pl.edu.pw.elka.tabusearch.optimization.TabuSearchSolver;

class TabuSearch {
    public static void main(final String[] args) {
        try {
            ConfigReader configReader;
            if (args.length > 0) {
                configReader = new ConfigArgumentsReader(args);
            } else {
                configReader = new ConfigFileReader();
            }
            final Config config = configReader.getSolverConfig();
            final String graphFilename = configReader.getGraphFilename();

            final GraphReader graphReader = new GraphReader(graphFilename);
            final Graph graph = graphReader.read();

            final Solver solver = new TabuSearchSolver(config);
            final Solution solution = solver.findSolution(graph);

            final SolutionPrinter solutionPrinter = new ConsoleSolutionPrinter();
            solutionPrinter.print(solution);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
