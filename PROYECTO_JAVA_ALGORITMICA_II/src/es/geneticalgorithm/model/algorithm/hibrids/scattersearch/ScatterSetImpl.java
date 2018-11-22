/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.hibrids.scattersearch;

import es.geneticalgorithm.model.algorithm.hibrids.memetic.MemeticSet;
import es.geneticalgorithm.model.algorithm.hibrids.memetic.MemeticSetImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import es.geneticalgorithm.model.algorithm.genetic.Crossover;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.localsearch.trayectories.SimulatedAnnealing;
import es.geneticalgorithm.model.algorithm.localsearch.trayectories.TabuSearch;
import es.geneticalgorithm.util.Utils;


/**
 * Clase que implementará una búsqueda dispersa,
 * donde se generarán los subconjuntos R1 y R2 y se trabajará con ellos.
 *
 * @author Quini Roiz
 */
public class ScatterSetImpl extends MemeticSetImpl implements ScatterSet{

    private List<AbstractIndividual> p;
    private List<AbstractIndividual> r1;
    private List<AbstractIndividual> r2;

    private boolean estable;

    @Override
    public void generatePopulation() throws CloneNotSupportedException {
        super.generatePopulation();
        localSearch();
        p = new ArrayList<>();
        p.addAll(individuals);
    }

    @Override
    public void localSearchR() throws CloneNotSupportedException {
        // ordenar en paralelo
        r1 = r1.parallelStream().sorted().collect(Collectors.toList());

        // contador auxiliar
        AtomicInteger index = new AtomicInteger();
        // itero en paralelo y almaceno el resultado en listado auxiliar
        List<AbstractIndividual> aux = r1.parallelStream()
                    .filter(i -> index.incrementAndGet() <= r1.size())
                    .map((ind) -> {
                        // crear enfriamiento e inicializar
                        try {
                            TabuSearch ts = new TabuSearch(true);
                            // actualizar individuo con el que trabaja
                            ts.setIndividual(ind);
                            // ejecutar búsqueda local
                            ts.run();
                            // recogemos el resultado
                            return ts.getBest();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(MemeticSet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return null;
                        // se recolecta a una lista todo
                    }).collect(Collectors.toList());
        // reinsertar lo calculado
        r1 = aux;

        // ordenar en paralelo
        r1 = r1.parallelStream().sorted().collect(Collectors.toList());

        // contador auxiliar
        AtomicInteger inde = new AtomicInteger();
        // itero en paralelo y almaceno el resultado en listado auxiliar
        aux = r2.parallelStream()
                    .filter(j -> inde.incrementAndGet() <= r2.size())
                    .map((ind) -> {
                        // crear enfriamiento e inicializar
                        try {
                            SimulatedAnnealing ts = new SimulatedAnnealing(true);
                            // actualizar individuo con el que trabaja
                            ts.setIndividual(ind);
                            // ejecutar búsqueda local
                            ts.run();
                            // recogemos el resultado
                            return ts.getBest();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(MemeticSet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return null;
                        // se recolecta a una lista todo
                    }).collect(Collectors.toList());
        // reinsertar lo calculado
        r2 = aux;
    }

    @Override
    public void generateR1() {
        p = p.parallelStream().sorted().collect(Collectors.toList());
        r1 = IntStream.range(0, 5).parallel().mapToObj((i) -> {
            return p.get(i);
        }).collect(Collectors.toList());
    }

    @Override
    public void crossover() {
        // tamaño restante para igualar a la población total
        nextGeneration = new LinkedList<>();
        List aux = r1.parallelStream()
                    .map(p1 -> {
                        List<AbstractIndividual> next = new ArrayList<>();
                        r2.parallelStream().forEach((p2) -> {
                            AbstractIndividual ind;
                            Crossover c1;
                            // tiramos dado. 50% de probabilidades de realizar un cruce uniforme o un cruce por porción
                            if (rn.nextFloat() > 0.5) {
                                c1 = new Crossover(p1, p2, Utils.UNIFORM_TYPE);
                            } else {
                                c1 = new Crossover(p1, p2, Utils.PORTION_TYPE);
                            }
                            ind = c1.crossover();
                            if (ind.isValid()) {
                                next.add(ind);
                            }
                        });
                        return next;
                    }).collect(Collectors.toList());
        aux.forEach((a) -> {
            nextGeneration.addAll((Collection<? extends AbstractIndividual>) a);
        });
        nextGeneration.removeAll(Collections.singleton(null));
        // actualizamos la referencia de los individuos de la población, 
        // dado que ya se han reproducido y es una nueva población
        individuals = nextGeneration;
        nextGeneration = null;
        evaluate();
    }

    @Override
    public void generateR2() {
        calcularDiversidadRespectoR1();
        p = p.parallelStream().sorted(new DiversityComparator()).collect(Collectors.toList());
        r2 = IntStream.range(0, 5).parallel().mapToObj((i) -> {
            return p.get(i);
        }).collect(Collectors.toList());
    }

    private void calcularDiversidadRespectoR1() {
        /* Calcular difversidad de todos respecto a R1 */
        IntStream.range(0, p.size()).parallel().forEach((i) -> {
            int diferencias = IntStream.range(0, r1.size()).parallel().map((j) -> {
                return IntStream.range(0, p.get(i).getGenotype().size()).parallel().map((k) -> {
                    if (!p.get(i).getGenotype().get(k).equals(r1.get(j).getGenotype().get(k))) {
                        return 1;
                    }
                    return 0;
                }).sum();
            }).sum();
            p.get(i).setDiversity(diferencias);
        });
    }

    @Override
    public AbstractIndividual getBestIndividual() {
        if (r1 != null) {
            r1 = r1.parallelStream().sorted().collect(Collectors.toList());
            return r1.get(0);
        }
        return super.getBestIndividual();
    }

    @Override
    public void updateR() {
        estable = false;
        List<AbstractIndividual> newR1 = new LinkedList<>();
        List<AbstractIndividual> oldR1 = new LinkedList<>();
        oldR1.addAll(r1);
        newR1.addAll(individuals);
        newR1.addAll(r1);
        Collections.sort(newR1);
        r1.clear();
        r1.addAll(newR1.subList(0, 5));
        List<AbstractIndividual> newR2 = new LinkedList<>();
        List<AbstractIndividual> oldR2 = new LinkedList<>();
        oldR2.addAll(r2);
        newR2.addAll(individuals);
        newR2.addAll(r2);
        IntStream.range(0, newR2.size()).parallel().forEach((i) -> {
            int diferencias = IntStream.range(0, r1.size()).parallel().map((j) -> {
                return IntStream.range(0, newR2.get(i).getGenotype().size()).parallel().map((k) -> {
                    if (!newR2.get(i).getGenotype().get(k).equals(r1.get(j).getGenotype().get(k))) {
                        return 1;
                    }
                    return 0;
                }).sum();
            }).sum();
            newR2.get(i).setDiversity(diferencias);
        });
        newR2.sort(new DiversityComparator());
        r2.clear();
        r2.addAll(newR2.subList(0, 5));
        if (r1.containsAll(oldR1) && r2.containsAll(oldR2)) {
            estable = true;
        }
    }

    private class DiversityComparator implements Comparator<AbstractIndividual> {

        @Override
        public int compare(AbstractIndividual o1, AbstractIndividual o2) {
            if (o1.getDiversity() < o2.getDiversity()) {
                return 1;
            } else if (o1.getDiversity() > o2.getDiversity()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean isEstable() {
        return estable;
    }

}
