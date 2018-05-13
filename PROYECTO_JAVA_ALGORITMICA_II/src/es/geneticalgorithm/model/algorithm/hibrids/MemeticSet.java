/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids;

import es.geneticalgorithm.model.algorithm.genetic.IPopulation;

/**
 * Interfaz que utilizará la clase Set del algoritmo Memético. Extiende de la
 * clase IPopulation.
 *
 *
 *
 * @see IPopulation
 *
 *
 *
 * @author Quini Roiz
 */
public interface MemeticSet extends IPopulation {

    /**
     * Se ejecuta una búsqueda local sobre un tamaño de población
     *
     * @throws CloneNotSupportedException en caso de error de clonación
     */
    void localSearch() throws CloneNotSupportedException;
}
