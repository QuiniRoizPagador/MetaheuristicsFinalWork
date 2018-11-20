/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.OutputStrategy;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Clase Perceptrón que representa una neurona y su aprendizaje.
 *
 * @author Quini Roiz
 */
public class Perceptron {

    private double[] weights;
    private double bias;
    private double output;
    private final OutputStrategy outputStrategy;
    private double error;
    private double derivate;
    private double x;

    public Perceptron(int nEntries, OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
        weights = new double[nEntries];
        Random rn = new Random();
        IntStream.range(0, nEntries)
                .forEach((t) -> {
                    weights[t] = rn.nextDouble();
                });
        bias = 1.0;
    }

    public double output(double... entries) {
        x = IntStream
                .range(0, entries.length)
                .mapToDouble((v) -> entries[v] * weights[v]).sum();
        x += bias;
        output = outputStrategy.output(x);
        derivate = outputStrategy.derivative(x);
        return output;
    }

    void erase() {
        output = Double.NaN;
    }

    double getBias() {
        return bias;
    }

    void setBias(double bias) {
        this.bias = bias;
    }

    Double getOutput() {
        return output;
    }

    public double[] getWeights() {
        return weights;
    }
    

    private OutputStrategy getOutputStrategy() {
        return outputStrategy;
    }

    Double getError() {
        return error;
    }

    void setError(double error) {
        this.error = error;
    }

    public double getDerivate() {
        return derivate;
    }

    public void setDerivate(double derivate) {
        this.derivate = derivate;
    }

}
