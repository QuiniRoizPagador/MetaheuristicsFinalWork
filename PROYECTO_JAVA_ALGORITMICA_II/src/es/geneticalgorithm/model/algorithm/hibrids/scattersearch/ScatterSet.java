/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids.scattersearch;

/**
 * Interfaz que será implementada por los conjuntos para búsqueda dispersa,
 * donde se generarán los subconjuntos R1 y R2 y se trabajará con ellos.
 *
 * @author Quini Roiz
 */
public interface ScatterSet extends ISet {

    /**
     * Generación del subconjunto R1
     */
    void generateR1();

    /**
     * Generación de subconjunto R2
     */
    void generateR2();

    /**
     * Método que realizará una búsqueda local sobre el conjunto R
     *
     * @throws CloneNotSupportedException en caso de fallo
     */
    void localSearchR() throws CloneNotSupportedException;

    /**
     * Método que actualizará el conjunto de datos
     */
    void updateR();

    /**
     * Método que comprobará si ha habido cambios durante la última
     * actualización de los subconjuntos
     *
     * @return variable booleana con el resultado de la comprobación
     */
    boolean isEstable();

}
