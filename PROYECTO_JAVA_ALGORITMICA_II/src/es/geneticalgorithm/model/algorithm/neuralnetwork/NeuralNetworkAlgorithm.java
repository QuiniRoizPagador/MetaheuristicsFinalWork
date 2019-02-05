package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.Algorithm;
import es.geneticalgorithm.model.algorithm.individuals.AbstractIndividual;
import es.geneticalgorithm.model.algorithm.individuals.AsyncQuadraticIndividual;
import es.geneticalgorithm.model.persistence.MemoryData;
import es.geneticalgorithm.util.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author cobra
 */
public class NeuralNetworkAlgorithm extends Algorithm implements Runnable {

    private NeuralNetwork nn;
    private int size;

    public NeuralNetworkAlgorithm(boolean async) {
        super(async);
    }

    @Override
    public void prepare() throws CloneNotSupportedException {
        size = MemoryData.getInstance().getClients().size();
        nn = new NeuralNetworkImpl(Utils.N_ENTRIES, size, Utils.N_HIDDEN_LAYERS, Utils.N_HIDDEN_NEURONS);
    }

    @Override
    public synchronized void run() {
        double[][] outputs = new double[size][size];
        double[] inputs;
        double[] expected = new double[size];
        posExito = 0;
        // train
        bestIndividual = new AsyncQuadraticIndividual(new ArrayList<>(), 1);
        do {
            for (it = 0; it < 10; it++) {
                for (int j = 0; j < size; j++) {
                    inputs = new double[]{
                        MemoryData.getInstance().getEmployees().get(j).getDir_x(),
                        MemoryData.getInstance().getEmployees().get(j).getDir_y(),
                        Math.pow(MemoryData.getInstance().getEmployees().get(j).getDir_x(), 2),
                        Math.pow(MemoryData.getInstance().getEmployees().get(j).getDir_y(), 2),
                        MemoryData.getInstance().getEmployees().get(j).getDir_x()
                        * MemoryData.getInstance().getEmployees().get(j).getDir_y()
                    };
                    Arrays.fill(expected, 0);
                    expected[j] = 1;
                    outputs[j] = nn.train(inputs,
                            expected);
                }

                posible = getIndividual(outputs);
                posible.calculateFitness();
                if (posible.isValid() && posible.compareTo(bestIndividual) < 0) {
                    posExito = it;
                    bestIndividual = posible;
                }
                notifyObservers();

            }
        } while (!bestIndividual.isValid() || (bestIndividual.isValid() && bestIndividual.getFitness() > 0.6));
        notifyObservers();
        notifyFinished();
    }

    private AbstractIndividual getIndividual(double[][] outputs) {
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            int max = 0;
            double maxCont = 0;
            for (int k = 0; k < size; k++) {
                if (outputs[j][k] > maxCont) {
                    max = k;
                    maxCont = outputs[j][k];
                }
            }
            res.add(max);
        }
        return new AsyncQuadraticIndividual(res);
    }

    @Override
    public double getPorcentaje() {
        return it * 100 / 10;
    }

}
