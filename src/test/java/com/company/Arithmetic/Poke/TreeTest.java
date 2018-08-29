package com.company.Arithmetic.Poke;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

public class TreeTest {

    @Test
    public void printTree() {
        TreeUtils.printTree(createTree(), " ");
    }

    @Test
    public void findDataInTree() {
    }

    @Test
    public void deleteAllLeaf() {
    }

    @Test
    public void displayTree() {
        TreeUtils.displayTree(createTree());
    }

    private TreeNode<String> createTree(){
        TreeNode<String> root = new TreeNode<>("root");

        TreeNode<String> node1 = root.addChild(new TreeNode<>("Node 1"));

        TreeNode<String> node11 = node1.addChild(new TreeNode<>("node 11"));
        TreeNode<String> node111 = node11.addChild(new TreeNode<>("node 111"));
        TreeNode<String> node112 = node11.addChild(new TreeNode<>("node 112"));

        TreeNode<String> node12 = node1.addChild(new TreeNode<>("node 12"));

        TreeNode<String> node2 = root.addChild(new TreeNode<>("node 2"));

        TreeNode<String> node21 = node2.addChild(new TreeNode<>("node 21"));
        TreeNode<String> node211 = node2.addChild(new TreeNode<>("node 22"));
        return root;
    }

}