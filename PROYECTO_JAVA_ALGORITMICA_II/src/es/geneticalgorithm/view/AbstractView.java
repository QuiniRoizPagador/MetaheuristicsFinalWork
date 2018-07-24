/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.view;

import es.geneticalgorithm.controller.IController;
import es.geneticalgorithm.controller.impl.AbstractController;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.model.service.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta para las vistas de la aplicación.
 *
 * @param <M> Tipo modelo
 * @param <C> Tipo Controladora
 * @see IController
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public abstract class AbstractView<M extends IModel, C extends AbstractController> extends javax.swing.JFrame implements IView<C, M> {

    protected M model;
    protected List<C> controllers;
    protected AbstractIndividual best;
    protected long startTime;

    public AbstractView() {
        controllers = new ArrayList<>();

    }

    @Override
    public void display() {
        setVisible(true);
    }

    @Override
    public List<C> getControllers() {
        return controllers;
    }

    @Override
    public void addController(C controller) {
        controllers.add(controller);
    }

    @Override
    public M getModel() {
        return model;
    }

    @Override
    public void setModel(M model) {
        this.model = model;
    }

    @Override
    public abstract void dataModelChanged();

    @Override
    public abstract void notifyFinished();

    @Override
    public abstract void notifyStarted();
}
