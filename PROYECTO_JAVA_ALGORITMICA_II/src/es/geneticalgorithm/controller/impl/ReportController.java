/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.controller.impl;

import es.geneticalgorithm.controller.IReportController;
import es.geneticalgorithm.model.report.ReportIndividual;
import es.geneticalgorithm.model.service.IReportService;
import es.geneticalgorithm.view.IView;
import java.util.List;

/**
 * Controlador encargado de los informes.
 *
 * @see IReportController
 *
 * @author Quini Roiz
 */
public class ReportController extends AbstractController<IReportService, IView> implements IReportController<IReportService, IView> {

    @Override
    public void setup(IReportService model, List<IView> views) {
        this.model = model;
        addViews(views);
    }

    @Override
    public void addView(IView v) {
        v.addController(this);
        views.add(v);
    }
    

    @Override
    public void exportFile(int algType, int problemType, ReportIndividual o, boolean async) throws Exception {
        model.exportFile(algType, problemType, o, async);
    }

    @Override
    public String exportPDF(int algType, int problemType, ReportIndividual o, boolean async) throws Exception {
        return model.exportPDF(algType, problemType, o, async);
    }
}
