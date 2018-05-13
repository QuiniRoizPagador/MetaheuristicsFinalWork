/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report;

import java.util.Calendar;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;

/**
 * Clase que almacenará los datos de una solución a imprimir
 *
 * @see AbstractIndividual
 *
 * @author Quini Roiz
 */
public class ReportIndividual {

    private final AbstractIndividual ind;
    private final Calendar time;
    private final double iteration;

    /**
     * Constructor que formará el report para las impresiones.
     *
     * @param ind Solución encontrada
     * @param time tiempo tardado en encontrarla
     * @param iteration iteración en la que se encontró la solución
     */
    public ReportIndividual(AbstractIndividual ind, Calendar time, double iteration) {
        this.ind = ind;
        this.time = time;
        this.iteration = iteration;
    }

    /**
     * @return the ind
     */
    public AbstractIndividual getInd() {
        return ind;
    }

    /**
     * @return the time
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * @return the iteration
     */
    public double getIteration() {
        return iteration;
    }

    @Override
    public String toString() {
        return String.valueOf(getInd().getFitness());
    }
}
