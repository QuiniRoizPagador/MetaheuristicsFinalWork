/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.graph;

import es.geneticalgorithm.model.structuredata.node.Node;
import es.geneticalgorithm.model.structuredata.node.NodeImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementación de la clase Grafo, utilizando una matriz de adyacencia
 *
 * @author Quini Roiz
 * @param <E> Utilizaremos los tipos genéricos de datos para trabajar en esta
 * implementación
 */
public class GraphImpl<E> implements Graph<E> {

    private int[][] graph;
    private List<Node<E>> nodes;
    private int size;

    public GraphImpl() {
        this(0);
    }

    public GraphImpl(int n) {
        graph = new int[n][n];
        nodes = new ArrayList<>();
    }

    @Override
    public void addNode(E element) {
        if (size++ > graph.length) {
            ampliarMatriz();
        }
        Node<E> v = new NodeImpl<>(element);
        nodes.add(v);
        size++;
    }

    // método utilidad para ampliar la matriz
    private void ampliarMatriz() {
        int[][] aux = new int[size + 1][size + 1];
        // creamos matriz auxiliar y copiamos el contenido 
        // de la original en ella
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                aux[i][j] = graph[i][j];
            }
        }
        // actualizamos la referencia de la original
        graph = aux;
    }

    @Override
    public void connect(E from, E to, int weight) {
        Node<E> vFrom = new NodeImpl(from);
        Node<E> vTo = new NodeImpl(to);
        int nFrom = nodes.indexOf(vFrom);
        int nTo = nodes.indexOf(vTo);
        graph[nFrom][nTo] = weight;
    }

    @Override
    public boolean contains(E vertex) {
        return nodes.indexOf(vertex) >= 0;
    }

    @Override
    public List<Node<E>> shortestPath(E from, E to) {
        Node<E> vFrom = new NodeImpl(from);
        Node<E> vTo = new NodeImpl(to);

        int nFrom = nodes.indexOf(vFrom);
        int nTo = nodes.indexOf(vTo);
        if (nFrom < 0 || nTo < 0) {
            throw new NullPointerException();
        }
        System.out.println("Calculando ruta desde " + from + " hasta " + to + "...");
        if (hasSomeNeighbour(nFrom)) {
            List<Node<E>> path = dijsktra(nodes.get(nFrom), nodes.get(nTo));
            return path;
        } else {
            System.out.println("No es posible calcular la ruta.");
            return null;
        }
    }

    private List<Node<E>> dijsktra(Node<E> from, Node<E> to) {

        // creamos la cola de prioridad
        PriorityQueue<Node<E>> q = new PriorityQueue<>();
        for (Node n : nodes) {
            // a cada nodo le anulamos el previo
            n.setPrevious(null);
            // por defecto todos tienen distancia infinita
            n.setCost(Integer.MAX_VALUE);
            // ninguno se habrá visitado
            n.setUnvisited();
        }
        // nuestro nodo inicio no tendrá coste a sí mismo
        from.setCost(0);
        // lo añadimos a la cola de prioridad
        q.add(from);
        // flag con el chivato de encontrado
        boolean found = false;
        // repetir mientras no encontrado y lista no vacía
        while (!found && !q.isEmpty()) {
            // 1 Pull el nodo A con el menor coste de la cola
            Node<E> a = q.poll();
            // 2. Si este nodo es el destino, parar. Tenemos una ruta
            if (a.equals(to)) {
                // cambiamos la flag de encontrado
                found = true;
            } else { // en caso contrario, sacar todos los vecinos de A
                a.setVisited();
                // obtengo el índice del nodo actual A
                int indexA = nodes.indexOf(a);
                // obtengo los vecinos de A y los llamo b
                for (Node<E> b : nodes) {
                    // por cada nodo b vecino de a:
                    // obtengo su índice
                    int indexB = nodes.indexOf(b);
                    // si la distancia en la matriz de adyacencia
                    // es distinta a 0, es vecino
                    // si la distancia además es menor que la que ya tiene
                    // y si no ha sido visitado
                    if (!b.isVisited() && graph[indexA][indexB] != 0
                            && b.getCost() > a.getCost() + graph[indexA][indexB]) {
                        // actualizo el coste
                        b.setCost(a.getCost() + graph[indexA][indexB]);
                        // marco a como previo a b
                        b.setPrevious(a);
                        // añado b a la cola
                        q.add(b);
                    }
                }
            }
        }
        // creamos el listado para volcado
        List<Node<E>> path = new ArrayList<>();
        // almacenamos el recorrido
        if (to.hasPrevious()) {
            Node<E> n = to;
            do {
                path.add(n);
                n = n.getPrevious();
            } while (n != null);
            // invertimos el orden
            Collections.reverse(path);
        }
        // devolvemos la ruta encontrada
        return path;
    }

    private boolean hasSomeNeighbour(int n) {
        boolean has = false;
        int i = 0;
        while (!has && i < size) {
            if (graph[n][i] != 0) {
                has = true;
            }
            i++;
        }
        return has;
    }

    @Override
    public String toString() {
        String res = "";
        res += "----------------------------\n";
        res += "NODOS DISPONIBLES: \n";
        res += "----------------------------\n";
        res = nodes.stream().map((n) -> n + "\n").reduce(res, String::concat);
        res += "----------------------------\n";
        res += "----------------------------\n";
        return res;
    }

}
