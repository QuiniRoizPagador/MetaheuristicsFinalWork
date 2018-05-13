/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.service.IAlgorithmService;

/**
 * Clase abstracta desde la que se heredarán los algoritmos.
 *
 * @see AbstractIndividual
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public abstract class Algorithm implements IAlgorithm {

    private IAlgorithmService model;
    protected boolean converge;
    protected AbstractIndividual bestIndividual;
    protected double posExito;
    protected double it;
    protected int repeticiones;
    protected AbstractIndividual posible;
    protected final boolean async;

    /**
     * Default constructor
     *
     * @param async variable que determinará si el algoritmo es paralelo o no.
     */
    public Algorithm(boolean async) {
        this.async = async;
    }

    @Override
    public abstract void prepare() throws CloneNotSupportedException;

    @Override
    public abstract void run() throws CloneNotSupportedException;

    @Override
    public void notifyObservers() {
        if (model != null) {
            model.notifyViews();
        }
    }

    @Override
    public AbstractIndividual getBest() {
        return bestIndividual;
    }

    @Override
    public void setModel(IAlgorithmService m) {
        model = m;
    }

    @Override
    public void notifyStarted() {
        if (model != null) {
            model.notifyStarted();
        }
    }

    @Override
    public void notifyFinished() {
        if (model != null) {
            model.notifyFinished();
        }
    }

    @Override
    public double getPosExito() {
        return posExito;
    }

    @Override
    public abstract double getPorcentaje();

    @Override
    public int compareTo(Algorithm o) {
        return o.getBest().compareTo(getBest());
    }

}
