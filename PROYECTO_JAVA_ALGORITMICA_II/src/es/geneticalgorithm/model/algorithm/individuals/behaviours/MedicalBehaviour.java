/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.individuals.behaviours;

import es.geneticalgorithm.model.persistence.MemoryData;

import java.util.List;

/**
 * Clase que implementa el comportamiento de los fitness para un cálculo
 * particular a un tipo de problema a resolver.
 *
 * Este comportamiento es del problema de asignación de médicos y pacientes.
 *
 * @see FitnessBehaviour
 *
 *
 * @author Quini Roiz
 */
public class MedicalBehaviour implements FitnessBehaviour {

    @Override
    public double calculateFitness(List<Integer> genotype) {
        double fitness;
        double dist = 0;
        double coste = 0;
        boolean[] medicals = new boolean[MemoryData.getInstance().getEmployees().size()];
        // sumatorio de los costes de los médicos contratados
        // y sumatorio de las distancias de los pacientes a los médicos asignados
        for (int i = 0; i < genotype.size(); i++) {
            if (!medicals[genotype.get(i)]) {
                coste += MemoryData.getInstance().getEmployees().get(genotype.get(i)).getCoste();
                medicals[genotype.get(i)] = true;
            }
            dist += MemoryData.getInstance().getDistances()[i][genotype.get(i)];
        }
        // coste entre el peor caso de costes, por distancias entre peor caso de distancias
        fitness = (coste / MemoryData.getInstance().getWorstCost())
                    * (dist / MemoryData.getInstance().getWorstDist());
        return fitness;
    }
}
