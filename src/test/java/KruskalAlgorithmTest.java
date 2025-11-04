import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KruskalAlgorithmTest {

    private Graph g1, g2;
    private KruskalAlgorithm kruskal;

    @BeforeEach
    void setUp() {
        kruskal = new KruskalAlgorithm();

        // Граф 1 из ass_3_input.json
        g1 = new Graph(1);
        g1.addNode("A"); g1.addNode("B"); g1.addNode("C"); g1.addNode("D"); g1.addNode("E");
        g1.addEdge("A", "B", 4);
        g1.addEdge("A", "C", 3);
        g1.addEdge("B", "C", 2);
        g1.addEdge("B", "D", 5);
        g1.addEdge("C", "D", 7);
        g1.addEdge("C", "E", 8);
        g1.addEdge("D", "E", 6);

        // Граф 2 из ass_3_input.json
        g2 = new Graph(2);
        g2.addNode("A"); g2.addNode("B"); g2.addNode("C"); g2.addNode("D");
        g2.addEdge("A", "B", 1);
        g2.addEdge("A", "C", 4);
        g2.addEdge("B", "C", 2);
        g2.addEdge("C", "D", 3);
        g2.addEdge("B", "D", 5);
    }

    @Test
    void testGraph1_Kruskal() {
        MSTResult result = kruskal.findMST(g1);
        // Проверяем стоимость и кол-во ребер V-1
        assertEquals(16, result.totalCost);
        assertEquals(4, result.mstEdges.size());
    }

    @Test
    void testGraph2_Kruskal() {
        MSTResult result = kruskal.findMST(g2);
        // Проверяем стоимость и кол-во ребер V-1
        assertEquals(6, result.totalCost);
        assertEquals(3, result.mstEdges.size());
    }
}