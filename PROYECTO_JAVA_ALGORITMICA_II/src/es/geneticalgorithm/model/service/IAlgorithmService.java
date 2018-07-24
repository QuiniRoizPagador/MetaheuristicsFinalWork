/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.service;

import es.geneticalgorithm.controller.IAlgorithmController;
import es.geneticalgorithm.controller.IController;
import es.geneticalgorithm.model.algorithm.IAlgorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;

import java.util.Observer;

/**
 * Interfaz para el modelo de la los algoritmos.
 *
 * @param <C> Controladora
 * @see IController
 * @see AbstractIndividual
 * @see IAlgorithm
 *
 *
 * @author Quini Roiz
 */
public interface IAlgorithmService<C extends IAlgorithmController> extends Observer, IModel<C> {

    /**
     * Notifica los observadores suscritos a la clase.
     */
    void notifyObservers();

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
    void prepare(int algType, boolean async) throws CloneNotSupportedException;

    /**
     * Ejecutará el algoritmo previamente preparado.
     *
     * @throws java.lang.InterruptedException en caso de error de ejecución del
     * algoritmo
     */
    void executeAlgorithm() throws Exception;

    /**
     * Devuelve una referencia al algoritmo en ejecución.
     *
     * @return algoritmo en ejecución.
     */
    IAlgorithm getAlgorithm();

    /**
     * Notificará a las vistas cuando se hayan actualizado los datos del
     * algoritmo en ejecución.
     */
    void notifyViews();

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
     * Forzará al algoritmo en cuestión a parar.
     */
    void stopEjecutation();

}
