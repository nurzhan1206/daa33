import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class DisjointSet {
    private Map<String, String> parent = new HashMap<>();

    public DisjointSet(Set<String> nodes) {
        for (String node : nodes) {
            parent.put(node, node);
        }
    }

    public String find(String node) {
        if (parent.get(node).equals(node)) {
            return node;
        }
        String root = find(parent.get(node));
        parent.put(node, root);
        return root;
    }

    public boolean union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);

        if (!root1.equals(root2)) {
            parent.put(root1, root2);
            return true;
        }
        return false;
    }
}