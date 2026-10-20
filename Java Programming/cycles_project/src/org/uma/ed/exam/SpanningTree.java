package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.graph.Edge;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.stack.Stack;
import org.uma.ed.datastructures.stack.JDKStack;

/**
 * Class to compute a Spanning Tree (or Forest) of a graph using DFS.
 */
public class SpanningTree<V> {

    // This set will hold the edges that make up the Spanning Tree
    private final Set<Edge<V>> treeEdges;

    // Helper record to store the Current vertex and the Parent we came from
    private record Pair<V>(V current, V parent) {}

    //// BEGIN (A)
    public SpanningTree(Graph<V> graph) {
        // 1. Initialize the result set
        treeEdges = JDKHashSet.empty();

        // 2. Set to keep track of visited vertices
        Set<V> visited = JDKHashSet.empty();

        // TODO: Implement the algorithm.
        // Logic Hint:
        // - Loop through all vertices (to handle disconnected graphs).
        // - Use a Stack<Pair<V>>.
        // - When you visit a node 'current' that is NOT in 'visited':
        //     a. Mark it as visited.
        //     b. If 'parent' is not null, create an Edge(parent, current)
        //        and add it to 'treeEdges'.
        //     c. Push neighbors to stack (neighbor, current).

        // ... Your code here ...
        V current = graph.vertices().iterator().next();

        Stack<Pair<V>> stack = JDKStack.of(new Pair<>(current, null));

        while (!stack.isEmpty()) {
            Pair<V> v = stack.top();
            stack.pop();
            V ver = v.current;
            V parent = v.parent;

            if(!visited.contains(ver)){
                visited.insert(ver);

                if(parent != null) {
                    treeEdges.insert(new Edge<>(ver, parent));
                }

                for(V suc: graph.successors(ver)){
                    stack.push(new Pair<>(suc, ver));
                }

            };

        }


    }
    //// END (A)

    /**
     * Returns the set of edges representing the Spanning Tree.
     */
    public Set<Edge<V>> edges() {
        return treeEdges;
    }

    public static <V> SpanningTree<V> of(Graph<V> graph) {
        return new SpanningTree<>(graph);
    }
}