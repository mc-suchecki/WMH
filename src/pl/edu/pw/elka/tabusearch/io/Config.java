package pl.edu.pw.elka.tabusearch.io;

public class Config
{
    // parameters for Aspiration Plus Strategy
    private final Integer minParameter;
    private final Integer maxParameter;
    private final Integer plusParameter;

    public Config(final Integer minParameter, final Integer maxParameter, final Integer plusParameter) {
        this.minParameter = minParameter;
        this.maxParameter = maxParameter;
        this.plusParameter = plusParameter;
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
}
