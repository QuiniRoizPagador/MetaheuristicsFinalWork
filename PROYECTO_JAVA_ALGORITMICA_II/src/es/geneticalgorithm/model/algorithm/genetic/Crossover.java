/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic;

import java.util.Random;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.genetic.behaviours.BinaryCrossover;
import es.geneticalgorithm.model.algorithm.genetic.behaviours.CrossoverStrategy;
import es.geneticalgorithm.util.Utils;

/**
 * Clase que se encargará de cruzar y mutar individuos
 *
 * @author Quini Roiz
 */
public class Crossover<I extends AbstractIndividual> {

    private final I p1;
    private final I p2;
    private I child;
    private final int crossoverType;
    private CrossoverStrategy<I> strategy;
    private final Random rn;

    /**
     * Constructor por defecto que atenderá a dos individuos para que generen un
     * hijo. Según el tipo pasado por parámetros se realizará un tipo u otro de
     * cruce. Esta clase implementa Runnable para paralelizar a través de hilos.
     *
     * @param p1 padre 1
     * @param p2 padre 2
     * @param crossoverType tipo de cruce a realizar
     */
    public Crossover(I p1, I p2, int crossoverType) {
        this.p1 = p1;
        this.p2 = p2;
        this.crossoverType = crossoverType;
        // el cruce puede ser binario o asignación para una asignación cuadrática
        // así que tiramos dado para elegir tipo de cruce
        strategy = new BinaryCrossover();
        rn = new Random();

    }

    /**
     * Método que lanzará un dado y ,en función del resultado, mutará o no un
     * gen al azar.
     *
     */
    public void mutation() {
        // lanzamos dado para mutar
        if (rn.nextFloat() < Utils.MUTATION_RATE) {
            child.mutate();
        }
    }

    /**
     * Ejecución del crossover que, según el tipo elegido en el constructor,
     * ejecutará una estrategia por porción o uniforme de cruce
     *
     * @return individuo hijo generado del cruce
     */
    public I crossover() {
        try {
            // según el dado del tipo recibido el cruce será por porción o uniforme
            switch (crossoverType) {
                case Utils.PORTION_TYPE:
                    child = strategy.portion(p1, p2);
                    break;
                case Utils.UNIFORM_TYPE:
                    child = strategy.uniform(p1, p2);
                    break;
            }
            // probamos suerte para mutar
            mutation();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("Error al clonar al hijo");
        }
        return child;
    }

    /**
     * Parte del patrón estrategia
     *
     * @param strategy estrategia elegida
     */
    public void setStrategy(CrossoverStrategy strategy) {
        this.strategy = strategy;
    }

}
