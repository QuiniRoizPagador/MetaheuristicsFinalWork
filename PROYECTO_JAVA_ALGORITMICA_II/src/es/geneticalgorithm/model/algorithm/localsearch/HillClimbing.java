/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.localsearch;

import es.geneticalgorithm.model.algorithm.Algorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;

/**
 * Algoritmo de búsqueda local Hill Climbing, el cual se encargará de buscar al
 * primer vecino más cercano con mejor solución.
 *
 *
 * @author Quini Roiz
 */
public class HillClimbing extends Algorithm {

    protected AbstractIndividual vecino;
    protected double t0;
    protected int size;

    public HillClimbing(boolean async) {
        super(async);
    }

    @Override
    public void prepare() throws CloneNotSupportedException {

    }

    @Override
    public void run() throws CloneNotSupportedException {
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < t0) {
            vecino.mutate();
            if (vecino.isValid()) {
                vecino.calculateFitness();
                if (vecino.compareTo(bestIndividual) < 0) {
                    bestIndividual = vecino.clone();
                    encontrado = true;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }

    @Override
    public double getPorcentaje() {
        return it / t0 * 100;
    }

    /**
     * Actualizará los datos del primer individuo con el que arrancar el
     * algoritmo de forma externa.
     *
     * @param ind individuo al que realizarle la mejora.
     * @throws CloneNotSupportedException en caso de error de clonación.
     *
     * @see AbstractIndividual
     */
    public void setIndividual(AbstractIndividual ind) throws CloneNotSupportedException {
        vecino = ind;
        posible = vecino.clone();
        bestIndividual = vecino.clone();
        it = 0;
        size = Math.max(MemoryData.getInstance().getClients().size(), MemoryData.getInstance().getEmployees().size());
        t0 = (int) (size * Math.pow(Math.log(size), 2));
    }

}
