/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata;

import es.geneticalgorithm.model.structuredata.node.Node;
import es.geneticalgorithm.model.structuredata.node.NodeImpl;
import es.geneticalgorithm.model.structuredata.queue.EmptyQueueException;
import es.geneticalgorithm.model.structuredata.queue.Queue;

/**
 * Clase que implementa una lista enlazada para colas y pilas.
 *
 * @author Quini Roiz
 * @param <E> Los tipos utilizados serán genéricos en esta clase, adaptándose a
 * lo recibido en la declaración
 */
public class LinkedList<E> implements Queue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public int size() {
        // devolvemos el tamaño de la cola
        return size;
    }

    @Override
    public boolean isEmpty() {
        // será cero cuando esté vacía
        return size == 0;
    }

    @Override
    public E front() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Error! Cola vacía!");
        }
        // devolvemos el primer elemento de la cola
        return first.getElement();
    }

    @Override
    public void enqueue(E element) {
        // añadimos al final de la cola creando un nodo
        Node<E> n = new NodeImpl<>(element);
        // si el último no es nulo es que tenemos +1 elementos
        if (last != null) {
            // enlazamos el último con su siguiente futuro
            last.setNext(n);
            // actualizamos la referencia al nuevo último
            last = n;
        } else {
            // si no hay último lo añadimos. En tal caso
            // será también primero, dado que estaba vacía
            first = last = n;
        }
        // actualizamos el tamaño
        size++;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        // la comprobación de vacío la realizará el método 'front()'
        // cogemos el frente (para seguir el sistema FIFO)
        E element = front();
        // eliminamos el primero primero salvando la posición del siguiente
        Node<E> antiguo = first;
        // modificamos la referencia del siguiente al antiguo primero
        first = antiguo.getNext();
        // eliminamos la referencia al siguiente
        antiguo.setNext(null);
        if (first == null) {
            last = first;
        }
        // bajamos 1 el tamaño de la cola
        size--;
        // devolvemos el elemento primero
        return element;
    }

    @Override
    public boolean search(E e) {
        boolean caught = false;
        Node n = first;
        while (!caught && n != null) {
            if (n.getElement().equals(e)) {
                caught = true;
            } else {
                n = n.getNext();
            }
        }
        return caught;
    }

    @Override
    public String toString() {
        String res = "";
        if (!isEmpty()) {
            Node<E> node = first;
            res += node.getElement() + " | ";
            while (node.getNext() != null) {
                node = node.getNext();
                res += node.getElement() + " | ";
            }
        } else {
            res = "No hay datos que mostrar.";
        }

        return res;

    }
}
