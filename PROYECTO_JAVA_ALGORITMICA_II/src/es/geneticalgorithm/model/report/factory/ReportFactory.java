/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report.factory;

import es.geneticalgorithm.model.report.Report;
import es.geneticalgorithm.model.report.ReportFile;
import es.geneticalgorithm.model.report.ReportPDF;
import es.geneticalgorithm.util.Utils;

/**
 * Clase que utiliza el patrón factoría, creando un report específico a partir
 * de unos parámetros recibidos.
 *
 * @see Report
 *
 *
 * @author Quini Roiz
 */
public class ReportFactory {

    /**
     * Método factoría que creará un tipo de informe acorde a la funcionalidad
     * requerida por parámetros.
     *
     * @param type tipo del report a crear
     * @param alg algoritmo utilizado en la ejecución
     * @param problemType tipo de problema resuelto
     * @param o objeto a imprimir
     * @param async si fue ejecutado asíncronamente o no
     * @return Instancia del informe que implementará el patrón estrategia.
     */
    public static Report createReport(int type, int alg, int problemType, Object o, boolean async) {
        Report report = null;
        switch (type) {
            case Utils.REPORT_FILE_TYPE:
                report = new ReportFile(alg, problemType, o, async);
                break;
            case Utils.REPORT_PDF_TYPE:
                report = new ReportPDF(alg, problemType, o, async);
                break;
        }
        return report;
    }
}
