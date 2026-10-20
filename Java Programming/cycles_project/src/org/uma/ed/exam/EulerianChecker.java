package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.stack.Stack;
import org.uma.ed.datastructures.stack.JDKStack;

/**
 * Class to check if a graph contains an Eulerian Path or Cycle.
 *
 * @param <V> Type of vertices in graph.
 */
public class EulerianChecker<V> {

    private boolean hasEulerianPath;

    //// DO NOT EDIT CODE ABOVE THIS LINE

    //// BEGIN (A)
    public EulerianChecker(Graph<V> graph) {
        this.hasEulerianPath = false;

        // Step 1: Count vertices with Odd Degrees
        int oddDegreeCount = 0;
        // TODO: Iterate over graph.vertices() and check graph.degree(v).
        // If oddDegreeCount is not 0 and not 2, we can stop early (it's false).
        int countedEdges = 0;
        V startVertex = null;

        if(graph.isEmpty()) hasEulerianPath = true;

        for(V vertex: graph.vertices()){
            if(oddDegreeCount !=0 || oddDegreeCount !=2) return;

            if(graph.degree(vertex)>0) {
                countedEdges++;
                if (startVertex == null) startVertex = vertex;
                if (graph.degree(vertex) % 2 == 0) {
                    oddDegreeCount++;
                }
            }
        }
        // Step 2: Check Connectivity
        // We must ensure all vertices with edges are in the same component.
        // Logic Hint:
        // a. Pick any vertex with degree > 0 as a starting point.
        // b. Run a DFS (using JDKStack) to count how many vertices you can reach.
        // c. Compare that count with the total number of vertices that have degree > 0.

        Set<V> visited = JDKHashSet.empty();
        // ... Your Stack logic here ...
        boolean connected = true;
        if(startVertex != null){
            int reachedCount = 0;
            Stack<V> stack = JDKStack.empty();

            stack.push(startVertex);

            while(!stack.isEmpty()){
                V u = stack.top();
                stack.pop();

                if(!visited.contains(u)){
                    visited.insert(u);
                    reachedCount++;
                    for(V suc: graph.successors(u)){
                        stack.push(suc);
                    }
                };
                connected = (reachedCount == countedEdges);
            }
            this.hasEulerianPath = connected && (oddDegreeCount == 0 || oddDegreeCount == 2);
        }




        // Step 3: Final Decision
        //hasEulerianPath = (connectivityOK) && (oddDegreeCount == 0 || oddDegreeCount == 2);
    }
    //// END (A)

    //// DO NOT EDIT CODE BELOW THIS LINE

    /**
     * Returns true if the graph has an Eulerian Path or Cycle.
     */
    public boolean isEulerian() {
        return hasEulerianPath;
    }

    /**
     * Factory method.
     */
    public static <V> EulerianChecker<V> of(Graph<V> graph) {
        return new EulerianChecker<>(graph);
    }
}