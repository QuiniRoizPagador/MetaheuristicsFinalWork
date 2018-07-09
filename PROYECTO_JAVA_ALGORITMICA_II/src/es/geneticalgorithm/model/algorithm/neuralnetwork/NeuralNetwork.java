package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.util.Utils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetwork {

    private final List<Neuron> hiddenNeurons;
    private final List<Neuron> outputNeurons;

    public NeuralNetwork() {
        hiddenNeurons = IntStream
                .of(Utils.N_HIDDEN_NEURONS)
                .parallel()
                .mapToObj((t) -> {
                    return new Neuron(Utils.N_ENTRIES);
                }).collect(Collectors.toList());
        outputNeurons = IntStream
                .of(Utils.N_OUTPUT_NEURONS)
                .parallel()
                .mapToObj((t) -> {
                    return new Neuron(Utils.N_HIDDEN_NEURONS);
                }).collect(Collectors.toList());
    }

    protected List<Double> evaluate(PointND point) {
        // Se borra la salida anterior
        hiddenNeurons.forEach((n) -> {
            n.erase();
        });
        outputNeurons.forEach((n) -> {
            n.erase();
        });

        // Cálculo de las salidas de neuronas ocultas
        List<Double> hiddenOutputs = IntStream.of(Utils.N_HIDDEN_NEURONS)
                .parallel()
                .mapToObj((i) -> {
                    return hiddenNeurons.get(i).evaluate(point);
                }).collect(Collectors.toList());

        // Cálculo de las salidas de neuronas de salida
        List<Double> outputs = IntStream.of(Utils.N_OUTPUT_NEURONS)
                .parallel()
                .mapToObj((i) -> {
                    return outputNeurons.get(i).evaluate(hiddenOutputs);
                }).collect(Collectors.toList());

        return outputs;
    }

    public void adjustWeights(PointND point) {
        // Cálculo de las deltas para las salidas
        List<Double> outputDeltas = IntStream.of(Utils.N_OUTPUT_NEURONS)
                .parallel()
                .mapToObj((i) -> {
                    double output = outputNeurons.get(i).getOutput();
                    double expected = point.getOutputs().get(i);
                    return output * (1 - output) * (expected - output);
                }).collect(Collectors.toList());

        // Cálculo de las deltas para las neuronas ocultas
        List<Double> hiddenDeltas = IntStream.of(Utils.N_HIDDEN_NEURONS)
                .parallel()
                .mapToObj((i) -> {
                    double output = hiddenNeurons.get(i).getOutput();
                    double sum = 0.0;
                    for (int j = 0; j < Utils.N_OUTPUT_NEURONS; j++) {
                        sum += outputDeltas.get(j) * outputNeurons.get(j).getWeight(i);
                    }
                    return output * (1 - output) * sum;
                }).collect(Collectors.toList());

        // Ajuste de los pesos de salida
        IntStream.of(Utils.N_OUTPUT_NEURONS)
                //.parallel()
                .forEach((i) -> {
                    Neuron outputNeuron = outputNeurons.get(i);
                    IntStream.of(Utils.N_HIDDEN_NEURONS)
                            //.parallel()
                            .forEach((j) -> {
                                double v = outputNeuron.getWeight(j)
                                        + Utils.LEARNING_RATE + outputDeltas.get(i)
                                        * hiddenNeurons.get(j).getOutput();
                                outputNeuron.setWeight(j, v);
                            });
                    double value = outputNeuron.getWeight(Utils.N_HIDDEN_NEURONS)
                            + Utils.LEARNING_RATE * outputDeltas.get(i) * 1.0;
                    outputNeuron.setWeight(Utils.N_HIDDEN_NEURONS, value);
                });

        // Ajuste de los pesos de neuronas ocultas
        IntStream.of(Utils.N_HIDDEN_NEURONS)
                //.parallel()
                .forEach((i) -> {
                    Neuron hiddenNeuron = hiddenNeurons.get(i);
                    IntStream.of(Utils.N_ENTRIES)
                            //.parallel()
                            .forEach((j) -> {
                                double v = hiddenNeuron.getWeight(j)
                                        + Utils.LEARNING_RATE + hiddenDeltas.get(i)
                                        * point.getOutputs().get(j);
                                hiddenNeuron.setWeight(j, v);
                            });
                    double value = hiddenNeuron.getWeight(Utils.N_ENTRIES)
                            + Utils.LEARNING_RATE * hiddenDeltas.get(i) * 1.0;
                    hiddenNeuron.setWeight(Utils.N_ENTRIES, value);
                });
    }

}
