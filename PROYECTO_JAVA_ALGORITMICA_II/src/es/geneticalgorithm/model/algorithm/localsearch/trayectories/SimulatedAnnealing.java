/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.localsearch.trayectories;

import es.geneticalgorithm.model.algorithm.individuals.AsyncQuadraticIndividual;
import es.geneticalgorithm.model.algorithm.localsearch.HillClimbing;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.individuals.SyncQuadraticIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;

/**
 * Algoritmo de búsqueda local Enfriamiento Simulado, el cual utilizará las
 * propiedades de la termodinámica para encontrar una mejor solución en función
 * de una terperatura que se irá enfriando lentamente.
 *
 *
 * @author Quini Roiz
 */
public class SimulatedAnnealing extends HillClimbing implements Runnable {

    public SimulatedAnnealing(boolean async) {
        super(async);
    }

    @Override
    public void prepare() throws CloneNotSupportedException {
        if (async) {
            vecino = new AsyncQuadraticIndividual();
        } else {
            vecino = new SyncQuadraticIndividual();
        }
        vecino.generateData();
        vecino.calculateFitness();
        posible = vecino.clone();
        bestIndividual = vecino.clone();
        size = Math.max(MemoryData.getInstance().getClients().size(), MemoryData.getInstance().getEmployees().size());
        t0 = size * Math.pow(Math.log(size), 2);
        it = t0;
    }

    @Override
    public void run() {

        int k = 0;
        while (it >= 1) {
            try {
                // se le vuelven a generar los datos cada 100 iteraciones
                if (k % 100 == 0) {
                    vecino.generateData();
                }
                // se genera un vecino válido
                do {
                    vecino.getVecino();
                } while (!vecino.isValid());
                // se le calcula el fitness al vecino
                vecino.calculateFitness();
                // se aplica la probabilidad de aceptación
                if (acceptProbability(vecino, bestIndividual, it) > Math.random()) {
                    try {
                        posible = vecino.clone();
                        posible.calculateFitness();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // si es solución mejor encontrada se actualiza referencia
                if (posible.compareTo(bestIndividual) < 0) {
                    posExito = k;
                    bestIndividual = posible.clone();
                }
                notifyObservers();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
            }
            // enfriar temperatura
            it -= it * 0.001;
            // contador de iteración incrementado
            k++;
        }
        // notifica a la vista de haber finalizado
        notifyFinished();
    }

    @Override
    public double getPorcentaje() {
        double res = (t0 - it + 1) / t0 * 100;
        return res;
    }

    private double acceptProbability(AbstractIndividual vecino, AbstractIndividual posible, double tmp) {
        // si es mejor se devuelve 1
        if (vecino.compareTo(posible) < 0) {
            return 1.0;
        }
        // aplicamos la función de e elevado a la nueva solución - la posible 
        // anterior y se divide entre la temperatura
        return Math.exp((vecino.getFitness() - posible.getFitness()) / tmp);
    }

    @Override
    public void setIndividual(AbstractIndividual ind) throws CloneNotSupportedException {
        vecino = ind;
        posible = vecino.clone();
        bestIndividual = vecino.clone();
        size = Math.max(MemoryData.getInstance().getClients().size(), MemoryData.getInstance().getEmployees().size());
        t0 = it = Math.pow(Math.log(size), 1.7);
    }

}
