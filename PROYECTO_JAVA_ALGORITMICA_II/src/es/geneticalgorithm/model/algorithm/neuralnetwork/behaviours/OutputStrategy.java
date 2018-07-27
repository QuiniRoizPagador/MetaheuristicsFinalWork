/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours;

/**
 * Interfaz que implementará una estrategia a cada clase según su función de
 * output para el perceptrón.
 *
 * @author Quini Roiz
 */
public interface OutputStrategy {

    /**
     * Función que tomará la salida del perceptrón, basándose en el cálculo de
     * los pesos y las entradas, junto con el bias.
     *
     * @param x Sumatorio de los pesos por sus entradas más el bias.
     * @return Se devuelve la función aplicada a este resultado (sigmoide,
     * tangenge hiperbólica...).
     */
    Double output(Double x);
}
