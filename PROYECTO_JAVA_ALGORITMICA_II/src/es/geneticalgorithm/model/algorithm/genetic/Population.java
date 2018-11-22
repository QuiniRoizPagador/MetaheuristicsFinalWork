/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Población compuesta por una coleccion de individuos
 *
 * @author Quini Roiz
 *
 * @see IPopulation
 */
public abstract class Population implements IPopulation {

    protected List<AbstractIndividual> individuals;
    protected List<AbstractIndividual> nextGeneration;
    protected Random rn;

    /**
     * Default constructor
     */
    public Population() {
        rn = new Random();
    }

    /**
     * Generación de población, en función de lo elegido en el constructor será
     * aleatoria o a partir de ciertos datos o ficheros.
     *
     */
    @Override
    public abstract void generatePopulation() throws CloneNotSupportedException;

    /**
     * Método que evalúa la población y obtiene el fitness de cada miembro.
     *
     */
    @Override
    public abstract void evaluate();

    /**
     * Algoritmo de selección de la población.
     *
     * @throws java.lang.CloneNotSupportedException en caso de error
     */
    @Override
    public void selection() throws CloneNotSupportedException {
        Selection selection = new Selection();
        List<AbstractIndividual> newPopulation = new ArrayList<>();
        newPopulation.addAll(selection.elitism(this));
        newPopulation.addAll(selection.selectionRoulette(this));
        newPopulation.addAll(selection.tournament(this));

        nextGeneration = newPopulation;
    }

    /**
     * Algoritmo de cruce de población. Lanzará diferentes hilos para
     * paralelizar los cruces.
     *
     * @throws java.lang.CloneNotSupportedException en caso de error
     */
    @Override
    public abstract void crossover() throws CloneNotSupportedException;

    @Override
    public void sort() {
        individuals = individuals.parallelStream().sorted().collect(Collectors.toList());
    }

    /**
     * Devolverá el mejor individuo de la población.
     *
     * @return el individuo con mejor fenotipo
     */
    @Override
    public AbstractIndividual getBestIndividual() {
        return individuals.get(0);
    }

    /**
     * Devolverá una copia del listado de individuos de la población.
     *
     * @return listado de individuos de la población
     */
    @Override
    public List<AbstractIndividual> getIndividuals() {
        return individuals;
    }

    @Override
    public String toString() {
        String res = "";
        for (AbstractIndividual i : individuals) {
            res += i;
        }
        return res;
    }
}
