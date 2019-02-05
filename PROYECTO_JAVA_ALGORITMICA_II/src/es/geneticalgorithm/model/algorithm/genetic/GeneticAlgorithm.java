/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic;

import es.geneticalgorithm.model.algorithm.Algorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;
import es.geneticalgorithm.util.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase algoritmo genético. Esta clase se podrá paralizar en futuras
 * implementaciones para realizar varios intentos diferentes en caso de que se
 * indique en el programa principal.
 *
 * @see IPopulation
 * @see Runnable
 *
 * @author Quini Roiz
 */
public class GeneticAlgorithm extends Algorithm implements Runnable {

    private IPopulation population;
    protected int size;
    protected AbstractIndividual newBest;

    public GeneticAlgorithm(boolean async) throws CloneNotSupportedException {
        super(async);
    }

    @Override
    public void prepare() throws CloneNotSupportedException {
        if (async) {
            population = new AsyncPopulation();
        } else {
            population = new SecuentialPopulation();
        }
        size = Math.max(MemoryData.getInstance().getClients().size(), MemoryData.getInstance().getEmployees().size());
        Utils.population_size = (int) Math.log(size) * 10;
        population.generatePopulation();
        population.evaluate();
        population.sort();
        bestIndividual = population.getBestIndividual();
    }

    @Override
    public synchronized void run() {
        try {
            converge = false;
            // mientras no converge
            it = 0;

            int maxGenerations = (int) (size * Math.pow(Math.log(size), 2));
            int maxIt = (int) (size * Math.log(size));
            while (it < maxGenerations && !converge) {
                // selección
                population.selection();
                // cruce
                population.crossover();
                population.sort();
                // evaluar nuevo mejor
                newBest = population.getBestIndividual().clone();
                int cmp = newBest.compareTo(bestIndividual);
                // si no se ha mejorado la solución encontrada
                if (cmp < 0) {
                    // si se ha mejorado la solución actualizamos al nuevo mejor
                    bestIndividual = newBest;
                    // guardamos la iteración en la que se ha encontrado
                    posExito = it;
                    // reseteamos las repeticiones
                    repeticiones = 0;
                    notifyObservers();

                } else {
                    repeticiones++;
                }

                // si se ha superado el número máximo iteraciones sin mejora
                if (repeticiones >= maxIt) {
                    // salimos
                    converge = true;
                }
                it++;
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneticAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            notifyFinished();
        }
    }

    @Override
    public double getPorcentaje() {
        return it / (size * Math.pow(Math.log(size), 2)) * 100;
    }

    @Override
    public AbstractIndividual getPosible() {
        return newBest;
    }

    
}
