/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.tue.s2id90.group40;
import java.util.ArrayList;

/**
 *
 * @author s135578
 */
public class Tree<T> {
    private GameNode root;

    public Tree(T rootData) {
        root = new GameNode();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
}
