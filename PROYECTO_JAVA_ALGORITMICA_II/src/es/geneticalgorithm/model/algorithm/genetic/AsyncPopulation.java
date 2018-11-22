/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.individuals.SyncQuadraticIndividual;
import es.geneticalgorithm.util.Utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que ejecutará los métodos propios de la población en paralelo
 *
 * @author Quini Roiz
 *
 * @see Population
 */
public class AsyncPopulation extends Population {

    @Override
    public void generatePopulation() throws CloneNotSupportedException {
        individuals = IntStream.range(0, Utils.population_size).parallel().mapToObj((value) -> {
            AbstractIndividual ind = new SyncQuadraticIndividual();
            ind.generateData();
            return ind;
        }).collect(Collectors.toList());
    }

    @Override
    public void evaluate() {
        individuals = individuals.parallelStream().map((ind) -> {
            ind.calculateFitness();
            return ind;
        }).collect(Collectors.toList());
    }

    @Override
    public void crossover() {
        // tamaño restante para igualar a la población total
        int size;
        List<AbstractIndividual> aux;
        size = Utils.population_size - nextGeneration.size();
        aux = IntStream.range(0, size)
                .parallel()
                .mapToObj((value) -> {
                    AbstractIndividual ind = null;
                    int p1, p2;
                    do {
                        p1 = rn.nextInt(Utils.population_size);
                        p2 = rn.nextInt(Utils.population_size);
                        Crossover c1;
                        // tiramos dado. 50% de probabilidades de realizar un cruce uniforme o un cruce por porción
                        if (rn.nextFloat() > 0.5) {
                            c1 = new Crossover(individuals.get(p1), individuals.get(p2), Utils.UNIFORM_TYPE);
                        } else {
                            c1 = new Crossover(individuals.get(p1), individuals.get(p2), Utils.PORTION_TYPE);
                        }
                        ind = c1.crossover();
                    } while (!ind.isValid());
                    return ind;
                }).collect(Collectors.toList());
        nextGeneration.addAll(aux);
        // actualizamos la referencia de los individuos de la población, 
        // dado que ya se han reproducido y es una nueva población
        individuals = nextGeneration;
        nextGeneration = null;
        evaluate();
    }

}
