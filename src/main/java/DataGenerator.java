import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static InputGraph generateGraph(int id, int minNodes, int maxNodes, double density) {
        Random rand = new Random();
        int numNodes = rand.nextInt(maxNodes - minNodes + 1) + minNodes;
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add("N" + i);
        }

        List<InputEdge> edges = new ArrayList<>();
        HashSet<String> addedEdges = new HashSet<>();
        int maxEdges = (int) (numNodes * (numNodes - 1) * 0.5 * density);

        for (int i = 0; i < maxEdges; i++) {
            String from = nodes.get(rand.nextInt(numNodes));
            String to = nodes.get(rand.nextInt(numNodes));
            if (from.equals(to)) continue;

            String edgeKey1 = from + "-" + to;
            String edgeKey2 = to + "-" + from;

            if (addedEdges.add(edgeKey1) && addedEdges.add(edgeKey2)) {
                int weight = rand.nextInt(100) + 1;

                InputEdge edge = new InputEdge();
                edge.from = from;
                edge.to = to;
                edge.weight = weight;
                edges.add(edge);
            }
        }

        InputGraph graph = new InputGraph();
        graph.id = id;
        graph.nodes = nodes;
        graph.edges = edges;
        return graph;
    }

    public static void main(String[] args) {
        InputData mediumData = new InputData();
        mediumData.graphs = new ArrayList<>();
        mediumData.graphs.add(generateGraph(3, 10, 15, 0.4));
        mediumData.graphs.add(generateGraph(4, 10, 15, 0.8));

        InputData largeData = new InputData();
        largeData.graphs = new ArrayList<>();
        largeData.graphs.add(generateGraph(5, 20, 30, 0.3));
        largeData.graphs.add(generateGraph(6, 20, 30, 0.7));

        saveToFile("data/medium_graph.json", mediumData);
        saveToFile("data/large_graph.json", largeData);

        System.out.println("Generated medium and large graph files.");
    }

    private static void saveToFile(String filename, InputData data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}