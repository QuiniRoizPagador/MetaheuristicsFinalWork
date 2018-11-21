/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.structureData.binaryTree.impl;

import es.geneticalgorithm.model.structureData.node.NodeImpl;
import es.geneticalgorithm.model.structureData.binaryTree.BinaryNode;

/**
 * Clase BinaryNode utilizada para implementación de árboles binarios
 *
 * @param <E> Clase con la que se trabajará, que implemente la interfaz
 * comparable
 *
 * @author Quini Roiz
 */
public class BinaryNodeImpl<E extends Comparable> extends NodeImpl<E> implements BinaryNode<E> {

    private BinaryNode<E> left;
    private BinaryNode<E> right;
    private BinaryNode<E> parent;

    public BinaryNodeImpl(E ele) {
        super(ele);
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public BinaryNodeImpl(E ele, BinaryNode<E> l, BinaryNode<E> r, BinaryNode<E> p) {
        super(ele);
        left = l;
        right = r;
        parent = p;
    }

    @Override
    public boolean hasLeft() {
        return left != null;
    }

    @Override
    public boolean hasRight() {
        return right != null;
    }

    @Override
    public BinaryNode<E> getLeft() {
        return left;
    }

    @Override
    public BinaryNode<E> getRight() {
        return right;
    }

    @Override
    public void setLeft(BinaryNode<E> left) {
        this.left = left;
    }

    @Override
    public void setRight(BinaryNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return super.getElement().toString();
    }

    @Override
    public BinaryNode<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryNode<E> parent) {
        this.parent = parent;
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof BinaryNode) {
            BinaryNode node = (BinaryNode<E>) obj;
            if (node.getElement() == null) {
                return false;
            }
            return node.getElement().equals(getElement());
        }
        return false;
    }

}
