package pl.edu.pw.elka.tabusearch.io.config;

public class ConfigArgumentsReader implements ConfigReader {
    private final String[] args;

    public ConfigArgumentsReader(String[] args) {
        this.args = args;
    }

    // not so pretty, but works...
    @Override
    public Config read() {
        Integer min = 20, max = 40, plus = 5, tabuSize = 25;
        for (Integer i = 0; i < args.length; ++i) {
            String argument = args[i];
            if (argument.equals("-min")) {
                min = new Integer(args[++i]);
            } else if (argument.equals("-max")) {
                max = new Integer(args[++i]);
            } else if (argument.equals("-plus")) {
                plus = new Integer(args[++i]);
            } else if (argument.equals("-tabuSize")) {
                tabuSize = new Integer(args[++i]);
            } else {
                continue;
            }
        }
        return new Config(min, max, plus, tabuSize);
    }
}
