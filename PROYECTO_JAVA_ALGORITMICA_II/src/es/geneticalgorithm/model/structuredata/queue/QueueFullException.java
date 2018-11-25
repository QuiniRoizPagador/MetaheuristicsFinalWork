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
 * error de una cola llena.
 *
 * @author Quini Roiz
 */
public class QueueFullException extends Exception {

    public QueueFullException(String msg) {
        super(msg);
    }

}
