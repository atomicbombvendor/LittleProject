package com.company.Arithmetic.Poke;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PokeTest {

    @Test
    public void diviendPokeRule() {
        Poke.dividendPokeRule("89A354TKJQ");

        Integer[] list = new Integer[5];
        list[0] = 1;
        list[1] = 5;
        list[2] = 4;
        list[3] = 3;
        list[4] = 2;

        Arrays.sort(list);

        list.toString();
    }

    /**
     * 该算法使用递归方式实现,采用深度优先遍历树的节点,同时记录下已经遍历的节点保存在栈中。
     * 当遇到叶子节点时,输出此时栈中的所有元素,即为当前的一条路径;然后pop出当前叶子节点
     * @param stack 为深度优先遍历过程中存储节点的栈
     * @param root 为树的要节点或子树的根节点
     * @param pathList 为树中所有从根到叶子节点的路径的列表
     */
    public void buildPath(Stack stack, Item root, List pathList) {
        if (root != null) {
            stack.add(root.getValue());
            if (root.getNextItem().size() == 0) {
                changeToPath(stack, pathList); // 把值栈中的值转化为路径
            } else {
                List items = root.getNextItem();
                for (int i = 0; i < items.size(); i++) {
                    buildPath(stack, items.get(i), pathList);
                }
            }
            stack.remove(stack.size() - 1);
        }
    }

    /**
     * @param path
     * @param pathList
     */
    public void changeToPath(List path, List pathList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) != null) {
                sb.append(path.get(i) + "");
            }
        }
        pathList.add(sb.toString().trim());
    }
}