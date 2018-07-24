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
import java.util.stream.Collectors;

/**
 * Clase Individual para problemas de asignación cuadrática sin utilizar
 * paralelismo, es decir, de forma secuencial.
 *
 * @see AbstractIndividual
 * @see Cloneable
 *
 *
 * @author Quini Roiz
 */
public class AsyncQuadraticIndividual extends AbstractIndividual implements Cloneable {

    /**
     * Constructor por defecto
     */
    public AsyncQuadraticIndividual() {
        super(new MedicalBehaviour());
    }

    /**
     * Constructor que copia el genotipo
     *
     * @param newGenotype genotipo a inicializar
     */
    public AsyncQuadraticIndividual(List<Integer> newGenotype) {
        super(new MedicalBehaviour(), newGenotype);
    }

    /**
     * Constructor que copia el genotipo y el fitness
     *
     * @param newGenotype genotipo a copiar
     * @param fitness fitness a copiar
     */
    public AsyncQuadraticIndividual(List<Integer> newGenotype, double fitness) {
        super(new MedicalBehaviour(), newGenotype, fitness);
    }

    @Override
    public AsyncQuadraticIndividual clone() throws CloneNotSupportedException {
        return new AsyncQuadraticIndividual(genotype, fitness);
    }

    @Override
    public void calculateFitness() {
        fitness = fitnessBehaviour.calculateFitness(genotype);
    }

    @Override
    public boolean isValid() {
        int[] assignations = new int[MemoryData.getInstance().getEmployees().size()];
        return !genotype.parallelStream().anyMatch(t -> ++assignations[t] > MemoryData.getInstance().getEmployees().get(t).getMax_clientes());
    }

    @Override
    public void generateData() {
        Queue<Cliente> clients = new LinkedList<>();
        clients.addAll(MemoryData.getInstance().getClients());
        int[] assignations = new int[MemoryData.getInstance().getEmployees().size()];

        genotype = clients.parallelStream().map((cl) -> {
            int j;
            do {
                j = rn.nextInt(MemoryData.getInstance().getEmployees().size());
            } while (assignations[j] + 1 > MemoryData.getInstance().getEmployees().get(j).getMax_clientes());
            assignations[j]++;
            return j;
        }).collect(Collectors.toList());
    }

    @Override
    public void mutate() {
        if (rn.nextFloat() > 0.4) {
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
