package pl.edu.pw.elka.tabusearch.io.config;

public class ConfigArgumentsReader implements ConfigReader {
    private static final String DEFAULT_INPUT_FILE_NAME = "test/simple_graph.txt";

    private final String graphFilename;
    private final Config solverConfig;

    // not so pretty, but works...
    public ConfigArgumentsReader(final String[] args) {
        // default parameters and graph filename
        Integer min = 20, max = 40, plus = 5, tabuSize = 25;
        String graphFilename = DEFAULT_INPUT_FILE_NAME;

        for (Integer i = 0; i < args.length; ++i) {
            String argument = args[i];
            switch (argument) {
                case "-min":
                    min = new Integer(args[++i]);
                    break;
                case "-max":
                    max = new Integer(args[++i]);
                    break;
                case "-plus":
                    plus = new Integer(args[++i]);
                    break;
                case "-tabuSize":
                    tabuSize = new Integer(args[++i]);
                    break;
                case "-graph":
                    graphFilename = args[++i];
                    break;
            }
        }

        this.solverConfig = new Config(min, max, plus, tabuSize);
        this.graphFilename = graphFilename;
    }

    @Override
    public Config getSolverConfig() {
        return solverConfig;
    }

    @Override
    public String getGraphFilename() {
        return graphFilename;
    }
}
