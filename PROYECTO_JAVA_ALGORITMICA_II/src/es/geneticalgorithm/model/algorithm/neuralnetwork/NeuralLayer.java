/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import java.util.List;

/**
 * Interfaz NeuralLayer que representa una capa perteneciente a una red neuronal
 *
 * @author Quini Roiz
 */
public interface NeuralLayer {

    /**
     * Tamaño de la capa neuronal
     *
     * @return número de neuronas que contiene la capa
     */
    int size();

    /**
     * Método que devuelve el perceptrón indicado por parámetros
     *
     * @param index índice del perceptrón buscado
     * @return perceptrón buscado
     */
    Perceptron get(int index);

    /**
     * Método que devuelve el listado de neuronas que contiene la capa
     *
     * @return listado de neuronas de la capa
     */
    List<Perceptron> getNeurons();

    /**
     * Método que obtiene salidas a partir de unas entradas para la capa de la
     * red
     *
     * @param entries entradas
     * @return salidas o etiquetas calculadas
     */
    double[] forwardPropagation(double[] entries);

    /**
     * Método que calculará el error de la última capa
     *
     * @param targets etiquetas que se esperan
     */
    void backError(double... targets);

    /**
     * Método que calculará el error referente a la capa, si ésta es la capa n-2
     * hasta la 1
     *
     * @param next
     */
    void backError(NeuralLayer next);

    /**
     * Método que actualizará los pesos de las neuronas en función del error
     * calculado en la retropropagación
     *
     * @param prev Capa anterior a la actual
     */
    void updateWeights(NeuralLayer prev);
}
