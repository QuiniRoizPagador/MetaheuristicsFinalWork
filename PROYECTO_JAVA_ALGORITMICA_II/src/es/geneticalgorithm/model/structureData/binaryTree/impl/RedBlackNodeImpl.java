/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structureData.binaryTree.impl;

import es.geneticalgorithm.model.structureData.binaryTree.BinaryNode;
import es.geneticalgorithm.model.structureData.binaryTree.BlackRedNode;

/**
 * Clase que representa la implementación de un nodo para un árbol rojinegro
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public class RedBlackNodeImpl<E extends Comparable> extends BinaryNodeImpl<E> implements BlackRedNode<E> {

    private static enum colors {
        RED,
        BLACK
    }
    private Enum color = colors.RED;

    public RedBlackNodeImpl(E ele) {
        super(ele);
        color = colors.RED;
    }

    public RedBlackNodeImpl(E ele, BinaryNode<E> left, BinaryNode<E> right, BinaryNode<E> parent) {
        super(ele, left, right, parent);
        color = colors.RED;
    }

    @Override
    public BlackRedNode<E> getParent() {
        return (BlackRedNode<E>) super.getParent();
    }

    @Override
    public void setParent(BlackRedNode<E> parent) {
        super.setParent(parent);
    }

    @Override
    public boolean isRed() {
        return color.equals(colors.RED);
    }

    @Override
    public boolean isBlack() {
        return color.equals(colors.BLACK);
    }

    @Override
    public Enum color() {
        return color;
    }

    @Override
    public void setRed() {
        color = colors.RED;
    }

    @Override
    public void setBlack() {
        color = colors.BLACK;
    }

    @Override
    public void inverseColor() {
        if (isRed()) {
            color = colors.BLACK;
        } else {
            color = colors.RED;
        }
    }

    @Override
    public void setColor(Enum color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && color.equals(((BlackRedNode<E>) obj).color());
    }

}
