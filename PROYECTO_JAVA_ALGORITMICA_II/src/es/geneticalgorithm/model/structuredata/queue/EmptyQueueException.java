/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.queue;

/**
 * Clase que extiende de la clase Exception y será utilizada para identificar el
 * error de una cola vacía.
 *
 * @author Quini Roiz
 */
public class EmptyQueueException extends Exception {

    public EmptyQueueException(String msg) {
        super(msg);
    }
}
