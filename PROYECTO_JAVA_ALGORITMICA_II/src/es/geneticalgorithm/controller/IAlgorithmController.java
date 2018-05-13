/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.controller;

import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.view.IView;

/**
 * Interfaz para el controlador principal de la aplicación.
 *
 * @param <V> Tipo Vista
 * @param <M> Tipo IAlgorithmService(Modelo)
 * @see IAlgorithmController
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public interface IAlgorithmController<M extends IAlgorithmService, V extends IView> extends IController<V, M> {

    /**
     * Prepara un algoritmo para ser ejecutado, recibiendo como parámetros el
     * tipo de algoritmo y si éste se ejecutará secuencialmente o de forma
     * paralela.
     *
     * @param algType tipo de algoritmo a preparar.
     * @param async <code>true</code> si es paralela la forma de ejecutarse del
     * algoritmo.
     * @throws java.lang.CloneNotSupportedException en caso de que se haya
     * producido un error al realizar una clonación en las clases individuo.
     *
     * @see CloneNotSupportedException
     */
    void selectProblemType(int algType, boolean async) throws CloneNotSupportedException;

    /**
     * Ejecutará el algoritmo previamente preparado.
     */
    void executeAlgorithm();

    /**
     * Leerá los datos recibidos por parámetros en la clase MemoryData del
     * modelo, que contiene las funciones y datos relacionados directamente con
     * los Médicos y Pacientes.
     *
     * @param nPacientes tamaño de los pacientes a leer.
     * @param nMedicos tamaño de los médicos a leer.
     */
    void readData(int nPacientes, int nMedicos);

    /**
     * Notificará a las vistas cuando haya terminado de ejecutarse el algoritmo
     * en ejecución.
     */
    void notifyFinished();

    /**
     * Notificará a las vistas cuando arranque la ejecución del algoritmo
     * previamente preparado.
     */
    void notifyStarted();
}
