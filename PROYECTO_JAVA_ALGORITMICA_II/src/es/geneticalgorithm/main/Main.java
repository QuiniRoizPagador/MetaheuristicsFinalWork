/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.main;

import es.geneticalgorithm.controller.IAlgorithmController;
import es.geneticalgorithm.controller.IReportController;
import es.geneticalgorithm.controller.impl.AlgorithmController;
import es.geneticalgorithm.controller.impl.ReportController;
import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.model.service.IReportService;
import es.geneticalgorithm.model.service.impl.AlgorithmService;
import es.geneticalgorithm.model.service.impl.ReportService;
import es.geneticalgorithm.view.IView;
import es.geneticalgorithm.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase principal de la aplicación, encargada de crear el modelo, las vistas y
 * el controlador. Tras crearlas conecta todas entre ellas adecuadamente para un
 * correcto funcionamiento.
 *
 * @see IAlgorithmService
 * @see IView
 *
 * @author Quini Roiz
 */
public class Main {

    /**
     * Método main que arrancará la aplicación con los parámetros configurados
     * correctamente.
     *
     * @param args argumentos recibidos desde la consola de comandos.
     */
    public static void main(String[] args) {

        IAlgorithmService model = new AlgorithmService();
        IReportService reportmodel = new ReportService();

        IView view1 = new View();
        //IView view2 = new Chart();
        List<IView> views = new LinkedList<>();
        //views.add(view2);
        views.add(view1);

        IAlgorithmController c = new AlgorithmController();
        c.setup(model, views);
        
        //views.remove(view2);

        IReportController rc = new ReportController();
        rc.setup(reportmodel, views);
        c.start();
    }
}
