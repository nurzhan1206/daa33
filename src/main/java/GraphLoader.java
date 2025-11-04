import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class InputData {
    List<InputGraph> graphs;
}
class InputGraph {
    int id;
    List<String> nodes;
    List<InputEdge> edges;
}
class InputEdge {
    String from;
    String to;
    int weight;
}

public class GraphLoader {

    public static List<Graph> loadGraphs(String filePath) {
        Gson gson = new Gson();
        List<Graph> graphList = new ArrayList<>();

        try (Reader reader = new FileReader(filePath)) {
            InputData inputData = gson.fromJson(reader, InputData.class);

            for (InputGraph inputGraph : inputData.graphs) {
                Graph g = new Graph(inputGraph.id);
                for (String node : inputGraph.nodes) {
                    g.addNode(node);
                }
                for (InputEdge edge : inputGraph.edges) {
                    g.addEdge(edge.from, edge.to, edge.weight);
                }
                graphList.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graphList;
    }
}