package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class PointND {

    public final List<Double> entries;
    public final List<Double> outputs;

    public PointND(String str, int _numSalidas) {
        entries = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    public List<Double> getOutputs() {
        return outputs;
    }

}
