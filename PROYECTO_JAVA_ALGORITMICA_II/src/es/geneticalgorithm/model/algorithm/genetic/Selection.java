/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.genetic;

import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import es.geneticalgorithm.util.Utils;

/**
 * Clase que se encarga de la selección de una población
 *
 *
 * @author Quini Roiz
 */
public class Selection {

    /**
     * Default constructor
     */
    public Selection() {
    }

    /**
     * Elitismo puro t duro, cojo los n mejores según el porcentaje
     *
     * @param p poblacion sobre la que trabajar
     * @return El individual con mejor fitness
     *
     * @see Population
     */
    public List<AbstractIndividual> elitism(Population p) {
        List<AbstractIndividual> elitism = new ArrayList<>();
        for (int i = 0; i < Utils.population_size * Utils.ELITE_PERCENT; i++) {
            elitism.add(p.getIndividuals().get(i));
        }
        return elitism;
    }

    /**
     *
     * Selección por ruleta. Se toma el porcentaje de ruleta asignando a cada
     * individuo un número aleatorio.
     *
     * @param p población sobre la que trabajar
     * @return Individual seleccionado
     * @throws java.lang.CloneNotSupportedException si error
     *
     * @see Population
     */
    public List<AbstractIndividual> selectionRoulette(Population p) throws CloneNotSupportedException {
        List<AbstractIndividual> roulete = new ArrayList<>();
        List<Double> porcentajes = new ArrayList<>();
        for (int i = 0; i < Utils.population_size; i++) {
            porcentajes.add(Math.random());
        }
        for (int j = 0; j < Utils.population_size * Utils.ROULETE_PERCENT; j++) {
            AbstractIndividual res = null;
            int pos;
            do {
                pos = (int) (Math.random() * Utils.population_size);
                if (Math.random() > porcentajes.get(pos)) {
                    res = p.getIndividuals().get(pos).clone();
                }

            } while (res == null);
            roulete.add(res);
        }
        return roulete;
    }

    /**
     * Se somete a torneo según el porcentaje definido
     *
     * @param p población sobre la que trabajar
     * @return individual elegido por torneo
     * @throws java.lang.CloneNotSupportedException se propaga si error
     *
     * @see Population
     */
    public List<AbstractIndividual> tournament(Population p) throws CloneNotSupportedException {
        Random rand = new Random();
        AbstractIndividual ind;
        List<AbstractIndividual> tournament = new ArrayList<>();
        Collections.sort(p.getIndividuals());
        for (int i = 0; i < Utils.population_size * Utils.TOURNAMENT_PERCENT; i++) {
            if (rand.nextDouble() < Utils.ELITE_PARENT_RATE) {
                int numElite = (int) (Utils.ELITE_PARENT_RATE * Utils.population_size);
                ind = p.getIndividuals().get(rand.nextInt(numElite)).clone();
            } else {
                List<AbstractIndividual> tournamentPopulation = new ArrayList<>();
                for (int j = 0; j < Utils.population_size; j++) {
                    int randIndex = (int) (Math.random() * Utils.population_size);
                    tournamentPopulation.add(p.getIndividuals().get(randIndex));
                }
                Collections.sort(tournamentPopulation);
                ind = tournamentPopulation.get(0).clone();
            }
            tournament.add(ind);
        }
        return tournament;
    }

}
