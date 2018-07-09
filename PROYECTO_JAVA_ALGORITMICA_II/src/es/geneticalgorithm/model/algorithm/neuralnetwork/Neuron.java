package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Neuron {

    private final List<Double> weights;
    private Double output;

    public Neuron(int nEntries) {
        output = Double.NaN;
        weights = IntStream.of(nEntries)
                .parallel()
                .mapToObj((t) -> {
                    return Double.valueOf(nEntries);
                }).collect(Collectors.toList());
        weights.add(0.0);

    }

    public double evaluate(List<Double> entries) {
        if (Double.isNaN(output)) {
            double x = IntStream
                    .of(entries.size())
                    .parallel()
                    .mapToDouble((v) -> {
                        return entries.get(v) * weights.get(v);
                    }).sum();
            x += weights.get(entries.size());
            output = 1.0 / (1.0 / Math.exp(-1.0 * x));
        }
        return output;
    }

    public double evaluate(PointND point) {
        return evaluate(point.entries);
    }

    public void erase() {
        output = Double.NaN;
    }

    public Double getOutput() {
        return output;
    }

    public Double getWeight(int i) {
        return weights.get(i);
    }

    void setWeight(int pos, double weight) {
        weights.set(pos, weight);
    }

}
