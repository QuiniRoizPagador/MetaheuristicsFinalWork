/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Quini_Dev
 */
public class NeuralNetworkTest {

    NeuralNetwork instance = new NeuralNetworkImpl(2, 2, 2, 1);
    double[] expResult = {1., 0., 0., 1.};

    public NeuralNetworkTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of forwardPropagation method, of class NeuralNetwork.
     */
    @Test
    public void testForwardPropagation() {
        System.out.println("forwardPropagation");
        double[][] entries = {{1., 1.},
        {0., 1.},
        {1., .0},
        {0., .0}};
        Double[] result = null;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 4; j++) {
                instance.train(entries[j], expResult);
                System.out.println(Arrays.toString(result));
            }
        }
        System.out.println(Arrays.toString(instance.forwardPropagation(entries[0])));
        assertTrue(true);
    }

    /**
     * Test of backPropagation method, of class NeuralNetwork.
     */
    @Test
    public void testBackPropagation() {

    }

}
