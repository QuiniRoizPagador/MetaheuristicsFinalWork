/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.controller;

import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.model.service.IModel;
import es.geneticalgorithm.view.IView;

import java.util.List;

/**
 * Interfaz para el controlador abstracto de la aplicación.
 *
 * @param <V> Tipo Vista
 * @param <M> Tipo IAlgorithmService(Modelo)
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public interface IController<V extends IView, M extends IModel> {

    /**
     * Arranca el sistema, ejecutando la orden de visibilidad de las vistas.
     */
    void start();

    /**
     * Configura los parámetros necesarios para la aplicación.
     *
     * @param model la clase que se encarga del modelo de la aplicación.
     * @param views listado de vistas que componen la aplicación.
     *
     * @see IAlgorithmService
     * @see List
     * @see IView
     */
    void setup(M model, List<V> views);

    /**
     * Actualiza el listado de vistas y añade una nueva.
     *
     * @param v nueva vista a añadir al listado de vistas del controlador.
     *
     * @see IView
     */
    void addView(V v);

    /**
     * Elimina la vista del listado de vistas del controlador.
     *
     * @param v vista a eliminar del listado de vistas del controlador.
     *
     * @see IView
     */
    void removeView(V v);

    /**
     * Devuelve una referencia al modelo de la aplicación.
     *
     * @return modelo de la aplicación.
     *
     * @see IAlgorithmService
     */
    M getModel();

    /**
     * Notificará a las vistas cuando se hayan actualizado los datos del
     * algoritmo en ejecución.
     */
    void notifyViews();

}
