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
public class PerceptronImpl implements Perceptron {

    private double[] weights;
    private double bias;
    private double output;
    private OutputStrategy outputStrategy;
    private double error;
    private double derivate;
    private double x;

    public PerceptronImpl(int nEntries, OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
        weights = new double[nEntries];
        Random rn = new Random();
        IntStream.range(0, nEntries)
                .forEach((t) -> {
                    weights[t] = rn.nextDouble();
                });
        bias = 1.0;
    }

    @Override
    public double output(double... entries) {
        x = IntStream
                .range(0, entries.length)
                .mapToDouble((v) -> entries[v] * weights[v]).sum();
        x += bias;
        output = outputStrategy.output(x);
        derivate = outputStrategy.derivative(x);
        return output;
    }

    @Override
    public void erase() {
        output = Double.NaN;
    }

    @Override
    public double getBias() {
        return bias;
    }

    @Override
    public void setBias(double bias) {
        this.bias = bias;
    }

    @Override
    public Double getOutput() {
        return output;
    }

    @Override
    public double[] getWeights() {
        return weights;
    }

    @Override
    public void setOutputStrategy(OutputStrategy o) {
        this.outputStrategy = o;
    }

    @Override
    public Double getError() {
        return error;
    }

    @Override
    public void setError(double error) {
        this.error = error;
    }

    @Override
    public double getDerivate() {
        return derivate;
    }

}
