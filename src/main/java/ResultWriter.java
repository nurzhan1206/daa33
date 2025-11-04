import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

class OutputData {
    List<OutputResult> results = new ArrayList<>();
}
class OutputResult {
    int graph_id;
    InputStats input_stats;
    AlgorithmResult prim;
    AlgorithmResult kruskal;
}
class InputStats {
    int vertices;
    int edges;
}
class AlgorithmResult {
    List<OutputEdge> mst_edges;
    int total_cost;
    long operations_count;
    double execution_time_ms;
}
class OutputEdge {
    String from;
    String to;
    int weight;
    OutputEdge(Edge e) {
        this.from = e.from;
        this.to = e.to;
        this.weight = e.weight;
    }
}

public class ResultWriter {

    public static void writeResults(List<OutputResult> results, String filePath) {
        OutputData outputData = new OutputData();
        outputData.results = results;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(outputData, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AlgorithmResult convertToOutputFormat(MSTResult result) {
        AlgorithmResult out = new AlgorithmResult();
        out.total_cost = result.totalCost;
        out.operations_count = result.operationsCount;
        out.execution_time_ms = Math.round(result.executionTimeMs * 100.0) / 100.0;
        out.mst_edges = result.mstEdges.stream()
                .map(OutputEdge::new)
                .collect(Collectors.toList());
        return out;
    }
}