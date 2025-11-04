import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String inputFile = "data/ass_3_input.json";
        String outputFile = "data/ass_3_output.json";

        List<Graph> graphs = GraphLoader.loadGraphs(inputFile);
        List<OutputResult> allResults = new ArrayList<>();

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        System.out.println("Processing " + graphs.size() + " graphs...");

        for (Graph g : graphs) {
            InputStats stats = new InputStats();
            stats.vertices = g.getVertexCount();
            stats.edges = g.getEdgeCount();

            MSTResult primResult = prim.findMST(g);

            MSTResult kruskalResult = kruskal.findMST(g);

            OutputResult finalResult = new OutputResult();
            finalResult.graph_id = g.getId();
            finalResult.input_stats = stats;
            finalResult.prim = ResultWriter.convertToOutputFormat(primResult);
            finalResult.kruskal = ResultWriter.convertToOutputFormat(kruskalResult);

            allResults.add(finalResult);

            System.out.println("Graph " + g.getId() + " processed.");
            System.out.println("  Prim Total Cost: " + primResult.totalCost);
            System.out.println("  Kruskal Total Cost: " + kruskalResult.totalCost);
        }

        ResultWriter.writeResults(allResults, outputFile);
        System.out.println("Results saved to " + outputFile);
    }
}