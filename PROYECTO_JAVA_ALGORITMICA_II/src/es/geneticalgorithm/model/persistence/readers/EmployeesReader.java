/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.persistence.readers;

import es.geneticalgorithm.model.Empleado;
import es.geneticalgorithm.model.persistence.FileManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que lee instancias de empleados o médicos
 *
 * @author Quini Roiz
 */
public class EmployeesReader {

    private static final String PATH = "data/empleados";

    /**
     * Lectura de fichereos para empleados. Lee y crea objetos a partir de lo
     * leído
     *
     * @param nMedicos tamaño de la instancia
     * @return Listado leído
     */
    public static List<Empleado> lecturaDeFicheros(int nMedicos) {
        List<Empleado> employees = new ArrayList<>();
        try {
            String[] data = FileManager.readFile(PATH + "_" + nMedicos + ".csv").split("\n");

            Empleado employee;
            String[] attrs;
            Calendar cal;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 1; i < data.length; i++) {
                attrs = data[i].split(";");
                cal = Calendar.getInstance();
                cal.setTime(sdf.parse(attrs[13]));
                employee = new Empleado(Integer.valueOf(attrs[0]), attrs[1], attrs[2], attrs[3], attrs[5], attrs[4].charAt(0),
                            attrs[6], attrs[7], attrs[8], Integer.valueOf(attrs[9]), attrs[10],
                            attrs[11], attrs[12], cal, Integer.valueOf(attrs[14]), Integer.valueOf(attrs[15]), Integer.valueOf(attrs[17]),
                            Integer.valueOf(attrs[16]));
                employees.add(employee);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeesReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employees;
    }
}
