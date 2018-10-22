/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase NeuralNetwork que representa un conjunto de neuronas y su aprendizaje.
 *
 * @author Quini Roiz
 */
public class NeuralNetwork {

    private final List<NeuralLayer> neuralNetwork;

    public NeuralNetwork(int entries, int layers, int neuronsPerLayer) {
        neuralNetwork = IntStream.range(0, layers).parallel().mapToObj((i) -> {
            return new NeuralLayer(entries, neuronsPerLayer);
        }).collect(Collectors.toList());
    }

    public Double[] forwardPropagation(Double[] entries) {
        // para cada entrada i en la capa j
        // tomamos las salidas y las incluímos en las entradas siguientes
        for (NeuralLayer layer : neuralNetwork) {
            entries = layer.forwardPropagation(entries);
        }
        return entries;
    }

}
