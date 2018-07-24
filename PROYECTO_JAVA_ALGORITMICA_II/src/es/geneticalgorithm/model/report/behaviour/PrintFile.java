/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report.behaviour;

import es.geneticalgorithm.model.persistence.FileManager;
import es.geneticalgorithm.model.report.ReportIndividual;
import es.geneticalgorithm.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Implementación del comportamiento de impresión en txt
 *
 *
 * @author Quini Roiz
 */
public class PrintFile implements IPrintBehaviour<ReportIndividual> {

    @Override
    public String print(int type, int problemType, ReportIndividual o, boolean async) throws Exception { // print a report on file
        Calendar cal = Calendar.getInstance();
        String algorithm = "";
        switch (type) {
            case Utils.GENETIC_ALGORITHM_TYPE:
                algorithm = "GA_";
                break;
            case Utils.SIMULATED_ANNEALING_TYPE:
                algorithm = "SA_";
                break;
            case Utils.TABU_SEARCH_TYPE:
                algorithm = "TS_";
                break;
            case Utils.ANT_COLONY_OPTIMIZATION:
                algorithm = "ACO_";
                break;
            case Utils.MEMETIC_ALGORITHM_TYPE:
                algorithm = "MEMETIC";
                break;
        }
        String formato = algorithm + "__" + cal.get(Calendar.DAY_OF_MONTH) + "_" + cal.get(Calendar.MONTH) + "_" + cal.get(Calendar.YEAR) + "__" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND);
        String filepath = Utils.REPORTPATH + "/txt/" + formato + ".txt";
        FileManager.createFile(filepath);
        String res = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:S");
        res += o.getInd().toString() + "\t" + sdf.format(o.getTime().getTime()) + "\tIteración: " + (int) o.getIteration() + "\n\n";

        FileManager.writeFile(filepath, res);
        return filepath;
    }
}
