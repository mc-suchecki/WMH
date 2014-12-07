package pl.edu.pw.elka.tabusearch.io.config;

import java.io.IOException;

public interface ConfigReader {
    Config getSolverConfig() throws IOException, NumberFormatException;
    String getGraphFilename() throws IOException;
}
