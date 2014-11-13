package pl.edu.pw.elka.tabusearch.io;

import pl.edu.pw.elka.tabusearch.domain.Edge;
import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphReader {
    private static final String INPUT_FILE_NAME = "input_graph.txt";
    public static final String LINE_FORMAT_MESSAGE =
            "Each line should have the following format: LABEL LABEL WEIGHT(integer)";

    private Map<String, Node> nodeMap;
    private Set<Edge> edgeSet;

    public Graph read() throws IOException, MultipleInvocationException, InvalidDataFormatException {
        initReader();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                processLine(strLine);
            }
        }

        checkNodesSize();
        return new Graph(nodeMap, edgeSet);
    }

    private void checkNodesSize() throws InvalidDataFormatException {
        final int actualSize = edgeSet.size();
        final int expectedSize = getExpectedEdgesNumber(nodeMap);
        if (actualSize != expectedSize) {
            throw new InvalidDataFormatException(
                    "Wrong number of edges. Expected: " + expectedSize + ", actual: " + actualSize);
        }
    }

    private int getExpectedEdgesNumber(final Map<String, Node> nodeMap) {
        final int nodesNumber = nodeMap.size();
        // the number below will always be an integer, because the product
        // of two following integer numbers is always dividable by two
        return (int) (nodesNumber * (nodesNumber - 1) * 0.5);
    }

    private void initReader() throws MultipleInvocationException {
        if (nodeMap != null || edgeSet != null) {
            throw new MultipleInvocationException();
        } else {
            nodeMap = new HashMap<>();
            edgeSet = new HashSet<>();
        }
    }

    private void processLine(final String strLine)
            throws InvalidDataFormatException {
        final String[] tokens = strLine.split(" ");
        if (tokens.length != 3) {
            throw new InvalidDataFormatException(LINE_FORMAT_MESSAGE);
        }

        final Node firstNode = getOrCreateNode(tokens[0]);
        final Node secondNode = getOrCreateNode(tokens[1]);
        final Integer weight = Integer.parseInt(tokens[2]);

        edgeSet.add(new Edge(firstNode, secondNode, weight));
    }

    private Node getOrCreateNode(final String startNodeLabel) {
        Node startNode = nodeMap.get(startNodeLabel);
        if (startNode == null) {
            startNode = new Node(startNodeLabel);
            nodeMap.put(startNodeLabel, startNode);
        }

        return startNode;
    }
}
