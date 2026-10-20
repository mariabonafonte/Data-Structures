///////////////////////////////////////////////////////////////////////////////
// Student's name: [Your Name]
// Identity number:  [Your DNI or Passport Number] 
///////////////////////////////////////////////////////////////////////////////


package org.uma.ed.exam;


import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.graph.DictionaryGraph;
import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.stack.JDKStack;

import javax.xml.transform.Source;
import java.util.HashSet;
import java.util.Stack;

/**
 * This class is used to test if a given graph is bipartite using depth-first traversal. A bipartite graph is a graph
 * whose vertices can be divided into two disjoint sets such that every edge connects a vertex in the first set to one
 * in the second set.
 *
 * @param <V> The type of the vertices in the graph.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BipartiteExam<V> {
  public enum Color { Red, Blue;
    Color opposite() {
      return (this == Red) ? Blue : Red;
    }
  }

  private record Pair<V>(V vertex, Color color) {
    static <V> Pair<V> of(V vertex, Color color) {
      return new Pair<>(vertex, color);
    }
  }

  private boolean bipartite;
  private final Dictionary<V, Color> assignedColor;

  static <V> BipartiteExam<V> of(Graph<V> graph) {
    return new BipartiteExam<>(graph);
  }

    public BipartiteExam(Graph<V> graph) {
      bipartite = true;
      assignedColor = JDKHashDictionary.empty();

      if(!graph.isEmpty()) {

          V source = graph.vertices().iterator().next();
          JDKStack<Pair<V>> stack = JDKStack.of(new Pair<>(source, Color.Red));

          while (!stack.isEmpty() && bipartite) {
              Pair<V> p = stack.top();
              stack.pop();

              V v = p.vertex;
              Color c = p.color;

              if (!assignedColor.isDefinedAt(v)) {
                  assignedColor.insert(v, c);

                  for (V suc : graph.successors(v)) {
                      stack.push(new Pair<>(suc, c.opposite()));
                  }
              } else {
                  if (assignedColor.valueOf(v) != c) {
                      bipartite = false;
                  }else{
                      continue;
                  }
              }


          }
      }
    }

  public boolean isBipartite() {
    return bipartite;
  }

  public Dictionary<V, Color> assignedColor() {
    return bipartite ? assignedColor : null;
  }

  public static void main(String[] args) {
    Test.main(args);
  }
}

