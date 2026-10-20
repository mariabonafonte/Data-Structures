package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;

public class TestPlanar {
    public static void main(String[] args) {
        System.out.println("--- TEST PLANARITY CHECKS ---");

        // CASE 1: K5 (Complete Graph, 5 vertices)
        // V=5, E=10. Has Triangles.
        // Limit: 3(5) - 6 = 9.
        // 10 <= 9 is FALSE. (Correct: K5 is not planar)
        Graph<Integer> k5 = DictionaryGraph.empty();
        for(int i=1; i<=5; i++) k5.addVertex(i);
        k5.addEdge(1,2); k5.addEdge(1,3); k5.addEdge(1,4); k5.addEdge(1,5);
        k5.addEdge(2,3); k5.addEdge(2,4); k5.addEdge(2,5);
        k5.addEdge(3,4); k5.addEdge(3,5); k5.addEdge(4,5);

        test("K5 (Should be FALSE)", k5, false);

        // CASE 2: K3,3 (Bipartite Utility Graph)
        // V=6, E=9. NO Triangles.
        // Limit: 2(6) - 4 = 8.
        // 9 <= 8 is FALSE. (Correct: K3,3 is not planar)
        Graph<String> k33 = DictionaryGraph.empty();
        k33.addVertex("A1"); k33.addVertex("A2"); k33.addVertex("A3");
        k33.addVertex("B1"); k33.addVertex("B2"); k33.addVertex("B3");
        String[] groupA = {"A1", "A2", "A3"};
        String[] groupB = {"B1", "B2", "B3"};
        for(String a : groupA) {
            for(String b : groupB) k33.addEdge(a, b);
        }

        test("K3,3 (Should be FALSE)", k33, false);

        // CASE 3: Planar Triangle
        // V=3, E=3. Has Triangle.
        // Limit: 3(3) - 6 = 3.
        // 3 <= 3 is TRUE.
        Graph<String> tri = DictionaryGraph.empty();
        tri.addVertex("A"); tri.addVertex("B"); tri.addVertex("C");
        tri.addEdge("A", "B"); tri.addEdge("B", "C"); tri.addEdge("C", "A");

        test("Triangle (Should be TRUE)", tri, true);
    }

    private static <V> void test(String name, Graph<V> g, boolean expected) {
        boolean actual = PlanarityChecker.of(g).isPotentiallyPlanar();
        System.out.printf("Test %-25s | Expected: %-5s | Actual: %-5s | %s%n",
                name, expected, actual, (expected == actual ? "PASSED" : "FAILED"));
    }
}