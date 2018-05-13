/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids;

import es.geneticalgorithm.model.algorithm.genetic.AsyncPopulation;
import java.util.LinkedList;
import java.util.List;
import es.geneticalgorithm.model.algorithm.genetic.SecuentialPopulation;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.localsearch.trayectories.SimulatedAnnealing;
import java.util.Collections;

/**
 * Clase conjunto que extenderá de una población para los algoritmos híbridos.
 *
 *
 * @see MemeticSet
 * @see AsyncPopulation
 *
 * @author Quini Roiz
 */
public class SecuentialMemeticSet extends SecuentialPopulation implements MemeticSet {

    @Override
    public void localSearch() throws CloneNotSupportedException {
        // ordenar individuos
        Collections.sort(individuals);
        // crear llista de búsquedas locales
        List<SimulatedAnnealing> tss = new LinkedList<>();
        SimulatedAnnealing ts;
        AbstractIndividual ind;

        // itero en el número de individuos a mejorar
        for (int i = 0; i < individuals.size() / 5; i++) {
            ind = individuals.get(i);
            // inicializo enfriamiento simulado
            ts = new SimulatedAnnealing(false);
            // actualizo el individuo con el que trabajará
            ts.setIndividual(ind);
            // añadir a la lista para recaudar luego los resultados
            tss.add(ts);
            // ejecutar búsqueda local
            ts.run();
        }
        // reinserto lo calculado
        individuals.subList(individuals.size() - (individuals.size() / 5), individuals.size() - 1).clear();
        tss.forEach((t) -> {
            individuals.add(t.getBest());
        });
    }
}
