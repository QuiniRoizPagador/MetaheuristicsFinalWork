/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.controller.impl;

import es.geneticalgorithm.controller.IAlgorithmController;
import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.view.IView;

/**
 * Controlador principal de la aplicación. Se encarga de cualquier control
 * relacionado con los algoritmos.
 *
 * @see IAlgorithmController
 *
 * @author Quini Roiz
 */
public class AlgorithmController extends AbstractController<IAlgorithmService, IView> implements IAlgorithmController<IAlgorithmService, IView> {

    @Override
    public void readData(int nPacientes, int nMedicos) {
        model.readData(nPacientes, nMedicos);
    }

    @Override
    public void selectProblemType(int algType, boolean async) throws CloneNotSupportedException, UnsupportedOperationException {
        model.prepare(algType, async);
    }

    @Override
    public void executeAlgorithm() throws Exception {
        try {
            model.executeAlgorithm();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void notifyFinished() {
        views.forEach((view) -> {
            view.notifyFinished();
        });
    }

    @Override
    public void notifyStarted() {
        views.forEach((view) -> {
            view.notifyStarted();
        });
    }

}
