/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report.behaviour;

/**
 * Interfaz para los comportamientos de impresión
 *
 * @param <E> Tipo de objeto a imprimir
 *
 *
 * @author Quini Roiz
 */
public interface IPrintBehaviour<E> {

    /**
     * Método del patrón estrategia que se encargará de realizar cada algoritmo
     * en particular en sus implementaciones.
     *
     * @param best contenido a imprimir.
     * @param problemType problema resuelto
     * @param type tipo del algoritmo
     * @param async si fue ejecutado asíncronamente
     * @return <code>String</code> con la ruta en la que se guardó el fichero
     * @throws Exception En caso de error.
     */
    String print(int type, int problemType, E best, boolean async) throws Exception;
}
