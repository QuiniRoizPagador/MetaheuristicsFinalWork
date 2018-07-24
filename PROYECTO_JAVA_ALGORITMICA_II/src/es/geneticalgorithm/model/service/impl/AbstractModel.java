/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.service.impl;

import es.geneticalgorithm.controller.IController;
import es.geneticalgorithm.model.service.IModel;

/**
 * Clase abstracta para los modelos.
 *
 * @param <C> Controlador
 * @see IController
 * @see IModel
 *
 *
 * @author Quini Roiz
 */
public abstract class AbstractModel<C extends IController> implements IModel<C> {

    protected C controller;

    @Override
    public void setController(C controller) {
        this.controller = controller;
    }

}
