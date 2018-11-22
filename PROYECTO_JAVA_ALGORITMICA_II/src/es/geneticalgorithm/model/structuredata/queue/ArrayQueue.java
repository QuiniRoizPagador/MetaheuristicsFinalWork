/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.queue;

/**
 * Clase que implementa una cola a través de un array.
 *
 * @author Quini Roiz
 * @param <E> Los tipos utilizados serán genéricos en esta clase, adaptándose a
 * lo recibido en la declaración
 */
public class ArrayQueue<E> implements Queue<E> {

    private final E[] elements;
    private int currentSize;
    private int front;
    private int back;

    public ArrayQueue(int capacity) {
        E[] a = (E[]) new Object[capacity];
        elements = a;
        currentSize = 0;
        front = 0;
        back = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public E front() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("getFront: Queue is empty!!");
        }
        return elements[front];
    }

    public boolean isFull() {
        return elements.length == currentSize - 1;
    }

    @Override
    public int size() {
        return (currentSize - front + back) % currentSize;
    }

    @Override
    public void enqueue(E o) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException("Queue is full");
        }
        back = increment(back);
        elements[back] = o;
        currentSize++;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("dequeue: Queue is empty!!");
        }
        currentSize--;
        E frontItem = elements[front];
        elements[front] = null;
        front = increment(front);
        return frontItem;
    }

    private int increment(int x) {
        if (++x == elements.length) {
            x = 0;
        }
        return x;
    }

    @Override
    public boolean search(E e) {
        boolean encontrado = false;
        int pos = front;
        while (!encontrado && pos < currentSize) {
            if (e.equals(elements[pos])) {
                encontrado = true;
            } else {
                pos++;
            }
        }
        return encontrado;
    }
}
