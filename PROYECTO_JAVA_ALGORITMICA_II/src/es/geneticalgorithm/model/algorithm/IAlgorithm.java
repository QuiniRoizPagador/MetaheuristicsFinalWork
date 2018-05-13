/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.service.IAlgorithmService;

/**
 * Interfaz para los algoritmos de la aplicación.
 *
 * @see IAlgorithmService
 * @see AbstractIndividual
 *
 *
 * @author Quini Roiz
 */
public interface IAlgorithm extends Comparable<Algorithm> {

    /**
     * Prepara los datos (inicializa la población).
     *
     * @throws java.lang.CloneNotSupportedException si hay error en clonación.
     */
    void prepare() throws CloneNotSupportedException;

    /**
     * Arranca el algoritmo y lo hace correr hasta converger.
     *
     * @throws java.lang.CloneNotSupportedException si hay error en clonación.
     */
    void run() throws CloneNotSupportedException;

    /**
     * Muestra la solución encontrada tras ejecutar el algoritmo.
     */
    void notifyObservers();

    /**
     * Devuelve una referencia al mejor individuo encontrado en el algoritmo.
     *
     * @return individuo con la mejor puntuación o fitness.
     *
     * @see AbstractIndividual
     */
    AbstractIndividual getBest();

    /**
     * Actualiza el modelo con el que esta clase se hable para transferir datos
     * y notificaciones a las capas superiores.
     *
     * @param m referencia al modelo de la aplicación.
     *
     * @see IAlgorithmService
     */
    void setModel(IAlgorithmService m);

    /**
     * Notificará al modelo cuando haya terminado de ejecutarse el algoritmo en
     * ejecución.
     */
    void notifyFinished();

    /**
     * Se devuelve el porcentaje de algoritmo completado.
     *
     * @return numérico <code>double</code> con el porcentaje.
     */
    double getPorcentaje();

    /**
     * Se devuelve la posición en la que se encontró la mejor solución anotada.
     *
     * @return valor numérico con la iteración de la mejor solución anotada.
     */
    double getPosExito();

    /**
     * Notificará al modelo cuando arranque la ejecución del algoritmo
     * previamente preparado.
     */
    void notifyStarted();
}
