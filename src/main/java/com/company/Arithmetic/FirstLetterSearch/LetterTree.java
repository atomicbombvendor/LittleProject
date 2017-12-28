package com.company.Arithmetic.FirstLetterSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterTree {

    private Node root;
    private HashMap<String, List<Node>> map;
    private List<Node> nodes;

    public static List<String> forListSearch(String text, List<String> list) {
        List<String> result = new ArrayList<>();
        for (String item : list) {
            if (item.contains(text)) result.add(item);
        }
        return result;
    }
//---------------------------------------------------------------------

    public List<Node> treeSearch(String text) {
        if (text == null || text.length() < 2) throw new RuntimeException("字符数必须大于等于两个~!");
        List<Node> result = new ArrayList<>();

        String anchor = text.substring(0, 2);
        List<Node> list = map.get(anchor);
        if (list == null || list.size() == 0) return result;

        text = text.substring(2);
        if (list == null) list = result;
        if (isEmpty(text)) return list;

        for (Node node : list) {
            if (node == null) continue;
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                node = node.getNodeByLetter(ch);
                if (node == null) break;
            }
            if (node == null) continue;
            result.add(node);
        }
        return result;
    }

    /**
     * 构造tree
     * @param list
     */
    public void buildTree(List<String> list) {
        checkNull(list);
        root = new Node('A', null);//初始化根节点
        nodes = new ArrayList<>(); //总节点数
        Node currentNode = root;

        for (int index = 0; index < list.size(); index++) {
            String str = list.get(index);
            if (isEmpty(str)) continue;
            str = str.toUpperCase();// 转大写

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                //从当前的节点得到属于字符ch的节点
                Node node = currentNode.getNodeByLetter(ch);
                //如果没有找到，重新添加 包括 startPosition
                if (node == null) {
                    node = new Node(ch, currentNode);
                    node.setStartPosition(index);

                    nodes.add(node);
                }
                node.setEndPosition(index + 1);
                currentNode = node;
            }
            currentNode = root;
        }
        root.getList(); //root节点下面的子节点的list
        buildIndex();
    }

    public void buildIndex() {
        checkNull(root);

        map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            Node parent = node.getParent();
            if (parent == root) continue;
            String anchor = sb.append(parent.getLetter()).append(node.getLetter()).toString();
            sb.delete(0, sb.length());
            List<Node> list = map.get(anchor);
            if (list == null) {
                list = new ArrayList<>();
                map.put(anchor, list);
            }
            list.add(node);
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private void checkNull(Object obj) {
        if (obj == null) throw new NullPointerException("参数为空~!");
    }

    public Node getRoot() {
        return root;
    }
}
