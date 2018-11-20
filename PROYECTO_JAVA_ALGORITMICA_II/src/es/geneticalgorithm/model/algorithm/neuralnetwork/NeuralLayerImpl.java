/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.HyperbolicTangentOutputStrategy;
import es.geneticalgorithm.util.Utils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase NeuralNetwork que representa un conjunto de neuronas y su aprendizaje.
 *
 * @author Quini Roiz
 */
public class NeuralLayerImpl implements NeuralLayer {

    private final List<Perceptron> neurons;

    /**
     * Constructor que creará una capa para la red neuronal en función del
     * número de entradas de la capa y las neuronas requeridas para esto
     *
     * @param entries número de entradas
     * @param neurons número de neuronas
     */
    public NeuralLayerImpl(int entries, int neurons) {
        this.neurons = IntStream.range(0, neurons).parallel()
                .mapToObj((i) -> {
                    return new PerceptronImpl(entries, new HyperbolicTangentOutputStrategy());
                }).collect(Collectors.toList());
    }

    @Override
    public double[] forwardPropagation(double[] entries) {
        // para cada perceptrón de la capa actual
        // calcular output
        double[] outputs = new double[neurons.size()];

        IntStream.range(0, neurons.size()).forEach((i) -> {
            outputs[i] = neurons.get(i).output(entries);
        });
        return outputs;
    }

    @Override
    public void backError(double... targets) {
        IntStream.range(0, neurons.size())
                .forEach((n) -> {
                    double error = neurons.get(n).getOutput()
                            - targets[n];// * neurons.get(n).getDerivate();
                    neurons.get(n).setError(error);
                });
    }

    @Override
    public void backError(NeuralLayer next) {
        IntStream.range(0, neurons.size())
                .forEach((n) -> {
                    double sum = IntStream.range(0, next.size())
                            .mapToDouble((np) -> {
                                return next.get(np).getWeights()[n] * next.get(np).getError();
                            }).sum();
                    double error = sum * neurons.get(n).getDerivate();
                    if (Double.isNaN(error) || Double.isInfinite(error)) {
                        error = 1;
                    }
                    neurons.get(n).setError(error);
                });

    }

    @Override
    public void updateWeights(NeuralLayer prev) {
        IntStream.range(0, neurons.size())
                .forEach((n) -> {
                    IntStream.range(0, prev.size())
                            .forEach((pn) -> {
                                double delta = -Utils.LEARNING_RATE * prev.get(pn).getOutput() * neurons.get(n).getError();
                                neurons.get(n).getWeights()[pn] += delta;
                            });
                    double delta = -Utils.LEARNING_RATE * neurons.get(n).getError();
                    neurons.get(n).setBias(neurons.get(n).getBias() + delta);
                });
    }

    @Override
    public int size() {
        return neurons.size();
    }

    @Override
    public Perceptron get(int index) {
        return neurons.get(index);
    }

    @Override
    public List<Perceptron> getNeurons() {
        return neurons;
    }

}
