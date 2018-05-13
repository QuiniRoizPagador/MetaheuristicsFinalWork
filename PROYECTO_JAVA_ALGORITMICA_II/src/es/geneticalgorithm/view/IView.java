/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.view;

import es.geneticalgorithm.controller.impl.AbstractController;
import es.geneticalgorithm.controller.IController;
import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.model.service.IModel;
import java.util.List;

/**
 * Interfaz para las vistas de la aplicación.
 *
 * @param <C> Tipo controladora
 * @param <M> Tipo IAlgorithmService (modelo)
 * @see IController
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public interface IView<C extends AbstractController, M extends IModel> {

    /**
     * Devuelve un tipo <code>IController</code> con el controlador principal de
     * la aplicación.
     *
     * @return el controlador principal de la aplicación.
     * @see IController
     */
    List<C> getControllers();

    /**
     * Actualiza el valor del controlador de la vista recibiendo el nuevo
     * controlador por parámetros.
     *
     * @param controller el controlador nuevo.
     * @see IController
     */
    void addController(C controller);

    /**
     * Devuelve un tipo <code>IAlgorithmService</code> con el model de la
     * aplicación.
     *
     * @return el modelo principal de la aplicación.
     * @see IAlgorithmService
     */
    M getModel();

    /**
     * Actualiza el valor del modelo de la vista recibiendo el nuevo modelo por
     * parámetros.
     *
     * @param model el modelo nuevo.
     * @see IAlgorithmService
     */
    void setModel(M model);

    /**
     * Muestra la pantalla. En cada implementación se decidirá qué valores
     * pueden tener sentido actualizar también antes de esta visualización.
     *
     */
    void display();

    /**
     * Disparador que recibe la notificación de cambio de datos en el modelo.
     * Consultará en el modelo los datos a actualizar del algoritmo en
     * ejecución.
     */
    void dataModelChanged();

    /**
     * Disparador que recibe la notificación de comienzo de procesamiento del
     * algoritmo.
     */
    void notifyStarted();

    /**
     * Disparador que recibe la notificación de finalización de ejecución en un
     * algoritmo del modelo.
     */
    void notifyFinished();

}
