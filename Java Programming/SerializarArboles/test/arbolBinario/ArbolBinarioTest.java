package arbolBinario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;


import java.util.List;
import java.util.Arrays;
import java.util.Set;

import arbolBinario.ArbolBinario.Node;


public class ArbolBinarioTest {

    private Node<String> arbol;

    @BeforeEach
    public void setUp() {
        arbol = null;
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void testReconstruirArbolVacio() throws NoSuchFieldException, IllegalAccessException {
        List<String> recorridoVacio = Arrays.asList();
        arbol = ExamenArboles.reconstruirDesdeRecorrido(recorridoVacio);

        // Verificar que el árbol está vacío
        assertNull(arbol); // La raíz debe ser null si no hay nodos
    }

    @Test
    public void testReconstruirArbolConUnSoloElemento() throws NoSuchFieldException, IllegalAccessException {
        List<String> recorrido = Arrays.asList("A");
        Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);

        assertNotNull(raiz); // La raíz debe ser no null
        assertEquals("A", raiz.getElement()); // El valor de la raíz debe ser "A"
        assertNull(raiz.getLeft()); // No debe tener subárbol izquierdo
        assertNull(raiz.getRight()); // No debe tener subárbol derecho
    }

    @Test
    public void testReconstruirArbolConVariosElementos() throws NoSuchFieldException, IllegalAccessException {
        List<String> recorrido = Arrays.asList("A", "B", null, null, "C", null, null);
        Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);


        assertNotNull(raiz); // La raíz debe ser no null
        assertEquals("A", raiz.getElement()); // El valor de la raíz debe ser "A"
        assertNotNull(raiz.getLeft()); // Debe tener subárbol izquierdo
        assertEquals("B", raiz.getLeft().getElement()); // El valor del subárbol izquierdo debe ser "B"
        assertNull(raiz.getLeft().getLeft()); // El subárbol izquierdo de "B" debe ser null
        assertNull(raiz.getLeft().getRight()); // El subárbol derecho de "B" debe ser null
        assertNotNull(raiz.getRight()); // Debe tener subárbol derecho
        assertEquals("C", raiz.getRight().getElement()); // El valor del subárbol derecho debe ser "C"
        assertNull(raiz.getRight().getLeft()); // El subárbol izquierdo de "C" debe ser null
        assertNull(raiz.getRight().getRight()); // El subárbol derecho de "C" debe ser null
    }

    @Test
    public void testReconstruirArbolConNulosEnMedio() throws NoSuchFieldException, IllegalAccessException {
        List<String> recorrido = Arrays.asList("A", "B", null, "D", null, null, "C", null, null);
        Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);
        assertNotNull(raiz); // La raíz debe ser no null
        assertEquals("A", raiz.getElement()); // El valor de la raíz debe ser "A"
        assertNotNull(raiz.getLeft()); // Debe tener subárbol izquierdo
        assertEquals("B", raiz.getLeft().getElement()); // El valor del subárbol izquierdo debe ser "B"
        assertNull(raiz.getLeft().getLeft()); // El subárbol izquierdo de "B" debe ser null
        assertNotNull(raiz.getLeft().getRight()); // El subárbol derecho de "B" debe ser no null
        assertEquals("D", raiz.getLeft().getRight().getElement()); // El valor del subárbol derecho de "B" debe ser "D"
        assertNull(raiz.getLeft().getRight().getLeft()); // El subárbol izquierdo de "D" debe ser null
        assertNull(raiz.getLeft().getRight().getRight()); // El subárbol derecho de "D" debe ser null
        assertNotNull(raiz.getRight()); // Debe tener subárbol derecho
        assertEquals("C", raiz.getRight().getElement()); // El valor del subárbol derecho debe ser "C"
        assertNull(raiz.getRight().getLeft()); // El subárbol izquierdo de "C" debe ser null
        assertNull(raiz.getRight().getRight()); // El subárbol derecho de "C" debe ser null
    }
    
   @Test
    public void testReconstruirArbolComplejo() throws NoSuchFieldException, IllegalAccessException {
        // Recorrido: "A", "B", null, null, "C", null, "D", null, "E", null, null"
        // Representa el árbol:
        //       A
        //      / \
        //     B   C
        //          \
        //           D
        //            \
        //             E

        List<String> recorrido = Arrays.asList("A", "B", null, null, "C", null, "D", null, "E", null, null);
       Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);
        assertNotNull(raiz);
        assertEquals("A", raiz.getElement());
        assertNotNull(raiz.getLeft());
        assertEquals("B", raiz.getLeft().getElement());
        assertNull(raiz.getLeft().getLeft());
        assertNull(raiz.getLeft().getRight());
        assertNotNull(raiz.getRight());
        assertEquals("C", raiz.getRight().getElement());
        assertNotNull(raiz.getRight().getRight());
        assertEquals("D", raiz.getRight().getRight().getElement());
        assertNotNull(raiz.getRight().getRight().getRight());
        assertEquals("E", raiz.getRight().getRight().getRight().getElement());
        assertNull(raiz.getRight().getRight().getRight().getLeft());
        assertNull(raiz.getRight().getRight().getRight().getRight());
    }

    @Test
    public void testReconstruirArbolConNodosIntermedios() throws NoSuchFieldException, IllegalAccessException {
        // Recorrido: "A", "B", "D", null, null, "E", null, null, "C", null, "F", null, null
        // Representa el árbol:
        //       A
        //      / \
        //     B   C
        //    / \    \
        //   D   E    F

        List<String> recorrido = Arrays.asList("A", "B", "D", null, null, "E", null, null, "C", null, "F", null, null);
        Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);
        assertNotNull(raiz);
        assertEquals("A", raiz.getElement());
        assertNotNull(raiz.getLeft());
        assertEquals("B", raiz.getLeft().getElement());
        assertNotNull(raiz.getLeft().getLeft());
        assertEquals("D", raiz.getLeft().getLeft().getElement());
        assertNull(raiz.getLeft().getLeft().getLeft());
        assertNull(raiz.getLeft().getLeft().getRight());
        assertNotNull(raiz.getLeft().getRight());
        assertEquals("E", raiz.getLeft().getRight().getElement());
        assertNull(raiz.getLeft().getRight().getLeft());
        assertNull(raiz.getLeft().getRight().getRight());
        assertNotNull(raiz.getRight());
        assertEquals("C", raiz.getRight().getElement());
        assertNull(raiz.getRight().getLeft());
        assertNotNull(raiz.getRight().getRight());
        assertEquals("F", raiz.getRight().getRight().getElement());
        assertNull(raiz.getRight().getRight().getLeft());
        assertNull(raiz.getRight().getRight().getRight());
    }

    @Test
    public void testReconstruirArbolConNivelesProfundos() throws NoSuchFieldException, IllegalAccessException {
        // Recorrido: "A", "B", "C", null, "D", null, "E", null, null, null, null
        // Representa el árbol:
        //       A
        //      / 
        //     B
        //    /
        //   C
        //    \
        //     D
        //      \
        //       E

        List<String> recorrido = Arrays.asList("A", "B", "C", null, "D", null, "E", null, null, null, null);
        Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);
        assertNotNull(raiz);
        assertEquals("A", raiz.getElement());
        assertNotNull(raiz.getLeft());
        assertEquals("B", raiz.getLeft().getElement());
        assertNotNull(raiz.getLeft().getLeft());
        assertEquals("C", raiz.getLeft().getLeft().getElement());
        assertNull(raiz.getLeft().getLeft().getLeft());
        assertNotNull(raiz.getLeft().getLeft().getRight());
        assertEquals("D", raiz.getLeft().getLeft().getRight().getElement());
        assertNull(raiz.getLeft().getLeft().getRight().getLeft());
        assertNotNull(raiz.getLeft().getLeft().getRight().getRight());
        assertEquals("E", raiz.getLeft().getLeft().getRight().getRight().getElement());
        assertNull(raiz.getLeft().getLeft().getRight().getRight().getLeft());
        assertNull(raiz.getLeft().getLeft().getRight().getRight().getRight());
        assertNull(raiz.getRight());
    }

    @Test
    public void testReconstruirArbolConVariosNiveles() throws NoSuchFieldException, IllegalAccessException {
        // Recorrido: "A", "B", "C", null, "D", null, null, null, "E", null, null
        // Representa el árbol:
        //       A
        //      / \
        //     B   E
        //    /
        //   C
        //    \
        //     D

        List<String> recorrido = Arrays.asList("A", "B", "C", null, "D", null, null, null, "E", null, null);
        Node<String> raiz = ExamenArboles.reconstruirDesdeRecorrido(recorrido);
        assertNotNull(raiz);
        assertEquals("A", raiz.getElement());
        assertNotNull(raiz.getLeft());
        assertEquals("B", raiz.getLeft().getElement());
        assertNotNull(raiz.getLeft().getLeft());
        assertEquals("C", raiz.getLeft().getLeft().getElement());
        assertNull(raiz.getLeft().getLeft().getLeft());
        assertNotNull(raiz.getLeft().getLeft().getRight());
        assertEquals("D", raiz.getLeft().getLeft().getRight().getElement());
        assertNull(raiz.getLeft().getLeft().getRight().getLeft());
        assertNull(raiz.getLeft().getLeft().getRight().getRight());
        assertNotNull(raiz.getRight());
        assertEquals("E", raiz.getRight().getElement());
        assertNull(raiz.getRight().getLeft());
        assertNull(raiz.getRight().getRight());
    }    
    
  @Test
    public void testObtenerNodosEnNivelCero() {
        // Árbol: A
        Node<String>  raiz = Node.of("A");


        // Obtener los nodos en el nivel 0 (raíz)
        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(0,raiz);

        // Verificar que el conjunto contiene solo la raíz
        assertEquals(1, nodos.size());
        assertTrue(nodos.contains("A"));
    }

    @Test
    public void testObtenerNodosEnNivelUno() {
        // Árbol:      A
        //            /   \
        //           B     C
        Node<String> raiz = Node.of("A", Node.of("B"),Node.of("C"));


        // Obtener los nodos en el nivel 1 (hijos de la raíz)
        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(1, raiz);

        // Verificar que el conjunto contiene los nodos B y C
        assertEquals(2, nodos.size());
        assertTrue(nodos.contains("B"));
        assertTrue(nodos.contains("C"));
    }

    @Test
    public void testObtenerNodosEnNivelDos() {
        // Árbol:       A
        //             /   \
        //            B     C
        //           /       \
        //          D         E
        Node<String> raiz = Node.of("A", Node.of("B",Node.of("D"),null),Node.of("C",null, Node.of("E")));


        // Obtener los nodos en el nivel 2
        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(2, raiz);

        // Verificar que el conjunto contiene los nodos D y E
        assertEquals(2, nodos.size());
        assertTrue(nodos.contains("D"));
        assertTrue(nodos.contains("E"));
        
        // Obtener los nodos en el nivel 1 (hijos de la raíz)
        nodos = ExamenArboles.obtenerNodosEnNivel(1, raiz);

        // Verificar que el conjunto contiene los nodos B y C
        assertEquals(2, nodos.size());
        assertTrue(nodos.contains("B"));
        assertTrue(nodos.contains("C"));
    }

    @Test
    public void testObtenerNodosEnNivelDos2() {
        // Árbol:       A
        //             /   \
        //            B     C
        //           / \     \
        //          D   E     F

        Node<String> raiz = Node.of("A",
                Node.of("B",Node.of("D"),Node.of("E")),
                Node.of("C",null, Node.of("F")));



        // Obtener los nodos en el nivel 2
        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(2, raiz);

        // Verificar que el conjunto contiene los nodos D, E, y F
        assertEquals(3, nodos.size());
        assertTrue(nodos.contains("D"));
        assertTrue(nodos.contains("E"));
        assertTrue(nodos.contains("F"));
    }

    @Test
    public void testObtenerNodosEnNivelInexistente() {
        // Árbol:      A
        //            /   \
        //           B     C
        // Verificar el comportamiento para un nivel que no existe (nivel 3 en un árbol de profundidad 2)
        Node<String> raiz = Node.of("A",
                Node.of("B"),
                Node.of("C"));



        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(3, raiz);

        // Verificar que el conjunto está vacío
        assertTrue(nodos.isEmpty());
    }

    @Test
    public void testObtenerNodosEnNivelConUnSoloNodo() {
        // Árbol: A
        Node<String> raiz = Node.of("A");

        // Obtener los nodos en el nivel 1 (no debe haber nodos, ya que el árbol solo tiene raíz)
        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(1, raiz);

        // Verificar que el conjunto está vacío
        assertTrue(nodos.isEmpty());
    }

    @Test
    public void testObtenerNodosEnNivelConArbolGrande() {
        // Árbol grande: A -> B -> C -> D -> E -> F -> G
        Node<String> raiz = Node.of("A",Node.of("B",Node.of("C",Node.of("D",Node.of("E",Node.of("F",Node.of("G"),null),null),null),null),null),null);


        // Obtener los nodos en el nivel 5
        Set<String> nodos = ExamenArboles.obtenerNodosEnNivel(5, raiz);

        // Verificar que el conjunto contiene solo el nodo "F"
        assertEquals(1, nodos.size());
        assertTrue(nodos.contains("F"));
        
        // Obtener los nodos en el nivel 6
        nodos = ExamenArboles.obtenerNodosEnNivel(6,raiz );

        // Verificar que el conjunto contiene solo el nodo "G"
        assertEquals(1, nodos.size());
        assertTrue(nodos.contains("G"));
    }    

    
}

