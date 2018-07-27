package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.LinealOutputStrategy;
import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.OutputStrategy;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NeuronTest {

    private Perceptron n;
    private Double[][] entries;
    private Double[] o;
    private OutputStrategy outputStrategy;

    @Before
    public void setUp() {
        entries = new Double[][]{
            {1.0, 1.0},
            {0.0, 0.0},
            {1.0, 0.0},
            {0.0, 1.0}
        };
        outputStrategy = new LinealOutputStrategy();
        n = new Perceptron(2, outputStrategy);
    }

    @Test
    public void learnANDTest() {
        o = new Double[]{1.0, 0.0, 0.0, 0.0};
        n.learn(entries, o);

        assertTrue((int) n.output(Arrays.asList(new Double[]{1.0, 1.0})) == 1.0);
        assertTrue((int) n.output(Arrays.asList(new Double[]{0.0, 0.0})) == 0.0);
        assertTrue((int) n.output(Arrays.asList(new Double[]{1.0, 0.0})) == 0.0);
        assertTrue((int) n.output(Arrays.asList(new Double[]{0.0, 1.0})) == 0.0);
    }

    @Test
    public void learnORTest() {
        o = new Double[]{1.0, 0.0, 1.0, 1.0};
        n.learn(entries, o);

        assertTrue((int) n.output(Arrays.asList(new Double[]{1.0, 1.0})) == 1.0);
        assertTrue((int) n.output(Arrays.asList(new Double[]{0.0, 0.0})) == 0.0);
        assertTrue((int) n.output(Arrays.asList(new Double[]{1.0, 0.0})) == 1.0);
        assertTrue((int) n.output(Arrays.asList(new Double[]{0.0, 1.0})) == 1.0);
    }
}
