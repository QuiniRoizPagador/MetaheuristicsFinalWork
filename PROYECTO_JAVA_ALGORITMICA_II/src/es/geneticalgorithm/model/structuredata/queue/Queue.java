/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.queue;

/**
 * Interfaz que representa una cola y sus funciones básicas.
 *
 * @author Quini Roiz
 * @param <E> Los tipos utilizados serán genéricos en esta clase, adaptándose a
 * lo recibido en la declaración
 */
public interface Queue<E> {

    /**
     * Método que devuelve el tamaño de la cola
     *
     * @return entero con el número de elementos en la cola
     */
    int size();

    /**
     * Método que comprobará si la cola está vacía
     *
     * @return booleano con el resultado de la comprobación
     */
    boolean isEmpty();

    /**
     * Método que devuelve referencia al primer elemento sin borrarlo de la cola
     *
     * @return referencia al primero elemento de la cola
     * @throws EmptyQueueException en caso de cola vacía
     */
    E front() throws EmptyQueueException;

    /**
     * Método que añadirá un elemento a la cola
     *
     * @param o Elemento a añadir a la cola
     * @throws QueueFullException en caso de cola llena
     */
    void enqueue(E o) throws QueueFullException;

    /**
     * Método que desencolará al primer elemento de la cola
     *
     * @return Referencia al primer elemento de la cola
     * @throws EmptyQueueException en caso de cola vacía
     */
    E dequeue() throws EmptyQueueException;

    /**
     * Método que buscará un elemento en la cola
     *
     * @param e elemento a buscar en la cola
     * @return booleano con el resultado de la comprobación
     */
    boolean search(E e);
}
