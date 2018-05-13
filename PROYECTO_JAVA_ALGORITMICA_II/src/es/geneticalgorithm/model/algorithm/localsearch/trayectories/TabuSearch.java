/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.localsearch.trayectories;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.geneticalgorithm.model.algorithm.localsearch.HillClimbing;

/**
 * Algoritmo basado en trayectorias que buscará en un número de iteraciones
 * límite sobre los vecinos del individuo actual. Escogerá el mejor y,
 * posteriormente, lo introducirá temporamente en una lista tabú a corto plazo.
 *
 * @see Queue
 *
 * @author Quini Roiz
 */
public class TabuSearch extends HillClimbing {

    private final Queue<AbstractIndividual> tabu;
    private final Queue<AbstractIndividual> longTimeTabu;

    public TabuSearch(boolean async) {
        super(async);
        tabu = new LinkedList<>();
        longTimeTabu = new LinkedList<>();
    }

    @Override
    public void run() {
        while (it < t0) {
            for (int j = 0; j < Math.pow(size, 2); j++) {
                if (!longTimeTabu.contains(bestIndividual)) {
                    longTimeTabu.add(bestIndividual);
                }
                for (int i = 0; i < Math.log(size); i++) {
                    vecino.mutate(i);
                    vecino.calculateFitness();
                    if (!tabu.contains(vecino) && !longTimeTabu.contains(vecino) && vecino.compareTo(posible) < 0) {
                        try {
                            posible = vecino.clone();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(TabuSearch.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (posible.compareTo(bestIndividual) < 0) {
                    try {
                        bestIndividual = posible.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(TabuSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tabu.add(posible);
                if (tabu.size() > Math.log(size)) {
                    tabu.poll();
                }
                it++;
            }
            if (longTimeTabu.size() > 100) {
                longTimeTabu.poll();
            }
        }
    }

}
