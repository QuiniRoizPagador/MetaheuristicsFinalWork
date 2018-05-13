/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.individuals;

import es.geneticalgorithm.model.Cliente;
import es.geneticalgorithm.model.algorithm.individuals.behaviours.MedicalBehaviour;
import es.geneticalgorithm.model.persistence.MemoryData;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Clase Individual para problemas de asignación cuadrática para utilizar
 * paralelismo.
 *
 * @see AbstractIndividual
 * @see Cloneable
 *
 *
 * @author Quini Roiz
 */
public class SyncQuadraticIndividual extends AbstractIndividual implements Cloneable {

    public SyncQuadraticIndividual() {
        super(new MedicalBehaviour());
    }

    public SyncQuadraticIndividual(List<Integer> newGenotype) {
        super(new MedicalBehaviour(), newGenotype);
    }

    public SyncQuadraticIndividual(List<Integer> newGenotype, double fitness) {
        super(new MedicalBehaviour(), newGenotype, fitness);
    }

    @Override
    public SyncQuadraticIndividual clone() throws CloneNotSupportedException {
        return new SyncQuadraticIndividual(genotype, fitness);
    }

    @Override
    public void calculateFitness() {
        fitness = fitnessBehaviour.calculateFitness(genotype);
    }

    @Override
    public boolean isValid() {
        int[] assignations = new int[MemoryData.getInstance().getEmployees().size()];
        int i = 0;
        boolean valid = true;
        while (valid && i < genotype.size()) {
            assignations[genotype.get(i)]++;
            if (assignations[genotype.get(i)] > MemoryData.getInstance().getEmployees().get(genotype.get(i)).getMax_clientes()) {
                valid = false;
            } else {
                i++;
            }
        }

        return valid;
    }

    @Override
    public void generateData() {
        Queue<Cliente> clients = new LinkedList<>();
        clients.addAll(MemoryData.getInstance().getClients());
        int i, j = rn.nextInt(MemoryData.getInstance().getEmployees().size());
        Cliente c;
        int[] assignations = new int[MemoryData.getInstance().getEmployees().size()];
        while (!clients.isEmpty()) {
            c = clients.poll();
            i = MemoryData.getInstance().getClients().indexOf(c);
            do {
                j = rn.nextInt(MemoryData.getInstance().getEmployees().size());
            } while (assignations[j] + 1 > MemoryData.getInstance().getEmployees().get(j).getMax_clientes());
            genotype.set(i, j);
            assignations[j]++;
        }
    }

    @Override
    public void mutate() {
        if (rn.nextFloat() > 0.5) {
            int i1 = rn.nextInt(MemoryData.getInstance().getClients().size());
            mutate(i1);
        } else {
            Collections.reverse(genotype);
        }
    }

    @Override
    public void mutate(int i) {
        int n2 = rn.nextInt(MemoryData.getInstance().getEmployees().size());
        genotype.set(i, n2);
    }
}
