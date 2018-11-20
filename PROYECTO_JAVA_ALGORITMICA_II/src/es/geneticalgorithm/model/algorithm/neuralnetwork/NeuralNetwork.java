/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.List;

/**
 * Interfaz NeuralNetwork que representa un conjunto de neuronas y su
 * aprendizaje.
 *
 * @author Quini Roiz
 */
public interface NeuralNetwork {

    /**
     * Método que entrenará una red neuronal en función de las entradas y las
     * salidas o etiquetas esperadas
     *
     * @param entry entradas
     * @param expected etiquetas esperadas
     * @return
     */
    double[] train(double[] entry, double[] expected);

    /**
     * Método que adentrará unas entradas en la red neuronal para obtener una
     * salida
     *
     * @param entry entradas
     * @return salidas procesadas
     */
    double[] forwardPropagation(double[] entry);

    /**
     * Método que devuelve una referencia a las capas de la red neuronal
     *
     * @return listado de capas de la red neuronal
     */
    List<NeuralLayer> getNeuralNetwork();

}
