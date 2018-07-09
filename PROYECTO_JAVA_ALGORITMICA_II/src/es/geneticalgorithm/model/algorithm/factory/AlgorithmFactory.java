/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.factory;

import es.geneticalgorithm.model.algorithm.Algorithm;
import es.geneticalgorithm.model.algorithm.genetic.GeneticAlgorithm;
import es.geneticalgorithm.model.algorithm.localsearch.trayectories.SimulatedAnnealing;
import es.geneticalgorithm.model.algorithm.hibrids.MemeticAlgorithm;
import es.geneticalgorithm.model.algorithm.neuralnetwork.Mind;
import es.geneticalgorithm.model.service.IAlgorithmService;
import es.geneticalgorithm.util.Utils;

/**
 * Clase que utiliza el patrón factoría, creando un algoritmo específico a
 * partir de unos parámetros recibidos.
 *
 * @see Algorithm
 * @see IAlgorithmService
 *
 *
 * @author Quini Roiz
 */
public class AlgorithmFactory {

    /**
     * Método factoría que se encarga de crear el algoritmo a ejecutar en
     * función de los parámetros recibidos.
     *
     * @param algorithm tipo de algoritmo a crear.
     * @param m modelo al que se asociará el algoritmo.
     * @param async variable que definirá si se ejecuta en paralelo.
     * @return algoritmo instanciado y configurado.
     * @throws CloneNotSupportedException en caso de error de clonación.
     *
     * @see IAlgorithmService
     */
    public static Algorithm createAlgorithm(int algorithm, IAlgorithmService m, boolean async) throws CloneNotSupportedException, UnsupportedOperationException {
        Algorithm al = null;
        switch (algorithm) {
            case Utils.SIMULATED_ANNEALING_TYPE:
                al = new SimulatedAnnealing(async);
                break;
            case Utils.GENETIC_ALGORITHM_TYPE:
                al = new GeneticAlgorithm(async);
                break;
            case Utils.MEMETIC_ALGORITHM_TYPE:
                al = new MemeticAlgorithm(async);
                break;
            case Utils.NEURAL_NETWORK_ALGORITHM_TYPE:
                al = new Mind();
                break;
        }
        // prepara los datos para el algoritmo
        al.prepare();
        // actualiza el valor del modelo en la clase algoritmo
        al.setModel(m);
        return al;
    }
}
