import java.util.*;

public class Graph {
    private final int id;
    private final Set<String> nodes;
    private final List<Edge> allEdges;
    private final Map<String, List<Edge>> adj;

    public Graph(int id) {
        this.id = id;
        this.nodes = new HashSet<>();
        this.allEdges = new ArrayList<>();
        this.adj = new HashMap<>();
    }

    public void addNode(String node) {
        nodes.add(node);
        adj.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        Edge edge1 = new Edge(from, to, weight);
        Edge edge2 = new Edge(to, from, weight);

        allEdges.add(edge1);

        adj.get(from).add(edge1);
        adj.get(to).add(edge2);
    }

    public int getId() { return id; }
    public Set<String> getNodes() { return nodes; }
    public List<Edge> getAllEdges() { return allEdges; }
    public Map<String, List<Edge>> getAdj() { return adj; }

    public int getVertexCount() { return nodes.size(); }
    public int getEdgeCount() { return allEdges.size(); }
}