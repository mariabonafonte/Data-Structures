package arbolBinario;

import java.util.*;


public class ArbolBinario {
    //NO TOCAR

    public static final class Node<E> {
        private final E element;
        private final Node<E> left;
        private final Node<E> right;

        public Node(E element) {
            this(element, null, null);
        }

        public E getElement() {
            return this.element;
        }

        public Node<E> getLeft() {
            return this.left;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public static <T> Node<T> of(T element) {
            return new Node(element);
        }

        public static <T> Node<T> of(T element, Node<T> left, Node<T> right) {
            return new Node(element, left, right);
        }
    }

    //Sólo para usar en el principal y las pruebas, está ofuscado
    public static <T> List<T> preorder(Node<T> root) {
        List<T> t = new ArrayList<>();
        nn(root, t);
        return t;
    }

    private static <T> void nn(Node<T> A, List<T> a) {
        if (A != null) {
            rr(A,a);
        }
    }

    private static <T> void rr(Node<T> b, List<T> B) {
        B.add(b.element);
        ll(b, B);
    }

    private static <T> void ll(Node<T> A, List<T> a) {
        nn(aXc(A), a);
        nn(axC(A), a);
    }

    private static <T> Node<T> aXc (Node<T> A){
        return A.left;
    }

    private static <T> Node<T> axC (Node<T> A){
        return A.right;
    }

}

