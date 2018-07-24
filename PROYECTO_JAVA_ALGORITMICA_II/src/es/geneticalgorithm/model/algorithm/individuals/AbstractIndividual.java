/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.individuals;

import es.geneticalgorithm.model.algorithm.individuals.behaviours.FitnessBehaviour;
import es.geneticalgorithm.model.persistence.MemoryData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase Individual que compondrá las principales características de un
 * individuo para todo tipo de problema.
 *
 * @see Comparable
 * @see Cloneable
 *
 *
 * @author Quini Roiz
 */
public abstract class AbstractIndividual implements Comparable<AbstractIndividual>, Cloneable {

    /**
     * Genotipo del que se compone un individuo.
     */
    protected List<Integer> genotype;

    /**
     * Fitness, bondad o valor del individuo.
     */
    protected double fitness;

    protected FitnessBehaviour fitnessBehaviour;

    protected Random rn;

    /**
     * Default constructor
     *
     * @param fitnessBehaviour comportamiento por defecto
     */
    public AbstractIndividual(FitnessBehaviour fitnessBehaviour) {
        this.fitnessBehaviour = fitnessBehaviour;
        genotype = new ArrayList<>();
        MemoryData.getInstance().getClients().forEach((_item) -> {
            genotype.add(0);
        });
        rn = new Random();
    }

    /**
     * Constructor para nuevos individuos a partir de otros
     *
     * @param fitnessBehaviour fitnessBehaviour comportamiento por defecto
     * @param newGenotype genotipo a copiar
     */
    public AbstractIndividual(FitnessBehaviour fitnessBehaviour, List<Integer> newGenotype) {
        this.fitnessBehaviour = fitnessBehaviour;
        genotype = new ArrayList<>();
        newGenotype.forEach((i) -> {
            genotype.add(i);
        });
        rn = new Random();
    }

    /**
     * Constructor para nuevos individuos a partir de otros
     *
     * @param fitnessBehaviour fitnessBehaviour comportamiento por defecto
     * @param newGenotype genotipo a copiar
     * @param fitness fitness a copiar
     */
    public AbstractIndividual(FitnessBehaviour fitnessBehaviour, List<Integer> newGenotype, double fitness) {
        this(fitnessBehaviour, newGenotype);
        this.fitness = fitness;
    }

    /**
     * Devolverá el valor de la propiedad fitness.
     *
     * @return fitness del individuo
     */
    public double getFitness() {
        return fitness;
    }

    @Override
    public abstract AbstractIndividual clone() throws CloneNotSupportedException;

    /**
     * Calculará el fitness y lo almacenará en el atributo fitness.
     *
     */
    public abstract void calculateFitness();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractIndividual) {
            AbstractIndividual o = (AbstractIndividual) obj;
            return o.getGenotype().equals(getGenotype()) && o.getFitness() == getFitness();
        }
        return false;
    }

    /**
     * Implementación de la interfaz Comparable.
     *
     * @param o individual con el que comparar
     * @return int con el resultado de la comparación, en función del tipo de
     * individuos a comparar
     */
    @Override
    public int compareTo(AbstractIndividual o) {
        int res;
        if (getFitness() - o.getFitness() > 0) {
            res = 1;
        } else if (getFitness() - o.getFitness() < 0) {
            res = -1;
        } else {
            res = 0;
        }
        return res;
    }

    /**
     * generará el genotipo en función del tipo de problema a abordar
     *
     */
    public abstract void generateData();

    /**
     * Método que devolverá el genotipo del individuo
     *
     * @return Listado de genotipo
     */
    public List<Integer> getGenotype() {
        return genotype;
    }

    /**
     * Realización de una mutación del individuo al azar
     */
    public abstract void mutate();

    /**
     * Realización de una mutación del individuo a partir del parámetro recibido
     *
     * @param i posición a mutar
     */
    public abstract void mutate(int i);

    /**
     * Comprobación de la validez del individuo
     *
     * @return variable booleana con la validez del individuo
     */
    public abstract boolean isValid();

    /**
     * Método que actualiza el comportamiento del indiviudo para calcular el
     * fitness
     *
     * @param fb nuevo comportamiento
     */
    public void setFitnessBehaviour(FitnessBehaviour fb) {
        fitnessBehaviour = fb;
    }

    @Override
    public String toString() {
        return "\nGenotipo: " + genotype
                    + " | Fitness: " + fitness;
    }

    /**
     * Generación de un vecino a partir del genotipo actual
     */
    public void getVecino() {
        mutate();
    }
}
