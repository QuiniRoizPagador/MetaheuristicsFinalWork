/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structureData.binaryTree.impl;

import es.geneticalgorithm.model.structureData.binaryTree.SearchBinaryTree;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import es.geneticalgorithm.model.structureData.binaryTree.BinaryNode;
import es.geneticalgorithm.model.structureData.binaryTree.BlackRedNode;

/**
 * Clase que representa la implementación de un árbol binario de búsquedas con
 * sus algoritmos básicos
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public class SearchBinaryTreeImpl<E extends Comparable> implements SearchBinaryTree<E> {

    private BinaryNode<E> root;
    protected int size;

    public SearchBinaryTreeImpl(E first) {
        BinaryNode<E> firstNode = new BinaryNodeImpl<>(first);
        root = firstNode;
    }

    public SearchBinaryTreeImpl() {

    }

    protected void setRoot(BinaryNode<E> root) {
        this.root = root;
        if (root != null) {
            this.root.setParent(null);
        }
    }

    /**
     * Devuelve el nodo root
     *
     * @return Referencia IBinaryNode del root
     */
    protected BinaryNode<E> getRoot() {
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getHeight() {
        return maxDepth(root);
    }

    // busqueda de altura
    private int maxDepth(BinaryNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = maxDepth(node.getLeft());
            int rDepth = maxDepth(node.getRight());

            if (lDepth > rDepth) {
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator elements() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        List<E> elements = new ArrayList();
        visitElementsInOrder(root, elements);
        return elements.iterator();
    }

    private void visitElementsInOrder(BinaryNode<E> node, List<E> elements) {
        if (node.hasLeft()) {
            visitElementsInOrder(node.getLeft(), elements);
        }
        elements.add(node.getElement());
        if (node.hasRight()) {
            visitElementsInOrder(node.getRight(), elements);
        }
    }

    @Override
    public Iterator nodes() {
        List<BinaryNode<E>> nodes = new ArrayList<>();
        visitNodesInOrder(root, nodes);
        return nodes.iterator();
    }

    // las lecturas en el árbol binario de búsqueda se realizan inorder
    private void visitNodesInOrder(BinaryNode<E> node, List<BinaryNode<E>> nodes) {
        if (node.hasLeft()) {
            visitNodesInOrder(node.getLeft(), nodes);
        }
        nodes.add(node);
        if (node.hasRight()) {
            visitNodesInOrder(node.getRight(), nodes);
        }
    }

    @Override
    public E root() {
        return root.getElement();
    }

    @Override
    public void insert(E element) {
        BinaryNode<E> node = new BinaryNodeImpl<>(element);
        setRoot(insert(root, node));
        size++;
    }

    /**
     * Se insertará el nodo recursivamente
     *
     * @param node Nodo anterior
     * @param element elemento a añadir
     * @return Devolvemos el previo al nodo añadido recursivamente, al final de
     * la iteración será root
     */
    protected BinaryNode<E> insert(BinaryNode<E> node, BinaryNode<E> element) {
        if (node == null) {
            node = element;
        } else {
            int res = ((Comparable) element.getElement()).compareTo(node.getElement());
            if (res < 0) {
                BinaryNode<E> son = insert(node.getLeft(), element);
                node.setLeft(son);
                son.setParent(node);
            } else if (res > 0) {
                BinaryNode<E> son = insert(node.getRight(), element);
                node.setRight(son);
                son.setParent(node);
            }
        }
        return node;
    }

    @Override
    public void remove(E element) {
        setRoot(remove(root, element));
        size--;
    }

    /**
     * Traemos al hermano del nodo recibido por parámetros
     *
     * @param node Nodo del que deseamos conocer el hermano
     * @return Hermano del nodo
     */
    protected BinaryNode<E> getBrother(BinaryNode<E> node) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        BinaryNode parent = node.getParent();
        if (parent != null) {
            if (parent.hasLeft() && parent.getLeft().equals(node)) {
                return parent.getRight();
            } else {
                return parent.getLeft();
            }
        }
        return null;
    }

    // para eliminar recursivamente unicamente necesitaremos 
    // buscar en el subárbol izquierdo, el mayor elemento
    private BinaryNode<E> remove(BinaryNode<E> root, E element) {
        if (root == null) {
            return root;
        }
        int res = element.compareTo(root.getElement());
        if (res < 0) {
            root.setLeft(remove(root.getLeft(), element));
        } else if (res > 0) {
            root.setRight(remove(root.getRight(), element));
        } else {
            if (!root.hasRight()) {
                return root.getLeft();
            } else if (!root.hasLeft()) {
                return root.getRight();
            }
            root.setElement(maxValue(root.getLeft()));
            root.setLeft(remove(root.getLeft(), root.getElement()));
        }
        return root;
    }

    /**
     * Búsqueda de elemento
     *
     * @param element Elemento a buscar en el árbol
     * @return Devolverá la referencia al nodo que contenga el elemento, en caso
     * de existir
     */
    protected BinaryNode<E> searchElement(E element) {
        return searchInOrder(element, root);
    }

    /**
     * Método que buscar el maximo valor dado un nodo recibido
     *
     * @param root Nodo que se recibe para buscar en sus hijos
     * @return Máximo valor enncontrado
     */
    protected E maxValue(BinaryNode<E> root) {
        if (root != null) {
            E minv = root.getElement();
            while (root.hasRight()) {
                minv = root.getRight().getElement();
                root = root.getRight();
            }
            return minv;
        }
        return null;
    }

    // la búsqueda recursiva será inorder en los árboles de búsqueda
    private BinaryNode<E> searchInOrder(E element, BinaryNode<E> node) {
        if (node != null) {
            int res = ((Comparable) element).compareTo(node.getElement());
            if (res < 0) {
                return searchInOrder(element, node.getLeft());
            } else if (res > 0) {
                return searchInOrder(element, node.getRight());
            } else if (res == 0) {
                return node;
            }
        }
        return null;
    }

    /**
     * Preguntamos si el elemento recibido es la raíz
     *
     * @param element Elemento a preguntar
     * @return verdadero en caso de serlo, falso en caso contrario
     */
    protected boolean isRoot(E element) {
        return element.equals(root.getElement());
    }

    /**
     * Método para coger el primero (menor) de los nodos
     *
     * @return Devolverá el menor de los nodos
     */
    protected BinaryNode<E> getFirst() {
        if (root != null) {
            BinaryNode<E> e = root;
            while (e.hasLeft()) {
                e = e.getLeft();
            }
            return e;
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        Iterator it = nodes();
        while (it.hasNext()) {
            BinaryNode<E> node = (BlackRedNode<E>) it.next();
            res += "\n" + node.getElement() + ":" + node.getLeft() + "|" + node.getRight();
        }
        return res;
    }

}
