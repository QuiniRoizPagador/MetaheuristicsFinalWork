/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.Algorithm;
import es.geneticalgorithm.model.algorithm.individuals.AsyncQuadraticIndividual;
import es.geneticalgorithm.model.algorithm.individuals.SyncQuadraticIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Clase algoritmo de Red Neuronal. Esta clase se podrá paralizar en futuras
 * implementaciones para realizar varios intentos diferentes en caso de que se
 * indique en el programa principal.
 *
 * @see Runnable
 *
 * @author Quini Roiz
 */
public class NeuralNetworkAlgoritm extends Algorithm implements Runnable {

    private NeuralNetwork nn;
    private final int n;

    public NeuralNetworkAlgoritm(boolean async) {
        super(async);
        n = MemoryData.getInstance().getEmployees().size();
        nn = new NeuralNetworkImpl(3, n, n, n);//MemoryData.getInstance().getEmployees().size(), MemoryData.getInstance().getEmployees().size(), MemoryData.getInstance().getEmployees().size());
        repeticiones = 10;// * 10;
    }

    @Override
    public void prepare() throws CloneNotSupportedException {
        bestIndividual = new AsyncQuadraticIndividual();

    }

    @Override
    public void run() {
        notifyStarted();
        double[] result;
        double[] expected = new double[n];
        double[] entry = new double[3];
        Integer res[] = new Integer[MemoryData.getInstance().getClients().size()];
        List<Integer> medicos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            medicos.add(i);
        }
        Collections.shuffle(medicos);

        Arrays.fill(res, 0);
        for (it = 0; it < repeticiones; it++) {
            for (int j = 0; j < MemoryData.getInstance().getEmployees().size(); j++) {
                Arrays.fill(expected, 0.);
                entry[0] = (1. * MemoryData.getInstance()
                        .getEmployees()
                        .get(medicos.get(j))
                        .getDir_x());
                entry[1] = (1. * MemoryData.getInstance()
                        .getEmployees()
                        .get(medicos.get(j))
                        .getDir_y());
                entry[2] = 1. * MemoryData.getInstance()
                        .getEmployees()
                        .get(medicos.get(j))
                        .getMax_clientes();
                expected[j] = 1.;
                result = nn.train(entry, expected);
                int largest = 0;
                double maxV = Integer.MIN_VALUE;
                for (int i = 0; i < result.length; i++) {
                    if (result[i] > maxV) {
                        maxV = result[i];
                        largest = i;
                    }
                }
                res[j] = largest;
            }
            bestIndividual = new SyncQuadraticIndividual(Arrays.asList(res));
            bestIndividual.calculateFitness();
            notifyObservers();
            //System.out.println(Arrays.toString(res));
        }
        System.out.println("TRAIN: ");
        System.out.println(Arrays.toString(res));
        System.out.println("--------------");
        res = new Integer[MemoryData.getInstance().getClients().size()];
        Arrays.fill(res, 0);
        Arrays.fill(entry, 0);
        for (int j = 0; j < MemoryData.getInstance().getClients().size(); j++) {
            entry[0] = (1. * MemoryData.getInstance()
                    .getEmployees()
                    .get(j)
                    .getDir_x());
            entry[1] = (1. * MemoryData.getInstance()
                    .getEmployees()
                    .get(j)
                    .getDir_y());
            entry[2] = j;
            //result = nn.train(entry, expected);
            result = nn.forwardPropagation(entry);
            int largest = 0;
            double maxV = Integer.MIN_VALUE;
            for (int i = 0; i < result.length; i++) {
                if (result[i] > maxV) {
                    maxV = result[i];
                    largest = i;
                }
            }
            res[j] = largest;
        }
        System.out.println("TEST: ");
        System.out.println(Arrays.toString(res));
        System.out.println("--------------");
        bestIndividual = new SyncQuadraticIndividual(Arrays.asList(res));
        bestIndividual.calculateFitness();
        notifyObservers();
        notifyFinished();

    }

    @Override
    public double getPorcentaje() {
        return it / repeticiones;
    }

}
