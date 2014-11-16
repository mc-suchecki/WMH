package pl.edu.pw.elka.tabusearch.io;

import pl.edu.pw.elka.tabusearch.domain.Graph;
import pl.edu.pw.elka.tabusearch.domain.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GraphReader {
    private static final String INPUT_FILE_NAME = "test/input_graph.txt";
    public static final String LINE_FORMAT_MESSAGE =
            "Each line should have the following format: LABEL LABEL WEIGHT(integer)";

    private Map<String, Node> nodeMap;
    private Integer edgesCount;

    public Graph read() throws IOException, MultipleInvocationException, InvalidDataFormatException {
        initReader();

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                processLine(strLine);
            }
        }

        checkNodesSize();
        return new Graph(new HashSet<>(nodeMap.values()));
    }

    private void checkNodesSize() throws InvalidDataFormatException {
        final int expectedEdgesCount = getExpectedEdgesCount(nodeMap);
        if (edgesCount != expectedEdgesCount ) {
            throw new InvalidDataFormatException(
                    "Wrong number of edges. Expected: " + expectedEdgesCount + ", actual: " + edgesCount);
        }
    }

    private int getExpectedEdgesCount(final Map<String, Node> nodeMap) {
        final int nodesCount = nodeMap.size();
        // the number below will always be an integer, because the product
        // of two following integer numbers is always dividable by two
        return (int) (nodesCount * (nodesCount - 1) * 0.5);
    }

    private void initReader() throws MultipleInvocationException {
        if (nodeMap != null || edgesCount != null) {
            throw new MultipleInvocationException();
        } else {
            nodeMap = new HashMap<>();
            edgesCount = 0;
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

        firstNode.addConnection(secondNode.getLabel(), weight);
        secondNode.addConnection(firstNode.getLabel(), weight);
        ++edgesCount;
    }

    private Node getOrCreateNode(final String nodeLabel) {
        Node node = nodeMap.get(nodeLabel);
        if (null == node) {
            node = new Node(nodeLabel);
            nodeMap.put(nodeLabel, node);
        }
        return node;
    }
}
