/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.node;

/**
 * Clase nodo que guarda las referencias a los elementos que se almacenarán en
 * el grafo.
 *
 * @author Quini Roiz
 * @param <E> Los tipos utilizados serán genéricos en esta clase, adaptándose a
 * lo recibido en la declaración
 */
public class NodeImpl<E> implements Node<E>, Comparable<Node<E>> {

    private E element;
    private double cost;
    private boolean visited;
    private Node<E> previous;
    private Node<E> next;

    public NodeImpl(E element, int cost) {
        this.element = element;
        this.cost = cost;
    }

    public NodeImpl(E element) {
        this.element = element;
    }
    
    @Override
    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public Node<E> getNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setElement(E element) {
        this.element = element;
    }

    @Override
    public Node<E> getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    @Override
    public boolean hasPrevious() {
        return previous != null;
    }

    @Override
    public E getElement() {
        return element;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public void setVisited() {
        visited = true;
    }

    @Override
    public void setUnvisited() {
        visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node<E> o = (Node) obj;
            return element.equals(o.getElement());
        }
        return false;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    @Override
    public int compareTo(Node<E> o) {
        double cmp = cost - o.getCost();
        if (cmp > 0) {
            return 1;
        } else if (cmp < 0) {
            return -1;
        }
        return 0;
    }
}
