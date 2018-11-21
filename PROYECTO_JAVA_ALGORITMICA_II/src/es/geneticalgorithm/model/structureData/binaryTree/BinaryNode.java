/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structureData.binaryTree;

import es.geneticalgorithm.model.structureData.node.Node;

/**
 * Interfaz que será implementada por los nodos usados para implementar árboles
 * binarios
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public interface BinaryNode<E extends Comparable> extends Node<E> {

    /**
     * Si el nodo tiene hijo izquierdo
     *
     * @return Boolean true/false
     */
    boolean hasLeft();

    /**
     * Si el nodo tiene hijo derecho
     *
     * @return Boolean true/false
     */
    boolean hasRight();

    /**
     * Método que devuelve la referencia al hijo izquierdo
     *
     * @return Referencia al hijo izquierdo
     */
    BinaryNode<E> getLeft();

    /**
     * Método que devuelve la referencia al hijo derecho
     *
     * @return Referencia al hijo derecho
     */
    BinaryNode<E> getRight();

    /**
     * Actualización de hijo izquierdo
     *
     * @param left Hijo izquierdo nuevo
     */
    void setLeft(BinaryNode<E> left);

    /**
     * Actualización de hijo derecho
     *
     * @param right Hijo derecho nuevo
     */
    void setRight(BinaryNode<E> right);

    /**
     * Referencia al padre del nodo actual
     *
     * @return Devuelve la referencia al padre
     */
    BinaryNode<E> getParent();

    /**
     * Atualiza la referencia al padre del nodo
     *
     * @param parent Nueva referencia al nodo padre
     */
    void setParent(BinaryNode<E> parent);

    /**
     * Preguntamos si el nodo tiene padre
     *
     * @return true si tiene, false en caso contrario
     */
    boolean hasParent();
}
