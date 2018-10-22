/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.SigmoidOutputStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase NeuralNetwork que representa un conjunto de neuronas y su aprendizaje.
 *
 * @author Quini Roiz
 */
public class NeuralLayer {

    private final List<Perceptron> neurons;

    public NeuralLayer(int entries, int neurons) {
        this.neurons = IntStream.range(0, neurons).parallel()
                .mapToObj((i) -> {
                    return new Perceptron(entries, new SigmoidOutputStrategy());
                }).collect(Collectors.toList());
    }

    public Double[] forwardPropagation(Double[] entries) {
        // para cada perceptrón de la capa actual
        // calcular output
        List<Double> outputs = new ArrayList<>();
        neurons.forEach((p) -> {
            outputs.add(p.output(entries));
        });
        return (Double[]) outputs.toArray();
    }

}
