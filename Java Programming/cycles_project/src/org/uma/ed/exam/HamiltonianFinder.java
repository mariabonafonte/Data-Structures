package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.set.JDKHashSet;

/**
 * Class to find a Hamiltonian Cycle in a graph using Backtracking.
 *
 * @param <V> Type of vertices in graph.
 */
public class HamiltonianFinder<V> {

    private final List<V> hamiltonianCycle;

    //// DO NOT EDIT CODE ABOVE THIS LINE

    //// BEGIN (A)
    public HamiltonianFinder(Graph<V> graph) {
        this.hamiltonianCycle = JDKArrayList.empty();

        // 1. Handle edge cases (empty graph)
        if(graph.isEmpty()){
            return;
        }

        // 2. We can start looking for the cycle from an arbitrary vertex
        // (If a cycle exists, it passes through all vertices, so starting point doesn't matter)
        V vertex = graph.vertices().iterator().next();


        // 3. We need a Set to keep track of vertices currently in our path (for O(1) lookup)
        // TODO

        // 4. Start the recursive backtracking
        // We add the start node to the path and mark it as visited
        // TODO

        // 5. If not found, clear the list to indicate failure (or return null logic)
        // TODO
    }

    /**
     * Recursive Backtracking method.
     *
     * @param graph The graph.
     * @param current The vertex we are currently at.
     * @param start The original starting vertex (to close the loop).
     * @param visitedInPath Set of vertices currently in the path.
     * @return true if a cycle is found, false otherwise.
     */
    private boolean backtrack(Graph<V> graph, V current, V start, Set<V> visitedInPath) {

        // BASE CASE: We have visited ALL vertices
        // TODO
        // Check if there is an edge from current back to start

        // RECURSIVE STEP: Try all neighbors
        // TODO
        // For each neighbor:
        //  - Check if it is not already in the path
        //  - Add it to the path
        //  - Mark it as visited
        //  - Recurse
        //  - If it fails, undo (backtrack)

        return false;
    }
    //// END (A)

    //// DO NOT EDIT CODE BELOW THIS LINE

    /**
     * Returns the Hamiltonian Cycle if found, or an empty list/null otherwise.
     */
    public List<V> getCycle() {
        return hamiltonianCycle.isEmpty() ? null : hamiltonianCycle;
    }

    /**
     * Factory method.
     */
    public static <V> HamiltonianFinder<V> of(Graph<V> graph) {
        return new HamiltonianFinder<>(graph);
    }
}