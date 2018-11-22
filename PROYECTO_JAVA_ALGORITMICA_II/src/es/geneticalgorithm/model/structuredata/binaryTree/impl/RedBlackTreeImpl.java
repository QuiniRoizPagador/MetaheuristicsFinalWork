/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structuredata.binaryTree.impl;
import java.util.Iterator;
import es.geneticalgorithm.model.structuredata.binaryTree.BlackRedNode;

/**
 * Clase que representa la implementación de un árbol rojinegro
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public class RedBlackTreeImpl<E extends Comparable> extends SearchBinaryTreeImpl<E> {

    private BlackRedNode<E> first;

    public RedBlackTreeImpl(E first) {
        super(first);
    }

    public RedBlackTreeImpl() {
        super();
    }

    @Override
    public void insert(E element) {
        // insertamos igual que los árboles de búsquedas
        BlackRedNode<E> node = new RedBlackNodeImpl<>(element);
        BlackRedNode<E> root = (BlackRedNode<E>) insert(getRoot(), node);
        setRoot(root);
        size++;
        balance(node);
        first = (BlackRedNode<E>) getFirst();
    }

    private void balance(BlackRedNode<E> x) {
        // Caso Trivial: Si el padre del nodo insertado es negro, 
        // no se realiza ningún ajuste (el árbol es correcto)
        BlackRedNode<E> y = x.getParent();
        // cuando el padre no exista se cambia el color del nodo a negro y se termina el bucle
        if (y == null) {
            x.setBlack();
        } // En caso contrario entramos en un bucle.
        else if (y.isRed()) // El padre del nodo debe existir y ser rojo 
        {
            // Traemos al abuelo. Debe existir (no puede ser raíz un nodo rojo)
            BlackRedNode<E> z = (BlackRedNode<E>) y.getParent();
            // También traemos al tío, hermano del nodo padre
            BlackRedNode<E> t = (BlackRedNode<E>) getBrother(y);

            // caso 1: Tío rojo, nodo x izquierdo o derecho
            if (t != null && t.isRed())// Cambiamos de color a los nodos y, z y t
            {
                y.setBlack();
                z.setRed();
                t.setBlack();
                // comprobamos el nodo abuelo por si se ha violado la primera condición
                balance(z);
            } // Caso 2: Tío negro, nodo x es hijo derecho
            // conntemplaremos ambos casos simétricos
            else if ((z.hasRight() && z.getRight().equals(y) && y.hasLeft() && y.getLeft().equals(x))
                    || (z.hasLeft() && z.getLeft().equals(y) && y.hasRight() && y.getRight().equals(x))) {
                if (z.hasRight() && z.getRight().equals(y)) {
                    rotateSimpleRight(x, y, z);
                } else {
                    rotateSimpleLeft(x, y, z);
                }
                balance(y);
            }// Caso 3: Tío negro, nodo x es hijo izquierdo
            else if ((z.hasRight() && z.getRight().equals(y) && y.hasRight() && y.getRight().equals(x))
                    || (z.hasLeft() && z.getLeft().equals(y) && y.hasLeft() && y.getLeft().equals(x))) {
                if (z.hasRight() && z.getRight().equals(y)) {
                    rotateDoubleLeft(y, z);
                } else {
                    rotateDoubleRight(y, z);
                }
                z.inverseColor();
                y.inverseColor();
            }
        }
    }

    // rotacion simple izquierda
    private void rotateSimpleLeft(BlackRedNode<E> x, BlackRedNode<E> y, BlackRedNode<E> z) {
        z.setLeft(x);
        y.setRight(x.getLeft());
        x.setLeft(y);
        if (y.hasRight()) {
            y.getRight().setParent(y);
        }
        x.setParent(z);
        y.setParent(x);
    }

    // rotación simple derecha
    private void rotateSimpleRight(BlackRedNode<E> x, BlackRedNode<E> y, BlackRedNode<E> z) {
        z.setRight(x);
        if (x != null) {
            y.setLeft(x.getRight());
            x.setRight(y);
            x.setParent(z);
        }

        if (y.hasLeft()) {
            y.getLeft().setParent(y);
        }
        y.setParent(x);
    }

    // rotación derecha izquierda
    private void rotateDoubleLeft(BlackRedNode<E> y, BlackRedNode<E> z) {

        BlackRedNode<E> r = z.getParent();
        z.setRight(y.getLeft());
        y.setLeft(z);
        z.setParent(y);
        if (z.hasRight()) {
            z.getRight().setParent(z);
        }
        if (r != null) {
            if (r.hasRight() && r.getRight().equals(z)) {
                r.setRight(y);
            } else if (r.hasLeft()) {
                r.setLeft(y);
            }
            y.setParent(r);
        } else // En caso de no existir es que hemos llegado al nuevo root
        {
            setRoot(y);
        }
    }

    // rotación derecha doble
    private void rotateDoubleRight(BlackRedNode<E> y, BlackRedNode<E> z) {
        BlackRedNode<E> r = z.getParent();
        z.setLeft(y.getRight());
        y.setRight(z);
        z.setParent(y);
        if (z.hasLeft()) {
            z.getLeft().setParent(z);
        }
        if (r != null) {
            if (r.hasRight() && r.getRight().equals(z)) {
                r.setRight(y);
            } else if (r.hasLeft()) {
                r.setLeft(y);
            }
            y.setParent(r);
        } else // En caso de no existir es que hemos llegado al nuevo root
        {
            setRoot(y);
        }
    }

    @Override
    public void remove(E element) {
        // eliminamos igual que los árboles de búsqueda

        // si el nodo a borrar es el nodo raíz se elimina la raíz y
        // si el nuevo nodo raíz es de color rojo se cambia su color a negro
        if (isRoot(element)) {
            // Eliminamos el nodo en cuestión
            super.remove(element);
            if (getRoot() != null && ((BlackRedNode<E>) getRoot()).isRed()) {
                ((BlackRedNode<E>) getRoot()).setBlack();
            }
        } else {
            // necesitamos conocer los nodos x y z, es decir, hijo y padre del 
            // nodo a eliminar en el caso de que p sea rojo
            BlackRedNode<E> p = (BlackRedNode<E>) searchElement(element);
            BlackRedNode<E> x;
            if (p.hasLeft()) {
                E maxValue = maxValue(p.getLeft());
                if (maxValue != null) {
                    x = (BlackRedNode<E>) searchElement(maxValue);
                } else {
                    x = null;
                }
            } else {
                x = (BlackRedNode<E>) p.getRight();
            }
            // primeramente necesitamos al padre (z), por si fuera nulo x
            BlackRedNode<E> aux = p.getParent();
            BlackRedNode<E> z = new RedBlackNodeImpl(aux.getElement(), aux.getLeft(), aux.getRight(), aux.getParent());
            z.setColor(aux.color());
            super.remove(element);
            // Caso trivial 1: P es rojo: no hacemos nada
            if (!p.isRed() && (x == null || !x.isRed())) {
                //una vez tenemos los datos necesarios entramos en iteración
                balanceRemove(x, z);
            } else if (x != null && x.isRed()) {
                x.setBlack();
            }
        }
        first = (BlackRedNode<E>) getFirst();
    }

    private void balanceRemove(BlackRedNode<E> x, BlackRedNode<E> z) {

        // necesitamos al nodo hermano del que se va a eliminar (y)
        BlackRedNode<E> y;
        if ((x == null && !z.hasLeft()) || (x != null && x.equals(z.getLeft()))) {
            y = (BlackRedNode<E>) z.getRight();
        } else {
            y = (BlackRedNode<E>) z.getLeft();
        }
        // Caso trivial 2: Hijo x rojo
        if (x != null && x.isRed()) {
            // se inclumple la condición de que los hijos de z
            // deben tener la misma altura negra.
            // modificamos para ello el color
            x.setBlack();
        } else { // Comenzamos casos no triviales

            if (y != null) {
                BlackRedNode<E> a = (BlackRedNode<E>) y.getLeft();
                BlackRedNode<E> b = (BlackRedNode<E>) y.getRight();
                // Caso 1: Y es rojo, Z negro
                if (y.isRed() && z.isBlack()) {
                    z.setRed();
                    y.setBlack();
                    if (z.hasRight() && z.getRight().equals(y)) {
                        rotateDoubleLeft(y, z);
                    } else {
                        rotateDoubleRight(y, z);
                    }
                    /* x = z;
                    z = y;*/
                    balanceRemove(x, z);
                }// de ahora en adelante tomamos Y como negro no null
                else if (z.isBlack()
                        && ((a != null && a.isBlack()) || a == null)
                        && ((b != null && b.isBlack()) || b == null)) {
                    // Caso 2: Hermano negro no nulo, sobrinos negros, padre negro
                    y.setRed();
                    if (!getRoot().equals(z)) {
                        x = z;
                        z = z.getParent();
                        balanceRemove(x, z);
                    }
                } else if (z.isRed()
                        && ((a != null && a.isBlack()) || a == null)
                        && ((b != null && b.isBlack()) || b == null)) {
                    y.setRed();
                    z.setBlack();
                } else {
                    // Dos casos finales, 4 y 5: A partir de aquí se considera padre cualquier color
                    // y se estudiará en los dos siguientes casos (y sus simétricos)
                    // el color de los sobrinos mientras el hermano(y) sea negro no nulo

                    if (a != null && a.isRed()
                            && ((b != null && b.isBlack())
                            || b == null)) {
                        // Caso 4a: hermano negro no nulo, sobrinos rojo/negro, padre cualquier color
                        rotateSimpleRight(a, y, z);
                        //se vuelve a comprobar con los mismos nodos x y z, se caerá en caso 5
                        balanceRemove(x, z);
                    } else if (b != null && b.isRed()
                            && ((a != null && a.isBlack())
                            || a == null)) {
                        // Caso 4b: hermano negro no nulo, sobrinos negro/rojo, padre cualquier color
                        rotateSimpleLeft(a, y, z);
                        balanceRemove(x, z);
                    } else if (((a != null && (a.isBlack() || a.isRed()))
                            || a == null) && b != null && b.isRed()) {
                        // Caso 5: Hermano negro no nulo, sobrinos cualquiera/rojo
                        if (z.hasRight() && z.getRight().equals(y)) {
                            rotateDoubleLeft(y, z);
                        } else {
                            rotateDoubleRight(y, z);
                        }
                        y.setColor(z.color());
                        z.setBlack();
                        b.setBlack();
                    }

                }
            }

        }
    }

    /**
     * Método que saca la cabeza de la cola de prioridad
     *
     * @return Devolverá el contenido del nodo en caso de no ser nulo
     */
    public E pull() {
        if (first != null) {
            BlackRedNode<E> f = first;
            remove(first.getElement());
            return f.getElement();
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        if (isEmpty()) {
            res = "NO HAY MÁS DATOS QUE MOSTRAR";
        } else {
            Iterator it = nodes();
            while (it.hasNext()) {
                BlackRedNode node = (BlackRedNode) it.next();
                res += "\n----------------------------------\n" + node.getElement() + "(" + node.color() + ")";
            }
        }
        return res;
    }

}
