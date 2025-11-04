import java.util.*;

public class PrimAlgorithm {

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        long operations = 0;

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        Set<String> inMST = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        if (graph.getNodes().isEmpty()) {
            return new MSTResult(mstEdges, 0, 0, 0);
        }

        String startNode = graph.getNodes().iterator().next();
        inMST.add(startNode);

        for (Edge edge : graph.getAdj().get(startNode)) {
            pq.add(edge);
            operations++;
        }

        while (!pq.isEmpty() && mstEdges.size() < graph.getVertexCount() - 1) {
            Edge minEdge = pq.poll();
            operations++;

            String toNode = minEdge.to;
            String fromNode = minEdge.from;
            String nodeToAdd = null;

            if (inMST.contains(fromNode) && !inMST.contains(toNode)) {
                nodeToAdd = toNode;
            } else if (inMST.contains(toNode) && !inMST.contains(fromNode)) {
                nodeToAdd = fromNode;
            }

            operations++;
            if (nodeToAdd == null) {
                continue;
            }

            mstEdges.add(minEdge);
            totalCost += minEdge.weight;
            inMST.add(nodeToAdd);

            for (Edge neighborEdge : graph.getAdj().get(nodeToAdd)) {
                operations++;
                if (!inMST.contains(neighborEdge.to)) {
                    pq.add(neighborEdge);
                    operations++;
                }
            }
        }

        double timeMs = (System.nanoTime() - startTime) / 1_000_000.0;
        return new MSTResult(mstEdges, totalCost, operations, timeMs);
    }
}