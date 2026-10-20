
package org.uma.ed.exam;

import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.stack.JDKStack;

import java.util.Stack;

/**
 * Class to detect cycles in a graph.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class CycleDetector<V> {
  // A record to represent a pair of vertices in the stack
  private record Pair<V>(V predecessor, V current) {
    // Factory method
    static <V> Pair<V> of(V predecessor, V current) { // factory method
      return new Pair<>(predecessor, current);
    }
  }

  private boolean cyclic;
  private final Dictionary<V, V> predecessors;
  private final List<V> cycle;

  //// DO NOT EDIT ANY CODE ABOVE

  //// BEGIN (A)
  public CycleDetector(Graph<V> graph) {
      cycle = JDKArrayList.empty();
      predecessors = JDKHashDictionary.empty(); // vertice -> predecesor
      Set<V> visited = JDKHashSet.empty();
      cyclic = false;

      if(graph.isEmpty()){
         cyclic = true;

      }else{
          cyclic = false;
          V source = graph.vertices().iterator().next();
          Pair<V> vertex = new Pair<>(source, source);

        JDKStack<Pair<V>> stack1 = new JDKStack<>();
        stack1.push(vertex);

         while(!stack1.isEmpty() && !cyclic) {
             Pair<V> pair = stack1.top();
             stack1.pop();
             V predecessor = pair.predecessor;
             V current = pair.current;

             if (visited.contains(current)) {
                 cyclic = true;
                 reconstructCycle(predecessor, current);
             }else{
                 visited.insert(current);
                 predecessors.insert(current, predecessor);

                 for (V succ : graph.successors(current)) {
                     if (!succ.equals(predecessor)) {
                         Pair<V> newVertex = new Pair<>(current, succ);
                         stack1.push(newVertex);
                     }
                 }
             }
         }
      }
      // Complete the algorithm here
  }
  //// END (A)

  //// BEGIN (B)
  // Reconstructs a cycle from the first vertex revisited and its predecessor. Stores it in cycle list
  private void reconstructCycle(V predecessor, V revisited){
      cycle.clear();
      cycle.append(revisited);
      while(predecessor != revisited){
          cycle.append(predecessor);
          predecessor = predecessors.valueOf(predecessor);
      }
      cycle.append(revisited);

      }
  //// END (B)

  //// DO NOT EDIT ANY CODE BELOW

  // Factory method
  public static <V> CycleDetector<V> of(Graph<V> graph) {  // factory method
    return new CycleDetector<>(graph);
  }

  // Returns true if graph is cyclic
  public boolean isCyclic() {
    return cyclic;
  }

  // Returns the cycle detected in graph, if any
  public List<V> cycle() {
    return cyclic ? cycle : null;
  }

  public static void main(String[] args) {
    Test.main(args);
  }
}



