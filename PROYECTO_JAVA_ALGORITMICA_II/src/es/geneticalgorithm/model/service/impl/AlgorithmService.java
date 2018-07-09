/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.service.impl;

import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.controller.IAlgorithmController;
import es.geneticalgorithm.model.algorithm.IAlgorithm;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import es.geneticalgorithm.model.algorithm.factory.AlgorithmFactory;
import es.geneticalgorithm.model.persistence.MemoryData;

/**
 * Clase que implementa la interfaz IAlgorithmService para el modelo de la
 * aplicación.
 *
 * @see IAlgorithm
 * @see IAlgorithmController
 *
 *
 * @author Quini Roiz
 */
public class AlgorithmService extends AbstractModel<IAlgorithmController> implements IAlgorithmService<IAlgorithmController> {

    private IAlgorithm alg;
    private ExecutorService service;

    public AlgorithmService() {
        MemoryData.getInstance().addObserver(this);
    }

    @Override
    public void notifyObservers() {
        controller.notifyViews();
    }

    @Override
    public void executeAlgorithm() throws InterruptedException, UnsupportedOperationException {
        service = Executors.newSingleThreadExecutor();
        service.execute((Runnable) alg);
    }

    @Override
    public IAlgorithm getAlgorithm() {
        return alg;
    }

    @Override
    public void prepare(int algType, boolean async) throws CloneNotSupportedException, UnsupportedOperationException {
        alg = AlgorithmFactory.createAlgorithm(algType, this, async);
        notifyStarted();
    }

    @Override
    public void notifyViews() {
        controller.notifyViews();
    }

    @Override
    public void notifyStarted() {
        controller.notifyStarted();
    }

    @Override
    public void notifyFinished() {
        controller.notifyFinished();
    }

    @Override
    public void update(Observable o, Object arg) {
        notifyStarted();
    }

    @Override
    public void readData(int nPacientes, int nMedicos) {
        MemoryData.getInstance().readData(nPacientes, nMedicos);
    }

}
