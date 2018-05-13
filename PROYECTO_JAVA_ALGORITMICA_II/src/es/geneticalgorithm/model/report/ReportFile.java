/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report;

import es.geneticalgorithm.model.report.behaviour.PrintFile;

/**
 * Clase específica que imprimira en TXT
 *
 * @param <E> tipo genérico del objeto a imprimir
 *
 *
 * @author Quini Roiz
 */
public class ReportFile<E> extends Report<E> {

    public ReportFile(int type, int problemType, E o, boolean async) {
        super(type, problemType, new PrintFile(), o, async);
    }
}
