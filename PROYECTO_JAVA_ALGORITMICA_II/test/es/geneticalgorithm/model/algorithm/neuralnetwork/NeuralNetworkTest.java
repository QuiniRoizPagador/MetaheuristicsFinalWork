/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.Arrays;
import java.util.stream.IntStream;
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

    NeuralNetwork instance = new NeuralNetworkImpl(2, 1, 3, 4);
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
        double[][] outputs = new double[4][1];
        double error = 3;
        for (int i = 0; error > 0.2; i++) {
            //System.out.println("--------------------------------------");
            for (int j = 0; j < 4; j++) {
                outputs[j] = instance.train(entries[j], new double[]{expResult[j]});

                //System.out.print("|" + outputs[j][0] + "|");
            }
            //System.out.println("\n--------------------------------------");
            error = IntStream.range(0, outputs.length).
                    mapToDouble((e) -> {
                        return Math.pow(expResult[e] - outputs[e][0], 2);
                    }).sum();
            //System.out.println(instance);
            System.out.println("Iteración: " + (i + 1) + "\nError: " + error / 2 * 100);
        }
        System.out.println("Esperado");
        for (int j = 0; j < 4; j++) {
            System.out.print("|" + expResult[j] + "|");
        }
        System.out.println("Encontrado\n");
        for (int j = 0; j < 4; j++) {
            System.out.print("|" + outputs[j][0] + "|");
        }
        
    }

    /**
     * Test of backPropagation method, of class NeuralNetwork.
     */
    @Test
    public void testBackPropagation() {

    }

}
