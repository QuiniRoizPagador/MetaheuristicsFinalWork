/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.service.impl;

import es.geneticalgorithm.controller.IReportController;
import es.geneticalgorithm.model.algorithm.IAlgorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.report.Report;
import es.geneticalgorithm.model.report.ReportIndividual;
import es.geneticalgorithm.model.report.factory.ReportFactory;
import es.geneticalgorithm.model.service.IReportService;
import es.geneticalgorithm.util.Utils;

/**
 * Clase que implementa la interfaz IAlgorithmService para el modelo de la
 * aplicación.
 *
 * @see IReportController
 * @see AbstractIndividual
 * @see IAlgorithm
 *
 *
 * @author Quini Roiz
 */
public class ReportService extends AbstractModel<IReportController> implements IReportService<IReportController> {

    @Override
    public void exportFile(int algType, int problemType, ReportIndividual o, boolean async) throws Exception {
        Report<AbstractIndividual> r = ReportFactory.createReport(Utils.REPORT_FILE_TYPE, algType, problemType, o, async);
        r.print();
    }

    @Override
    public String exportPDF(int algType, int problemType, ReportIndividual o, boolean async) throws Exception {
        Report<AbstractIndividual> r = ReportFactory.createReport(Utils.REPORT_PDF_TYPE, algType, problemType, o, async);
        return r.print();
    }
}
