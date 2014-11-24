package pl.edu.pw.elka.tabusearch.io.config;

import java.io.IOException;

public interface ConfigReader {
    Config read() throws IOException, NumberFormatException;
}
