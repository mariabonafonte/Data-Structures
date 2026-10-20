
import arbolBinario.ArbolBinario.Node;
import arbolBinario.ArbolBinario;
import arbolBinario.ExamenArboles;

import java.util.Set;
import java.util.List;


public class EjemploUso {
    public static void main(String[] args) {
        //                               RaizPrincipal                                //Nivel 0
        //              RaizIzq                               RaizDer.                //Nivel 1
        //      RaizIzqIzq   RaizIzqDer                 RaizDerIzq     RaizDerDer     //Nivel 2
        //  null       null null      null            null     null  null     null


        // Creación de subárboles usando los constructores de ArbolBinario
        Node<String> subarbolIzq1 = Node.of("RaizIzqIzq", null, null);
        Node<String> subarbolDer1 = Node.of("RaizIzqDer", null, null);

        Node<String> subarbolIzq2 = Node.of("RaizDerIzq", null, null);
        Node<String> subarbolDer2 = Node.of("RaizDerDer", null, null);

        // Creación de árboles principales usando los constructores de ArbolBinario
        Node<String> arbol1 = Node.of("RaizIzq", subarbolIzq1, subarbolDer1);
        Node<String> arbol2 = Node.of("RaizDer", subarbolIzq2, subarbolDer2);

        // Creación del árbol principal usando el constructor de ArbolBinario
        Node<String> arbolPrincipal = Node.of("RaizPrincipal", arbol1, arbol2);

        // Obtener recorrido en preorden
        List<String> recorrido = ArbolBinario.preorder(arbolPrincipal);

        // Mostrar los nodos en preorden
        System.out.println("Recorrido en preorden del árbol:");
        for (String nodo : recorrido) {
            if (nodo != null) {
                System.out.println(nodo);
            } else {
                System.out.println("null");
            }
        }

        // Reconstruir el árbol desde el recorrido
        Node<String> nuevoArbol = ExamenArboles.reconstruirDesdeRecorrido(recorrido);

        // Mostrar el recorrido del árbol reconstruido
        System.out.println("\nRecorrido en preorden del árbol reconstruido:");
        List<String> recorridoReconstruido = ArbolBinario.preorder(nuevoArbol);
        for (String nodo : recorridoReconstruido) {
            if (nodo != null) {
                System.out.println(nodo);
            } else {
                System.out.println("null");
            }
        }
        
         // Obtener nodos en el nivel 1
        System.out.println("\nNodos en el nivel 1:");
        Set<String> nodosNivel1 = ExamenArboles.obtenerNodosEnNivel(1, arbolPrincipal);
        for (String nodo : nodosNivel1) {
            System.out.println(nodo);
        }

        // Obtener nodos en el nivel 2
        System.out.println("\nNodos en el nivel 2:");
        Set<String> nodosNivel2 = ExamenArboles.obtenerNodosEnNivel(2, arbolPrincipal);
        for (String nodo : nodosNivel2) {
            System.out.println(nodo);
        }
        
    }
}
