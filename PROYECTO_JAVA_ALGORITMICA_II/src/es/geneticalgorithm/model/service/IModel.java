/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.service;

import es.geneticalgorithm.controller.IController;

/**
 * Interfaz genérica de los modelos.
 *
 * @param <C> Controlador
 * @see IController
 * @see IModel
 *
 *
 * @author Quini Roiz
 */
public interface IModel<C extends IController> {

    /**
     * Actualiza el valor del controlador del modelo recibiendo el nuevo
     * controlador por parámetros.
     *
     * @param c el controlador nuevo.
     * @see IController
     */
    void setController(C c);

}
