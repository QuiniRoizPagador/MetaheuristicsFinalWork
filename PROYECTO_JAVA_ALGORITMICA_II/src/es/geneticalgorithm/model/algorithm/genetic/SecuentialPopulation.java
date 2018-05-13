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
import java.util.ArrayList;

/**
 * Clase que ejecutará los métodos propios de la población en serie
 *
 * @author Quini Roiz
 *
 * @see Population
 */
public class SecuentialPopulation extends Population {

    @Override
    public void generatePopulation() {
        AbstractIndividual ind;
        individuals = new ArrayList<>();
        for (int i = 0; i < Utils.population_size; i++) {
            ind = new SyncQuadraticIndividual();
            ind.generateData();
            individuals.add(ind);
        }
    }

    @Override
    public void evaluate() {
        for (AbstractIndividual ind : individuals) {
            ind.calculateFitness();
        }
    }

    @Override
    public void crossover() throws CloneNotSupportedException {
        int p1, p2;
        // tamaño restante para igualar a la población total
        Crossover c1;
        AbstractIndividual ind;
        do {
            // iteraremos por el número restante hasta rellenar la población
            // escogemos dos padres aleatoriamente
            p1 = rn.nextInt(Utils.population_size);
            p2 = rn.nextInt(Utils.population_size);
            // tiramos dado. 50% de probabilidades de realizar un cruce uniforme o un cruce por porción
            if (rn.nextFloat() > 0.5) {
                c1 = new Crossover(individuals.get(p1), individuals.get(p2), Utils.UNIFORM_TYPE);
            } else {
                c1 = new Crossover(individuals.get(p1), individuals.get(p2), Utils.PORTION_TYPE);
            }
            ind = c1.crossover();
            if (ind.isValid()) {
                nextGeneration.add(ind);
            }
        } while (nextGeneration.size() != Utils.population_size);
        // actualizamos la referencia de los individuos de la población, 
        // dado que ya se han reproducido y es una nueva población
        individuals = nextGeneration;
        nextGeneration = null;
        evaluate();
    }

}
