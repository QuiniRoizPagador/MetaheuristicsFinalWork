/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids;

import es.geneticalgorithm.model.algorithm.genetic.GeneticAlgorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;
import es.geneticalgorithm.util.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase similar al algoritmo genético que introduce un algoritmo de búsqueda
 * local tras los cruces.
 *
 * <p>
 *
 * Posteriormente, tras un número limitado de iteraciones, generará una nueva
 * población sobre la que iterar para buscar una nueva y mejor solución.
 *
 * @see MemeticSet
 *
 *
 * @author Quini Roiz
 */
public class MemeticAlgorithm extends GeneticAlgorithm implements Runnable {

    private MemeticSet set;

    public MemeticAlgorithm(boolean async) throws CloneNotSupportedException {
        super(async);
    }

    @Override
    public void prepare() throws CloneNotSupportedException {
        size = Math.max(MemoryData.getInstance().getClients().size(), MemoryData.getInstance().getEmployees().size());
        Utils.population_size = (int) Math.log(size) * 10;
        if (async) {
            set = new AsyncMemeticSet();
        } else {
            set = new SecuentialMemeticSet();
        }
        set.generatePopulation();
        set.evaluate();
        bestIndividual = set.getBestIndividual();
    }

    @Override
    public void run() {
        try {
            converge = false;
            // mientras no converge
            it = 1;
            int maxGenerations = (int) (size * Math.pow(Math.log(size), 2));
            int maxIt = (int) (size * Math.log(size));
            set.localSearch();
            boolean[] searchs = new boolean[100];
            while (it < maxGenerations && !converge) {
                // selección
                set.selection();
                // cruce
                set.crossover();
                if (((int) ((it / maxGenerations) * 100)) % 3 == 0 && !searchs[(int) ((it / maxGenerations) * 100)]) {
                    searchs[(int) ((it / maxGenerations) * 100)] = true;
                    set.localSearch();
                }

                set.sort();
                // evaluar nuevo mejor
                AbstractIndividual newBest = set.getBestIndividual().clone();
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

                // si se ha superado el número máximo tiempo sin mejora
                if (repeticiones >= maxIt / 2) {
                    // generamos nueva población
                    set.generatePopulation();
                    set.evaluate();
                }
                if (repeticiones >= maxIt) {
                    converge = true;
                }
                it++;
                notifyObservers();
            }

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MemeticAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            notifyFinished();
        }
    }

    @Override
    public double getPorcentaje() {
        return it / (size * Math.pow(Math.log(size), 2)) * 100;
    }
}
