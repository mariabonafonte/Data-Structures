package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.stack.Stack;
import org.uma.ed.datastructures.stack.JDKStack;

import java.util.HashSet;

/**
 * Class to identify connected components in a graph.
 * Each component is returned as a Set of vertices.
 */
public class ConnectedComponents<V> {

    // Stores the list of components (each component is a Set of vertices)
    private final List<Set<V>> components;

    //// BEGIN (A)
    public ConnectedComponents(Graph<V> graph) {
        // Initialize the list
        components = JDKArrayList.empty();

        // Use this set to keep track of ALL visited vertices across all components
        Set<V> globalVisited = JDKHashSet.empty();

        // TODO: Implement the algorithm here.
        // 1. Iterate over all vertices in the graph.
        // 2. If a vertex hasn't been visited, it's the start of a new component.
        // 3. Create a new Set for this component.
        // 4. Use a Stack (DFS) to find all reachable vertices and add them to the component.
        // 5. Add the finished component to the 'components' list.

        // Hint: The structure is very similar to Bipartite and CycleDetector.
       for( V v: graph.vertices() ) {

           if( !globalVisited.contains(v) ) {
               globalVisited.insert(v);
               Set<V> newComp = JDKHashSet.empty();
               Stack<V> stack = JDKStack.of(v);

               while(!stack.isEmpty()) {
                   V vertex = stack.top();
                   stack.pop();
                   newComp.insert(vertex);
                   for( V suc: graph.successors(vertex) ) {
                       if( !globalVisited.contains(suc) ) {
                           globalVisited.insert(suc);
                           stack.push(suc);
                       }
                   }
               }

               components.append(newComp);

           }
       }

    }
    //// END (A)

    // Returns the list of connected components
    public List<Set<V>> components() {
        return components;
    }

    // Returns the number of connected components
    public int count() {
        return components.size();
    }

    public static <V> ConnectedComponents<V> of(Graph<V> graph) {
        return new ConnectedComponents<>(graph);
    }
}