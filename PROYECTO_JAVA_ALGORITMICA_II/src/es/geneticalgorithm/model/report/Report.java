/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report;

import es.geneticalgorithm.model.report.behaviour.IPrintBehaviour;

/**
 * Clase abstracta que será utilizada para la factoría de Reports
 *
 * @param <E> Tipo genérico con el que se trabajará en la factoría de reports
 * para diferentes tipos de exportaciones.
 *
 * @see IPrintBehaviour
 *
 * @author Quini Roiz
 */
public abstract class Report<E> {

    protected E o;
    protected int type;
    protected int problemType;
    protected IPrintBehaviour<E> printBehaviour;
    protected boolean async;

    /**
     * Constructor único de esta clase que creará un tipo de report.
     *
     * @param type tipo de algoritmo utilizado
     * @param problemType problema resuelto
     * @param printBehaviour comportamiento de report (PDF, TXT..)
     * @param o Objeto a imprimir
     * @param async si fue asíncrona o no la ejecución
     *
     * @see IPrintBehaviour
     */
    public Report(int type, int problemType, IPrintBehaviour printBehaviour, E o, boolean async) {
        this.type = type;
        this.problemType = problemType;
        this.o = o;
        this.printBehaviour = printBehaviour;
        this.async = async;
    }

    /**
     * Parte del patrón estrategia
     *
     * @param printBehaviour Estrategia escogida
     */
    public void setPrintBehaviour(IPrintBehaviour printBehaviour) {
        this.printBehaviour = printBehaviour;
    }

    /**
     * Ejecución del patrón estrategia
     *
     * @return String con la ruta de la impresión
     * @throws Exception en caso de cualquier error
     */
    public String print() throws Exception {
        return printBehaviour.print(type, problemType, o, async);
    }
}
