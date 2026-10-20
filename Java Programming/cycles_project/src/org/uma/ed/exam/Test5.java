package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;

public class Test5 {
    public static void main(String[] args) {
        System.out.println("--- TEST START ---");

        // CASE 1: The "Bow Tie" (No Hamiltonian Cycle)
        // A -- Center -- B
        // |      |       |
        // C -- Center -- D
        // The center is a "cut vertex". You must pass through it multiple times
        // to visit everyone, which is forbidden in Hamiltonian graphs.
        Graph<String> bowTie = DictionaryGraph.empty();
        bowTie.addVertex("Center");
        bowTie.addVertex("A"); bowTie.addVertex("B");
        bowTie.addVertex("C"); bowTie.addVertex("D");

        bowTie.addEdge("Center", "A"); bowTie.addEdge("Center", "C");
        bowTie.addEdge("A", "C"); // Left Triangle

        bowTie.addEdge("Center", "B"); bowTie.addEdge("Center", "D");
        bowTie.addEdge("B", "D"); // Right Triangle

        test("Bow Tie (No Cycle)", bowTie, false);


        // CASE 2: The House (Has Hamiltonian Cycle)
        // Roof (top) connected to Base (square)
        //      A
        //     / \
        //    B - C
        //    |   |
        //    D - E
        // Cycle: A -> B -> D -> E -> C -> A
        Graph<String> house = DictionaryGraph.empty();
        house.addVertex("A"); house.addVertex("B"); house.addVertex("C");
        house.addVertex("D"); house.addVertex("E");

        house.addEdge("A", "B"); house.addEdge("A", "C"); // Roof
        house.addEdge("B", "C");
        house.addEdge("B", "D"); house.addEdge("C", "E"); // Walls
        house.addEdge("D", "E"); // Floor

        test("House (Has Cycle)", house, true);

        // CASE 3: Complete Graph K4 (Has Cycle)
        // Every node connected to every other node.
        Graph<Integer> k4 = DictionaryGraph.empty();
        for(int i=1; i<=4; i++) k4.addVertex(i);
        k4.addEdge(1,2); k4.addEdge(1,3); k4.addEdge(1,4);
        k4.addEdge(2,3); k4.addEdge(2,4); k4.addEdge(3,4);

        test("K4 (Complete)", k4, true);

        System.out.println("--- TEST END ---");
    }

    private static <V> void test(String name, Graph<V> g, boolean expectSuccess) {
        HamiltonianFinder<V> finder = HamiltonianFinder.of(g);
        boolean found = (finder.getCycle() != null);

        System.out.printf("Test %-20s | Expected: %-5s | Actual: %-5s | %s%n",
                name, expectSuccess, found, (expectSuccess == found ? "PASSED" : "FAILED"));

        if (found) {
            System.out.println("   Cycle found: " + finder.getCycle());
        }
    }
}