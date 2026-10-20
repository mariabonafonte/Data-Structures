package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;

public class TestSpanning {
    public static void main(String[] args) {
        // Create a square graph with a diagonal
        // A -- B
        // | \  |
        // D -- C
        // This graph has cycles (A-B-C-D-A) and (A-C-D-A).

        Graph<String> graph = DictionaryGraph.empty();
        graph.addVertex("A"); graph.addVertex("B");
        graph.addVertex("C"); graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "A");
        graph.addEdge("A", "C"); // The diagonal

        System.out.println("Graph Edges: " + graph.numberOfEdges()); // Should be 5

        // Compute Spanning Tree
        SpanningTree<String> st = SpanningTree.of(graph);

        System.out.println("Spanning Tree Edges: " + st.edges().size());
        System.out.println("Edges: " + st.edges());

        // Verification:
        // A spanning tree for 4 vertices MUST have exactly 3 edges (V-1).
        if (st.edges().size() == 3) {
            System.out.println("RESULT: PASSED (Correct number of edges)");
        } else {
            System.out.println("RESULT: FAILED");
        }
    }
}