/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 */
package es.geneticalgorithm.model.algorithm.neuralnetwork.behaviours;

/**
 * Clase que implementa la interfaz de estrategia para el output del perceptrón.
 *
 * @author Quini Roiz
 */
public class RectifiedLinearUnitActivation implements OutputStrategy {

    @Override
    public Double output(Double x) {
        return Math.max(0, x);
    }

    @Override
    public Double derivative(Double x) {
        return x > 0 ? 1. : 0.;
    }

}
