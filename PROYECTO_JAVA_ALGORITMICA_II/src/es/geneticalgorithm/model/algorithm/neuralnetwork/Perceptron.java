/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork;

import es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours.OutputStrategy;

/**
 * Interfaz Perceptrón que representa neurona
 *
 * @author Quini Roiz
 */
public interface Perceptron {

    /**
     * Método que calcula la salida función de activación de la neurona en
     * función de sus entradas y pesos
     *
     * @param entries entradas
     * @return función de activación
     */
    double output(double... entries);

    /**
     * Limpiar pesos
     */
    void erase();

    /**
     * Método que devuelve el bias de la neurona
     *
     * @return valor del bias
     */
    double getBias();

    /**
     * Método que actualiza el bias de la neurona
     *
     * @param bias nuevo valor del bias
     */
    void setBias(double bias);

    /**
     * Método que devuelve el valor de la función de activación calculado en la
     * función output
     *
     * @return output
     */
    Double getOutput();

    /**
     * Método que devuelve los pesos de la neurona
     *
     * @return array con los valores de los pesos de la neurona
     */
    double[] getWeights();

    /**
     * Método que actualiza la estrategia de la función de activación
     *
     * @param o función de activación
     */
    void setOutputStrategy(OutputStrategy o);

    /**
     * Método que devuelve el error de la neurona
     *
     * @return valor numérico con el error
     */
    Double getError();

    /**
     * Método que actualiza el valor del error de la neurona
     *
     * @param error valor numérico con el numero error d ela neurona
     */
    void setError(double error);

    /**
     * Método que devuelve la derivada de la función de activación
     *
     * @return valor numérico de la derivada
     */
    double getDerivate();
}
