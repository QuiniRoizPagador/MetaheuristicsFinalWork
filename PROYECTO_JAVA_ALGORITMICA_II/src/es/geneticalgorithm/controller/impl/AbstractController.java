/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.controller.impl;

import es.geneticalgorithm.controller.IController;
import es.geneticalgorithm.model.service.IModel;
import es.geneticalgorithm.view.IView;
import java.util.LinkedList;
import java.util.List;

/**
 * Controladora abstracta principal de la aplicación.
 *
 * @param <M> Tipo modelo
 * @param <V> Tipo Vista
 * @see IController
 *
 * @author Quini Roiz
 */
public abstract class AbstractController<M extends IModel, V extends IView> implements IController<V, M> {

    protected M model;
    protected final List<V> views = new LinkedList<>();

    @Override
    public void start() {
        views.forEach((v) -> {
            v.display();
        });
    }

    @Override
    public void setup(M model, List<V> views) {
        this.model = model;
        addViews(views);
        model.setController(this);
    }

    protected void addViews(List<V> views) {
        views.forEach((view) -> {
            addView(view);
        });
    }

    @Override
    public void addView(V v) {
        v.setModel(model);
        v.addController(this);
        views.add(v);
    }

    @Override
    public void removeView(V v) {
        views.remove(v);
    }

    @Override
    public M getModel() {
        return model;
    }

    @Override
    public void notifyViews() {
        views.forEach((view) -> {
            view.dataModelChanged();
        });
    }
}
