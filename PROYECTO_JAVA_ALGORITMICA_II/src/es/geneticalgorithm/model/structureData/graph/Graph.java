package es.geneticalgorithm.model.structureData.graph;

import es.geneticalgorithm.model.structureData.node.Node;
import java.util.List;

/**
 * Interfaz de un grafo, ofreciendo las funcionalidades principalmente
 * utilizadas para el problema descrito
 *
 * @author Quini_Dev
 * @param <E> Nuevamente utilizamos los tipos genéricos para este tipo de
 * implementación
 */
public interface Graph<E> {

    /**
     * Añadiremos un elemento al grafo
     *
     * @param element elemento a añadir al grafo
     */
    void addNode(E element);

    /**
     * Añadiremos una conexión entre dos nodos del grafo
     *
     * @param from Nodo desde el que se parte
     * @param to Nodo destino
     * @param weight Peso, Coste o distancia elegida
     */
    void connect(E from, E to, int weight);

    /**
     * Método que nos devuelve la existencia de un elemento en el grafo
     *
     * @param element elemento para comprobar existencia
     * @return True en caso de existir, Fale en caso contrario
     */
    boolean contains(E element);

    /**
     * Método que calculará la ruta más corta entre dos nodos, en caso de
     * existir. Utiliza el algoritmo Dijkstra
     *
     * @param from Nodo partida
     * @param to Nodo destino
     * @return path o ruta encontrada
     */
    List<Node<E>> shortestPath(E from, E to);

    @Override
    public String toString();

}
