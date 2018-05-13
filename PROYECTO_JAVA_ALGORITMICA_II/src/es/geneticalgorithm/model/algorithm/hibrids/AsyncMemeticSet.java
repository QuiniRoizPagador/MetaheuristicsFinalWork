/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import es.geneticalgorithm.model.algorithm.genetic.AsyncPopulation;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.localsearch.trayectories.SimulatedAnnealing;

/**
 * Clase conjunto que extenderá de una población para los algoritmos híbridos y
 * será utilizada para paralelizar los calculos.
 *
 *
 * @see MemeticSet
 * @see AsyncPopulation
 *
 * @author Quini Roiz
 */
public class AsyncMemeticSet extends AsyncPopulation implements MemeticSet {

    @Override
    public void localSearch() throws CloneNotSupportedException {
        // ordenar en paralelo
        individuals = individuals.parallelStream().sorted().collect(Collectors.toList());

        // contador auxiliar
        AtomicInteger index = new AtomicInteger();
        // itero en paralelo y almaceno el resultado en listado auxiliar
        List<AbstractIndividual> aux = individuals.parallelStream()
                    .filter(i -> index.incrementAndGet() < (individuals.size() / 5))
                    .map((ind) -> {
                        // crear enfriamiento e inicializar
                        try {
                            SimulatedAnnealing ts = new SimulatedAnnealing(true);
                            // actualizar individuo con el que trabaja
                            ts.setIndividual(ind);
                            // ejecutar búsqueda local
                            ts.run();
                            // recogemos el resultado
                            return ts.getBest();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(AsyncMemeticSet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return null;
                        // se recolecta a una lista todo
                    }).collect(Collectors.toList());
        // reinsertar lo calculado
        individuals.subList(individuals.size() - (individuals.size() / 5), individuals.size() - 1).clear();
        individuals.addAll(aux);
    }
}
