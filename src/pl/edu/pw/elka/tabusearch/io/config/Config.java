package pl.edu.pw.elka.tabusearch.io.config;

public class Config {
    // parameters for Aspiration Plus Strategy
    private final Integer minParameter;
    private final Integer maxParameter;
    private final Integer plusParameter;

    // parameters for Tabu List
    private final Integer tabuListSize;

    public Config(final Integer minParameter, final Integer maxParameter, final Integer plusParameter,
                  final Integer tabuListSize) {
        this.minParameter = minParameter;
        this.maxParameter = maxParameter;
        this.plusParameter = plusParameter;
        this.tabuListSize = tabuListSize;
    }

    public Integer getMinParameter() {
        return minParameter;
    }

    public Integer getMaxParameter() {
        return maxParameter;
    }

    public Integer getPlusParameter() {
        return plusParameter;
    }

    public Integer getTabuListSize() {
        return tabuListSize;
    }
}
