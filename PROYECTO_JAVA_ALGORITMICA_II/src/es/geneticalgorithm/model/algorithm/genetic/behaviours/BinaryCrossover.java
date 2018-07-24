/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic.behaviours;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.individuals.AsyncQuadraticIndividual;
import es.geneticalgorithm.model.algorithm.individuals.SyncQuadraticIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;
import es.geneticalgorithm.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementación del comportamiento de cruce para cruces binarios, donde las
 * repeticiones no importan
 *
 * @author Quini Roiz
 *
 * @see CrossoverStrategy
 */
public class BinaryCrossover<I extends AbstractIndividual> implements CrossoverStrategy<I> {

    // genotipos con los que trabajaremos
    private final List<Integer> newGenotype;
    private final List<Integer> p1Genotype;
    private final List<Integer> p2Genotype;
    private I child;
    private final Random rn;

    /**
     * Constructor por defecto
     */
    public BinaryCrossover() {
        newGenotype = new ArrayList<>();
        p1Genotype = new ArrayList<>();
        p2Genotype = new ArrayList<>();
        rn = new Random();
    }

    @Override
    public I portion(I p1, I p2) {

        // copiamos los genotipos de los padres para trabajar con ellos
        p1Genotype.addAll(p1.getGenotype());
        p2Genotype.addAll(p2.getGenotype());
        // calculamos el máximo porcentaje del 
        // primer padre que se obtendrá para el hijo
        int n1, n2;
        do {
            n1 = (int) (MemoryData.getInstance().getClients().size() * Utils.PARENT_USE_PERCENT);
            n2 = (int) (MemoryData.getInstance().getClients().size() * Math.random());
        } while (n1 == n2);
        int min = Math.min(n1, n2);
        int max = Math.max(n1, n2);
        // iteraremos y tomaremos una parte de un padre
        // y el resto del otro
        for (int i = 0; i < MemoryData.getInstance().getClients().size(); i++) {
            if (i <= max && i >= min) {
                newGenotype.add(p1Genotype.get(i));
            } else {
                newGenotype.add(p2Genotype.get(i));
            }
        }
        // inicializamos al hijo
        if (p1 instanceof AsyncQuadraticIndividual) {
            child = (I) new AsyncQuadraticIndividual(newGenotype);
        } else {
            child = (I) new SyncQuadraticIndividual(newGenotype);
        }

        return child;
    }

    @Override
    public I uniform(I p1, I p2) throws CloneNotSupportedException {
        // copiamos los genotipos de los padres para trabajar con ellos
        p1Genotype.addAll(p1.getGenotype());
        p2Genotype.addAll(p2.getGenotype());
        // tiramos dado para clonar o cruzar
        if (rn.nextFloat() < Utils.CLONE_RATE) {
            // calculamos el máximo porcentaje del 
            // primer padre que se obtendrá para el hijo
            int max = (int) (MemoryData.getInstance().getClients().size() / 2);
            // tomamos porcentaje elegido de padre 1 y 2 (50%)
            for (int i = 0; i < MemoryData.getInstance().getClients().size(); i++) {
                if (i < max) {
                    newGenotype.add(p1Genotype.get(i));
                } else {
                    newGenotype.add(p2Genotype.get(i));
                }
            }
            // inicializamos al hijo
            if (p1 instanceof AsyncQuadraticIndividual) {
                child = (I) new AsyncQuadraticIndividual(newGenotype);
            } else {
                child = (I) new SyncQuadraticIndividual(newGenotype);
            }

        } else // Si hay que clonar se cogerá al padre 
        //con mejor fitness según el problema
        {
            // comparamos al mejor y clonamos
            if (p1.compareTo(p2) <= 0) {
                child = (I) p1.clone();
            } else {
                child = (I) p2.clone();
            }
        }
        return child;
    }

}
