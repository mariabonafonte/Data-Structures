package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;

public class Test4 {
    public static void main(String[] args) {
        // CASE 1: Eulerian Cycle (Square)
        // A -- B
        // |    |
        // D -- C
        // All degrees are 2 (Even). Count(Odd) = 0. Connected. -> TRUE
        Graph<String> g1 = DictionaryGraph.empty();
        g1.addVertex("A"); g1.addVertex("B"); g1.addVertex("C"); g1.addVertex("D");
        g1.addEdge("A", "B"); g1.addEdge("B", "C"); g1.addEdge("C", "D"); g1.addEdge("D", "A");

        System.out.println("Test 1 (Square - Cycle): " + EulerianChecker.of(g1).isEulerian());

        // CASE 2: Eulerian Path (Line)
        // A -- B -- C -- D
        // Degrees: A(1), B(2), C(2), D(1). Count(Odd) = 2. Connected. -> TRUE
        Graph<String> g2 = DictionaryGraph.empty();
        g2.addVertex("A"); g2.addVertex("B"); g2.addVertex("C"); g2.addVertex("D");
        g2.addEdge("A", "B"); g2.addEdge("B", "C"); g2.addEdge("C", "D");

        System.out.println("Test 2 (Line - Path):    " + EulerianChecker.of(g2).isEulerian());

        // CASE 3: Not Eulerian (Star Shape)
        //      B
        //      |
        // A -- Center -- C
        // Degrees: A(1), B(1), C(1), Center(3). Count(Odd) = 4. -> FALSE
        Graph<String> g3 = DictionaryGraph.empty();
        g3.addVertex("A"); g3.addVertex("B"); g3.addVertex("C"); g3.addVertex("Center");
        g3.addEdge("Center", "A"); g3.addEdge("Center", "B"); g3.addEdge("Center", "C");

        System.out.println("Test 3 (Star - None):    " + EulerianChecker.of(g3).isEulerian());

        // CASE 4: Disconnected
        // A -- B    C -- D
        // Degrees: All 1. Count(Odd) = 4. Also disconnected. -> FALSE
        Graph<String> g4 = DictionaryGraph.empty();
        g4.addVertex("A"); g4.addVertex("B"); g4.addVertex("C"); g4.addVertex("D");
        g4.addEdge("A", "B"); g4.addEdge("C", "D");

        System.out.println("Test 4 (Disconnected):   " + EulerianChecker.of(g4).isEulerian());
    }
}