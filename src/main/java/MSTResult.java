import java.util.List;

public class MSTResult {
    public final List<Edge> mstEdges;
    public final int totalCost;
    public final long operationsCount;
    public final double executionTimeMs;

    public MSTResult(List<Edge> mstEdges, int totalCost, long operationsCount, double executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }
}