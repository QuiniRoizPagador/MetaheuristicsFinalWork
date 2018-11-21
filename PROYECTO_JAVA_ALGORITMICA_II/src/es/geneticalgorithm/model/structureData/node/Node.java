/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structureData.node;

/**
 * Interfaz que será implementada por los nodos usados para implementar
 * estructuras de datos
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public interface Node<E> {

    /**
     * Método que devuelve el elemento del nodo
     *
     * @return Elemento que contiene el nodo
     */
    E getElement();

    /**
     * Método que actualiza el valor del elemento del nodo
     *
     * @param element elemento nuevo
     */
    void setElement(E element);

    /**
     * Método que actualizará el valor referente al nodo anterior
     *
     * @param e nodo anterior
     */
    void setPrevious(Node<E> e);

    /**
     * Método que devuelve una referencia al nodo anterior
     *
     * @return nodo anterior
     */
    Node<E> getPrevious();

    /**
     * Método que devolverá la veracidad de existencia de un nodo previo
     *
     * @return booleano con valor de la comprobación
     */
    boolean hasPrevious();

    /**
     * Método que actualizará el coste del nodo
     *
     * @param c coste del nodo
     */
    void setCost(double c);

    /**
     * Método que actualizará el valor del nodo a no visitado
     */
    void setUnvisited();

    /**
     * Método que actualizará el valor del nodo a visitado
     */
    void setVisited();

    /**
     * Método que devolverá el valor de comprobar si el nodo ha sido visitado
     *
     * @return booleano con valor de la comprobación
     */
    boolean isVisited();

    /**
     * Método que devolverá el coste del nodo
     *
     * @return valor con el coste
     */
    double getCost();

    @Override
    public boolean equals(Object obj);

}
