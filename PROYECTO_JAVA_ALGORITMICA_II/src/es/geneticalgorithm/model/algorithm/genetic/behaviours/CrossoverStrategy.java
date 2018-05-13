/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic.behaviours;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;

/**
 * Interfaz comportamiento para los cruces, donde puede ejecutarse por porción o
 * uniforme
 *
 * @author Quini Roiz
 *
 */
public interface CrossoverStrategy<I extends AbstractIndividual> {

    /**
     * Cruce por porción, utilizando una pequeña porción de tamaño aleatorio de
     * un padre y el resto del otro.
     *
     * @param p1 padre 1
     * @param p2 padre 2
     * @return hijo instanciado con el cruce realizado
     */
    I portion(I p1, I p2);

    /**
     * Cruce uniforme que utiliza a partes iguales el cruce entre ambos padres.
     *
     * @param p1 padre 1
     * @param p2 padre 2
     * @return hijo instanciado con el cruce realizado
     * @throws CloneNotSupportedException en caso de error de clonación
     */
    I uniform(I p1, I p2) throws CloneNotSupportedException;
}
