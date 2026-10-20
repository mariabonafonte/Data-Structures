package org.uma.ed.exam;

import org.uma.ed.datastructures.graph.Graph;
import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;

/**
 * Class to check necessary conditions for graph homeomorphism.
 * Checks if two graphs have the same number of vertices of specific degrees
 * (ignoring vertices of degree 2).
 */
public class HomeomorphismHeuristic {

    /**
     * Checks if g1 and g2 are potentially homeomorphic.
     */
    public static <V> boolean arePotentiallyHomeomorphic(Graph<V> g1, Graph<V> g2) {
        // 1. Calculate profile for Graph 1
        Dictionary<Integer, Integer> profile1 = getPrincipalProfile(g1);

        // 2. Calculate profile for Graph 2
        Dictionary<Integer, Integer> profile2 = getPrincipalProfile(g2);

        // 3. Compare the two dictionaries
        // Hint: The 'equals' method in AbstractDictionary checks if contents are identical.
        return profile1.equals(profile2);
    }

    /**
     * Helper method to build the degree frequency profile.
     * Key = Degree (e.g., 1, 3, 4...)
     * Value = How many vertices have that degree.
     * NOTE: Vertices with degree 2 are IGNORED.
     */
    private static <V> Dictionary<Integer, Integer> getPrincipalProfile(Graph<V> graph) {
        Dictionary<Integer, Integer> profile = JDKHashDictionary.empty();

        // TODO: Implement the counting logic
        // 1. Iterate over all vertices in the graph.
        for(V vertex: graph.vertices()){
            int degree = graph.degree(vertex);
            if(degree == 2){
                continue;
            }else{
               if(profile.valueOf(degree)!=null){
                   int a = profile.valueOf(degree);
                   profile.delete(degree);
                   profile.insert(degree, a +1);
               } else{
                   profile.insert(degree, 1);
               }
            }
        }
        // 2. Get the degree of the vertex.
        // 3. IF degree == 2:
        //      Skip it (continue).
        // 4. ELSE:
        //      Update the dictionary.
        //      - If 'degree' is already in dictionary, increment its value.
        //      - If 'degree' is not in dictionary, insert it with value 1.

        // Hint: Use profile.valueOf(degree) to get current count,
        // and profile.insert(degree, count) to update.

        return profile;
    }
}