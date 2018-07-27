/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.OutputStrategy;
import es.geneticalgorithm.util.Utils;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase Perceptrón que representa una neurona y su aprendizaje.
 *
 * @author Quini Roiz
 */
public class Perceptron {

    private final List<Double> weights;
    private Double output;
    private final int nEntries;
    private final OutputStrategy outputStrategy;

    public Perceptron(int nEntries, OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
        this.nEntries = nEntries;
        output = Double.NaN;
        Random rn = new Random();
        weights = IntStream.range(0, nEntries + 1)
                .parallel()
                .mapToObj((t) -> rn.nextDouble()).collect(Collectors.toList());

    }

    public double output(List<Double> entries) {
        double x = IntStream
                .range(0, nEntries)
                .parallel()
                .mapToDouble((v) -> entries.get(v) * weights.get(v)).sum();
        x += weights.get(entries.size());

        return outputStrategy.output(x);
    }

    public double output(Double[] entries) {
        return output(Arrays.asList(entries));
    }

    void erase() {
        output = Double.NaN;
    }

    Double getOutput() {
        return output;
    }

    public Double getWeight(int i) {
        return weights.get(i);
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeight(int pos, double weight) {
        weights.set(pos, weight);
    }

    public void learn(Double[][] entries, Double[] outputs) {
        double globalError;
        int it = 0;
        System.out.println("Training model...");
        do {
            it++;
            globalError = IntStream.range(0, entries.length)
                    .parallel()
                    .mapToDouble((i) -> {
                        Double localError = outputs[i] - output(Arrays.asList(entries[i]));
                        IntStream.range(0, nEntries)
                                .forEach((j) -> {
                                    Double wj = getWeight(j) + Utils.LEARNING_RATE * localError * entries[i][j];
                                    getWeights().set(j, wj);
                                });
                        getWeights().set(nEntries, Utils.LEARNING_RATE * localError);
                        return (localError * localError);
                    }).sum();
        } while (globalError != 0 && it < 100);
        System.out.println("Iteration " + it + " : RMSE = " + Math.sqrt(globalError / nEntries));
        System.out.println("Model Trained.");
    }
}
