package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;

public class TestHomeo {
    public static void main(String[] args) {
        System.out.println("--- HOMEOMORPHISM CHECK ---");

        // CASE 1: Line vs Long Line (Homeomorphic)
        // A-B  vs  X-Y-Z-W
        // Both have 2 vertices of degree 1. The inner nodes (degree 2) are ignored.
        Graph<String> shortLine = DictionaryGraph.empty();
        shortLine.addVertex("A"); shortLine.addVertex("B");
        shortLine.addEdge("A", "B");

        Graph<String> longLine = DictionaryGraph.empty();
        longLine.addVertex("X"); longLine.addVertex("Y");
        longLine.addVertex("Z"); longLine.addVertex("W");
        longLine.addEdge("X", "Y"); longLine.addEdge("Y", "Z"); longLine.addEdge("Z", "W");

        test("Line vs Long Line", shortLine, longLine, true);


        // CASE 2: Triangle vs Square (Homeomorphic)
        // Triangle: 3 vertices of degree 2. (Profile is empty)
        // Square: 4 vertices of degree 2. (Profile is empty)
        // Empty == Empty -> True.
        Graph<String> triangle = DictionaryGraph.empty();
        triangle.addVertex("A"); triangle.addVertex("B"); triangle.addVertex("C");
        triangle.addEdge("A", "B"); triangle.addEdge("B", "C"); triangle.addEdge("C", "A");

        Graph<String> square = DictionaryGraph.empty();
        square.addVertex("1"); square.addVertex("2");
        square.addVertex("3"); square.addVertex("4");
        square.addEdge("1", "2"); square.addEdge("2", "3");
        square.addEdge("3", "4"); square.addEdge("4", "1");

        test("Triangle vs Square", triangle, square, true);


        // CASE 3: Star vs Line (NOT Homeomorphic)
        // Star: 1 vertex degree 3, 3 vertices degree 1.
        // Line: 2 vertices degree 1.
        Graph<String> star = DictionaryGraph.empty();
        star.addVertex("Center"); star.addVertex("1");
        star.addVertex("2"); star.addVertex("3");
        star.addEdge("Center", "1"); star.addEdge("Center", "2"); star.addEdge("Center", "3");

        test("Star vs Line", star, shortLine, false);
    }

    private static <V> void test(String name, Graph<V> g1, Graph<V> g2, boolean expected) {
        boolean actual = HomeomorphismHeuristic.arePotentiallyHomeomorphic(g1, g2);
        System.out.printf("Test %-20s | Expected: %-5s | Actual: %-5s | %s%n",
                name, expected, actual, (expected == actual ? "PASSED" : "FAILED"));
    }
}