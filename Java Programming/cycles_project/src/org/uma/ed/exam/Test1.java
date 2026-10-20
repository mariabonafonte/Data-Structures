package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

public class Test1 {
    public static void main(String[] args) {
        // Graph 1: Two separate triangles (2 components)
        // Triangle 1: A-B, B-C, C-A
        // Triangle 2: D-E, E-F, F-D
        Graph<String> graph1 = DictionaryGraph.empty();
        graph1.addVertex("A"); graph1.addVertex("B"); graph1.addVertex("C");
        graph1.addVertex("D"); graph1.addVertex("E"); graph1.addVertex("F");

        graph1.addEdge("A", "B"); graph1.addEdge("B", "C"); graph1.addEdge("C", "A");
        graph1.addEdge("D", "E"); graph1.addEdge("E", "F"); graph1.addEdge("F", "D");

        System.out.println("Testing Graph 1 (2 Triangles)...");
        testGraph(graph1, 2);

        // Graph 2: Fully connected square (1 component)
        Graph<Integer> graph2 = DictionaryGraph.empty();
        graph2.addVertex(1); graph2.addVertex(2); graph2.addVertex(3); graph2.addVertex(4);
        graph2.addEdge(1, 2); graph2.addEdge(2, 3); graph2.addEdge(3, 4); graph2.addEdge(4, 1);

        System.out.println("\nTesting Graph 2 (1 Square)...");
        testGraph(graph2, 1);

        // Graph 3: 3 isolated vertices (3 components)
        Graph<String> graph3 = DictionaryGraph.empty();
        graph3.addVertex("X"); graph3.addVertex("Y"); graph3.addVertex("Z");

        System.out.println("\nTesting Graph 3 (3 Isolated points)...");
        testGraph(graph3, 3);
    }

    private static <V> void testGraph(Graph<V> graph, int expectedCount) {
        ConnectedComponents<V> cc = ConnectedComponents.of(graph);
        int actualCount = cc.count();

        System.out.println("Expected components: " + expectedCount);
        System.out.println("Actual components:   " + actualCount);
        System.out.println("Details: " + cc.components());

        if (expectedCount == actualCount) {
            System.out.println("RESULT: PASSED");
        } else {
            System.out.println("RESULT: FAILED");
        }
    }
}