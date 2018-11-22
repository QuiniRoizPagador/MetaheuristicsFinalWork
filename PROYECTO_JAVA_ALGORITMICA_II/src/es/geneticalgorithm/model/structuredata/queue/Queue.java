/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.queue;

public interface Queue<E> {

    int size();

    boolean isEmpty();

    /**
     * devuelve el primer elemento sin borrarlo de la cola
     *
     * @return
     * @throws EmptyQueueException
     */
    E front() throws EmptyQueueException;

    void enqueue(E o) throws QueueFullException;

    E dequeue() throws EmptyQueueException;

    public boolean search(E e);
}
