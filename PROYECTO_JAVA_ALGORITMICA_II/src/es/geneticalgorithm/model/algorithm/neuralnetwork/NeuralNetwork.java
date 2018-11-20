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
    private final int ENTRIES;
    private final int LABELS;

    public List<NeuralLayer> getNeuralNetwork() {
        return neuralNetwork;
    }

    public NeuralNetwork(int entries, int nOutputs, int layers, int neuronsPerLayer) {
        ENTRIES = entries;
        LABELS = nOutputs;
        neuralNetwork = IntStream.range(0, layers - 1).parallel().mapToObj((i) -> {
            if (i == 0) {
                return new NeuralLayer(entries, neuronsPerLayer);
            }
            return new NeuralLayer(neuronsPerLayer, neuronsPerLayer);
        }).collect(Collectors.toList());
        neuralNetwork.add(new NeuralLayer(neuronsPerLayer, nOutputs));
    }

    public double[] forwardPropagation(double... entries) {
        if (entries.length != ENTRIES) {
            return null;
        }

        // para cada entrada i en la capa j
        // tomamos las salidas y las incluímos en las entradas siguientes
        for (NeuralLayer layer : neuralNetwork) {
            entries = layer.forwardPropagation(entries);
        }
        return entries;
    }

    public double[] train(double[] entries, double... labels) {
        double[] output = forwardPropagation(entries);
        backPropagation(labels);
        return output;
    }

    public void backPropagation(double... labels) {
        if (labels.length != LABELS) {
            return;
        }
        // ajustar error de la última capa
        neuralNetwork.get(neuralNetwork.size() - 1).backError(labels);

        // ajustar error de demás capas
        NeuralLayer next;
        for (int l = neuralNetwork.size() - 2; l >= 0; l--) {
            next = neuralNetwork.get(l + 1);
            neuralNetwork.get(l).backError(next);
        }

        // actualizar pesos
        NeuralLayer prev;
        for (int l = 1; l < neuralNetwork.size(); l++) {
            prev = neuralNetwork.get(l - 1);
            neuralNetwork.get(l).updateWeights(prev);
        }

    }

}
