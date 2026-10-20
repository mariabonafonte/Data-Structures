package arbolBinario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExamenArboles {
    /** Método para reconstruir el árbol a partir de una lista de vértices V
     * @param recorrido Lista con el recorrido en preorden, incluido los hijos null de las hojas.
     * @return Raiz del árbol binario que tiene el recorrido en preorden dado.
     */
    public static <V> ArbolBinario.Node<V> reconstruirDesdeRecorrido(List<V> recorrido) {
        if(recorrido.isEmpty()|| recorrido==null){
            return null;
        }
        Iterator<V> iterator = recorrido.iterator();

        return reconstruirSubarbol(iterator);
    }

    /**
     * Método privado SUGERIDO de apoyo para reconstruir un subárbol recursivamente.
     * @param iterador Iterador que recorre la lista dada en el método reconstruirDesdeRecorrido.
     * @return Raiz del árbol binario que tiene el recorrido en preorden de los elementos del iterador..
     */
    private static <V> ArbolBinario.Node<V> reconstruirSubarbol(Iterator<V> iterador) {
        if(!iterador.hasNext()){
            return null;
        }
        V valor = iterador.next();
        if(valor ==null){
            return null;
        }
        ArbolBinario.Node<V> izquierdo = reconstruirSubarbol(iterador);
        ArbolBinario.Node<V> derecho = reconstruirSubarbol(iterador);;

        return ArbolBinario.Node.of(valor, izquierdo, derecho);
    }

    /**
     * Método para obtener los nodos en un nivel específico
     * @param nivel VAlor entre 0 y N-1 (siendo N la áltura del arbol).
     * @param raiz Raiz del árbol con el nivel a buscar.
     * @return Conjunto de vértices V que están al nivel dado en el grafo.
     */
    //
    public static <V> Set<V> obtenerNodosEnNivel(int nivel, ArbolBinario.Node<V> raiz) {
        Set<V> resultado = new HashSet<>();

            if (raiz == null) {
                return resultado;
            }

            if (nivel == 0) {
                resultado.add(raiz.getElement());
                return resultado;
            }

            if (nivel > 0) {
                Set<V> nodosIzquierda = obtenerNodosEnNivel(nivel - 1, raiz.getLeft());
                resultado.addAll(nodosIzquierda);

                Set<V> nodosDerecha = obtenerNodosEnNivel(nivel - 1, raiz.getRight());
                resultado.addAll(nodosDerecha);
            }

            return resultado;
    }
}
