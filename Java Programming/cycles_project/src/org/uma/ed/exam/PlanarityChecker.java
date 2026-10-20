package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.Edge;
import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

import java.util.HashSet;

/**
 * Class to check necessary conditions for graph planarity.
 *
 * @param <V> Type of vertices in graph.
 */
public class PlanarityChecker<V> {

    private boolean satisfiesConditions;

    //// DO NOT EDIT CODE ABOVE THIS LINE

    //// BEGIN (A)
    public PlanarityChecker(Graph<V> graph) {
        // 1. Get basic counts
        int v = graph.numberOfVertices();
        int e = graph.numberOfEdges();

        // Base case: Small graphs (V < 3) are always planar
        if (v < 3) {
            this.satisfiesConditions = true;
            return;
        }

        // 2. DETECT TRIANGLES
        // TODO: Implement the logic to find if there is a triangle in the graph.
        boolean hasTriangle = false;

        // Instructions for Triangle Detection:
        // 1. Iterate over all vertices 'u' in the graph.
        while(!hasTriangle) {

            for (V u : graph.vertices()) {
                for (V vi : graph.successors(u)) {
                    for (V w : graph.successors(vi)) {
                        if (u != vi && w != u && w != vi) {
                            if (graph.successors(u).contains(w)) {
                                hasTriangle = true;
                            }
                        }
                    }
                }
            }
        }
        // 2. For each 'u', iterate over its neighbors 'v' (graph.successors(u)).
        // 3. For each 'v', iterate over ITS neighbors 'w' (graph.successors(v)).
        // 4. Check if 'w' is connected back to 'u' (and ensure u, v, w are distinct).
        //    If so, set hasTriangle = true and break the loops.

        // ... Your code here ...


        // 3. APPLY EULER'S INEQUALITIES
        // TODO: Update 'this.satisfiesConditions' based on the result of 'hasTriangle'.
        // If hasTriangle is true:  Check E <= 3V - 6
        // If hasTriangle is false: Check E <= 2V - 4

        // ... Your code here ...
        if(!hasTriangle) {
            if(e<= 2*v - 4){
                this.satisfiesConditions = true;
            }
        }else{
            if(e<=3*v - 6){
                this.satisfiesConditions = true;
            }
        }
    }
    //// END (A)

    //// DO NOT EDIT CODE BELOW THIS LINE

    /**
     * Returns true if the graph satisfies planarity conditions.
     */
    public boolean isPotentiallyPlanar() {
        return satisfiesConditions;
    }

    /**
     * Factory method.
     */
    public static <V> PlanarityChecker<V> of(Graph<V> graph) {
        return new PlanarityChecker<>(graph);
    }
}