/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.controller;

import es.geneticalgorithm.model.report.ReportIndividual;
import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.model.service.IReportService;
import es.geneticalgorithm.view.IView;

/**
 * Interfaz para el controlador encargado de los informes de la aplicación.
 *
 * @param <V> Tipo Vista
 * @param <M> Tipo IAlgorithmService(Modelo)
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public interface IReportController<M extends IReportService, V extends IView> extends IController<V, M> {

    /**
     * Enviará la señal de exportar un fichero al modelo con los datos del
     * algoritmo recibidos por parámetros.
     *
     * @param algType tipo del algoritmo ejecutado.
     * @param problemType tipo del problema a resolver.
     * @param o último registro anotado del algoritmo con la mejor solución
     * encontrada.
     * @param async <code>true</code> si es paralela la forma de ejecución del
     * algoritmo.
     * @throws Exception en caso de algún tipo de error
     *
     * @see ReportIndividual
     */
    void exportFile(int algType, int problemType, ReportIndividual o, boolean async) throws Exception;

    /**
     * Enviará la señal de exportar un archivo pdf al modelo con los datos del
     * algoritmo recibidos por parámetros.
     *
     * @param algType tipo del algoritmo ejecutado.
     * @param problemType tipo del problema a resolver.
     * @param o último registro anotado del algoritmo con la mejor solución
     * encontrada.
     * @param async <code>true</code> si es paralela la forma de ejecución del
     * algoritmo.
     * @throws Exception en caso de algún tipo de error
     *
     * @return ruta hasta el fichero para abrirlo.
     *
     * @see ReportIndividual
     */
    String exportPDF(int algType, int problemType, ReportIndividual o, boolean async) throws Exception;
}
