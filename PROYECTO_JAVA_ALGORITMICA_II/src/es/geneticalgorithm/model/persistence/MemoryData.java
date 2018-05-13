/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import es.geneticalgorithm.model.persistence.readers.EmployeesReader;
import es.geneticalgorithm.model.persistence.readers.ClientReader;
import java.util.List;
import java.util.Observable;
import es.geneticalgorithm.model.Cliente;
import es.geneticalgorithm.model.Empleado;

/**
 * Clase Singleton que contendrá objetos con los que trabajar en memoria
 *
 *
 * @author Quini Roiz
 */
public class MemoryData extends Observable {

    private List<Cliente> clients;
    private List<Empleado> employees;
    private Float[][] distances;
    private static MemoryData instance;
    private double worstDist;
    private double worstCost;

    private MemoryData() {
        clients = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public static MemoryData getInstance() {
        if (instance == null) {
            instance = new MemoryData();
        }
        return instance;
    }

    /**
     * @return the clients
     */
    public List<Cliente> getClients() {
        return clients;
    }

    /**
     * @return the employees
     */
    public List<Empleado> getEmployees() {
        return employees;
    }

    /**
     * Matriz distancias
     *
     * @return la matriz de las distancias
     */
    public Float[][] getDistances() {
        return distances;
    }

    /**
     * Lee los datos de los ficheros CSV
     *
     * @param nPacientes número de pacientes para la instancia
     * @param nMedicos número de médicos para la instancia
     */
    public void readData(int nPacientes, int nMedicos) {
        boolean leido = false;
        if (nPacientes != clients.size()) {
            leido = true;
            // leo fichero y cargo en memoria
            clients = ClientReader.lecturaDeFicheros(nPacientes);
            // mantengo ordenado
            Collections.sort(clients);
        }
        if (nMedicos != employees.size()) {
            leido = true;
            // leo fichero y cargo en memoria
            employees = EmployeesReader.lecturaDeFicheros(nMedicos);
            // mantengo ordenado
            Collections.sort(employees);
        }
        if (leido) {
            // calculo la matriz distancias
            distances = new Float[clients.size()][employees.size()];
            for (int i = 0; i < clients.size(); i++) {
                for (int j = 0; j < employees.size(); j++) {
                    distances[i][j] = (float) (Math.sqrt(Math.pow(clients.get(i).getDir_x() - employees.get(j).getDir_x(), 2)
                                + Math.pow(clients.get(i).getDir_y() - employees.get(j).getDir_y(), 2)));
                }
            }
            // calculo el peor coste
            worstCost = employees.parallelStream().mapToDouble((e) -> e.getCoste()).sum();
            worstDist = 0;
            // calculo la peor distancia
            for (int i = 0; i < clients.size(); i++) {
                Float[] d = distances[i];
                List<Float> aux = Arrays.asList(d);
                worstDist += Collections.max(aux);
            }
        }
        // actualizo observador
        setChanged();
        notifyObservers();
    }

    /**
     * @return peor coste
     */
    public double getWorstCost() {
        return worstCost;
    }

    /**
     * @return peor distancia
     */
    public double getWorstDist() {
        return worstDist;
    }

}
