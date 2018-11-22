/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;

import java.util.List;

/**
 * Interfaz que representa las acciones que puede realizar una población para un
 * algoritmo de tipo genético o basado en poblaciones.
 *
 *
 * @author Quini Roiz
 */
public interface IPopulation {

    /**
     * Generación de población, en función de lo elegido en el constructor será
     * aleatoria o a partir de ciertos datos o ficheros.
     *
     */
    void generatePopulation() throws CloneNotSupportedException;

    /**
     * Método que evalúa la población y obtiene el fitness de cada miembro.
     */
    abstract void evaluate();

    /**
     * Algoritmo de selección de la población.
     *
     * @throws java.lang.CloneNotSupportedException en caso de error de
     * clonación.
     *
     * @see CloneNotSupportedException
     */
    void selection() throws CloneNotSupportedException;

    /**
     * Algoritmo de cruce de población. Lanzará diferentes hilos para
     * paralelizar los cruces.
     *
     * @throws java.lang.CloneNotSupportedException en caso de error de
     * clonación.
     *
     * @see CloneNotSupportedException
     */
    abstract void crossover() throws CloneNotSupportedException;

    void sort();

    /**
     * Devolverá el mejor individuo de la población.
     *
     * @return el individuo con mejor fenotipo
     * @see AbstractIndividual
     */
    AbstractIndividual getBestIndividual();

    /**
     * Devolverá una copia del listado de individuos de la población.
     *
     * @return listado de individuos de la población
     *
     * @see List
     * @see AbstractIndividual
     */
    List<AbstractIndividual> getIndividuals();
}
