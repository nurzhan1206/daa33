import java.util.*;

public class KruskalAlgorithm {

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        long operations = 0;

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        DisjointSet ds = new DisjointSet(graph.getNodes());

        List<Edge> allEdges = new ArrayList<>(graph.getAllEdges());
        Collections.sort(allEdges);
        operations += (long)(allEdges.size() * Math.log(allEdges.size()));

        for (Edge edge : allEdges) {
            operations++;
            if (ds.union(edge.from, edge.to)) {
                operations++;
                mstEdges.add(edge);
                totalCost += edge.weight;
            }

            if (mstEdges.size() == graph.getVertexCount() - 1) {
                break;
            }
        }

        double timeMs = (System.nanoTime() - startTime) / 1_000_000.0;
        return new MSTResult(mstEdges, totalCost, operations, timeMs);
    }
}