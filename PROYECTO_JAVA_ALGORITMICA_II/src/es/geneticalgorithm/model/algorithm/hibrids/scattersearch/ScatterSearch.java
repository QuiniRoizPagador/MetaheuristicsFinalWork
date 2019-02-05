/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids.scattersearch;

import java.util.logging.Level;
import java.util.logging.Logger;
import es.geneticalgorithm.model.algorithm.genetic.GeneticAlgorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.util.Utils;

/**
 * Clase similar al algoritmo genético que trabajará con diferentes
 * subconjuntos.
 *
 * <p>
 *
 * Posteriormente, tras un número limitado de iteraciones, generará un nuevo
 * conjunto y actualizará P2 para seguir iterando y buscar una nueva y mejor
 * solución.
 *
 * @see ScatterSet
 *
 *
 * @author Quini Roiz
 */
public class ScatterSearch extends GeneticAlgorithm {

    private ScatterSet p;

    public ScatterSearch(boolean async) throws CloneNotSupportedException {
        super(async);
    }

    @Override
    public void prepare() throws CloneNotSupportedException {
        size = Utils.individual_size;
        Utils.population_size = 100;
        p = new ScatterSetImpl();
        p.generatePopulation();
        p.evaluate();
        p.sort();
        bestIndividual = p.getBestIndividual();

        p.generateR1();
        p.generateR2();
    }

    @Override
    public synchronized void run() {
        try {
            converge = false;
            // mientras no converge
            it = 0;

            
            while (it < Utils.MAX_ITERATIONS && !converge) {
                // cruce
                p.crossover();
                // búsqueda local
                p.localSearchR();
                // se actualiza el conjunto obtenido
                p.updateR();
                // evaluar nuevo mejor
                p.sort();
                newBest = p.getBestIndividual().clone();
                int cmp = newBest.compareTo(bestIndividual);
                // si no se ha mejorado la solución encontrada
                if (cmp < 0) {
                    // si se ha mejorado la solución actualizamos al nuevo mejor
                    bestIndividual = newBest;
                    // guardamos la iteración en la que se ha encontrado
                    posExito = it;
                    // reseteamos las repeticiones
                    repeticiones = 0;
                }else{
                    repeticiones++;
                }

                if (p.isEstable()) {
                    // si ha habido estabilidad reconstruimos
                    p.generatePopulation();
                    p.generateR2();
                }
                if (repeticiones >= Utils.MAX_REPETITIONS) {
                    converge = true;
                }
                it++;
                notifyObservers();
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(GeneticAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            notifyFinished();
        }
    }

    @Override
    public double getPorcentaje() {
        return it / Utils.MAX_ITERATIONS * 100;
    }

}
