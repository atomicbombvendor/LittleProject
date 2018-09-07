package com.company.Arithmetic.FirstLetterSearch;

import java.util.Arrays;
import java.util.List;

public class Node {
    public static final char A = 65;
    public static final char Z = 90;

    // A = 65 Z = 90
    /*当前字符　A-Z*/
    private char letter;

    private int startPosition;
    private int endPosition;

    private Node parent; //父节点
    private Node[] nodes; //用来存储子节点的数组，最多有26个子节点

    /**
     * 构造节点
     * @param letter
     * @param currentNode
     */
    public Node(char letter, Node currentNode) {
        if (letter < A || letter > Z) throw new RuntimeException("char error ---> " + letter);
        this.letter = letter;
        this.nodes = new Node[26];
        setParent(currentNode);
        if (currentNode != null) currentNode.setNodeByLetter(letter, this);
    }

    public Node getNodeByLetter(char ch) {
        int index = getIndexByChar(ch);
        return nodes[index];
    }

    public void setNodeByLetter(char ch, Node node) {
        int index = getIndexByChar(ch);
        nodes[index] = node;
    }

    /**
     * 得到字符的index ch-A
     * @param ch 参数
     * @return
     */
    private int getIndexByChar(char ch) {
        int index = ch - A;
        return index;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
        if (parent != null) parent.setEndPosition(endPosition);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getList() {
        return Arrays.asList(nodes);
    }
}
