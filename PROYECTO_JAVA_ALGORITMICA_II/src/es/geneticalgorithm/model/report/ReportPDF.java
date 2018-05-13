/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report;

import es.geneticalgorithm.model.report.behaviour.PrintHTML5PDF;

/**
 * Clase específica que imprimira en PDF
 *
 * @param <E> tipo genérico del objeto a imprimir
 *
 *
 * @author Quini Roiz
 */
public class ReportPDF<E> extends Report<E> {

    public ReportPDF(int type, int problemType, E o, boolean async) {
        super(type, problemType, new PrintHTML5PDF(), o, async);
    }
}
