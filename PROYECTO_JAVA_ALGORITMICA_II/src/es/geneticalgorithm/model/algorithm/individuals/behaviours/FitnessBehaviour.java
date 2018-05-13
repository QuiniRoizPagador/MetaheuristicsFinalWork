/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.individuals.behaviours;

import java.util.List;

/**
 * Interfaz que representa el comportamiento de los fitness para un cálculo
 * particular a un tipo de problema a resolver.
 *
 * @author Quini Roiz
 */
public interface FitnessBehaviour {

    /**
     * Calcula el fitness del individuo partir del genotipo recibido por
     * parámetros.
     *
     * @param genotype genotipo del individuo para realizar el cálculo de
     * fitness.
     * @return variable <code>double</code> con el valor del fitness.
     *
     * @see List
     * @see Integer
     */
    double calculateFitness(List<Integer> genotype);
}
