/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structureData.binaryTree;

/**
 * Interfaz que será implementada por los nodos usados para implementar árboles
 * rojinegros
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public interface BlackRedNode<E extends Comparable> extends BinaryNode<E> {

    /**
     * Método para saber si es rojo el nodo
     *
     * @return True si es rojo, False en caso contrario
     */
    boolean isRed();

    /**
     * Método para saber si es negro el nodo
     *
     * @return True si es negro, False en caso contrario
     */
    boolean isBlack();

    /**
     * Devuelve la referencia al color del nodo
     *
     * @return Referencia al color
     */
    Enum color();

    /**
     * Vuelve rojo al nodo
     */
    void setRed();

    /**
     * Vuelve negro al nodo
     */
    void setBlack();

    /**
     * Invierte el color del nodo
     */
    void inverseColor();

    /**
     * Actualiza el color del nodo con el parámetro recibido
     *
     * @param color Nuevo color del nodo
     */
    void setColor(Enum color);

    @Override
    BlackRedNode<E> getParent();

    /**
     * Actualización de la referencia al padre del nodo
     *
     * @param parent Nuevo padre del nodo
     */
    void setParent(BlackRedNode<E> parent);

    @Override
    boolean equals(Object obj);

}
