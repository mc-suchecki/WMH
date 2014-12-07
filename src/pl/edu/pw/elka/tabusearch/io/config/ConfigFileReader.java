package pl.edu.pw.elka.tabusearch.io.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader implements ConfigReader {
    private static final String CONFIG_FILE_NAME = "config.properties";

    @Override
    public Config getSolverConfig() throws IOException, NumberFormatException {
        final Properties properties = loadProperties();
        final Integer minParameter = Integer.parseInt(properties.getProperty("minParameter"));
        final Integer maxParameter = Integer.parseInt(properties.getProperty("maxParameter"));
        final Integer plusParameter = Integer.parseInt(properties.getProperty("plusParameter"));
        final Integer tabuListSize = Integer.parseInt(properties.getProperty("tabuListSize"));

        return new Config(minParameter, maxParameter, plusParameter, tabuListSize);
    }

    @Override
    public String getGraphFilename() throws IOException {
        final Properties properties = loadProperties();
        return properties.getProperty("graphFilename");
    }

    private Properties loadProperties() throws IOException {
        final Properties prop = new Properties();
        try (final BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE_NAME))) {
            prop.load(br);
        }

        return prop;
    }
}
