/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.binaryTree;

import java.util.Iterator;

/**
 * Interfaz que será implementada por los árboles binarios de búsqueda
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public interface SearchBinaryTree<E extends Comparable> {

    /**
     * Método que insertará un elemento en el árbol
     *
     * @param element Elemento a insertar en el árbol
     */
    void insert(E element);

    /**
     * Método que eliminará un elemento del árbol
     *
     * @param element Elemento a eliminar del arbol
     */
    void remove(E element);

    /**
     * Tamaño del árbol
     *
     * @return número entero con el tamaño del árbol
     */
    int size();

    /**
     * Metodo que devuelve la altura del árbol
     *
     * @return número entero con la altura
     */
    int getHeight();

    /**
     * Comprobacion de si el árbol está vacío
     *
     * @return variable booleana
     */
    boolean isEmpty();

    /**
     * Método que permite recorrer el listado de elementos
     *
     * @return Iterator para recorrer los elementos de árbol
     */
    Iterator elements();

    /**
     * Método que permite recorrer el listado de nodos
     *
     * @return Iterator para recorrer los nodos del árbol
     */
    Iterator nodes();

    /**
     * Nodo root o raíz del árbol
     *
     * @return Elemento contenido en la raíz del árbol
     */
    E root();
}
