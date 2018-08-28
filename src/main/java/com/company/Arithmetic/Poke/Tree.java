package com.company.Arithmetic.Poke;

import java.util.List;
import java.util.Optional;

public class Tree {

    /**
     * 递归打印Tree
     * @param tree 树
     * @param appender 每一个节点的之间的分割
     * @param <T> 范型
     */
    public static <T> void printTree(TreeNode<T> tree, String appender){
        System.out.println(appender + tree.getData());
        tree.getChildren().forEach(each -> printTree(each, appender + appender));
    }

    /**
     * 递归查找树中的数据
     * @param tree 树
     * @param searchData 要查询的数据
     * @param <T> 范型
     * @return 查找到的节点
     */
    public static <T> Optional<TreeNode<T>> findDataInTree(TreeNode<T> tree, T searchData){
        if (tree.getData().equals(searchData)){
            return Optional.of(tree);
        }
        for(TreeNode<T> each : tree.getChildren()){
            System.out.println(each.getData());
            Optional<TreeNode<T>> findDataInTree = findDataInTree(each, searchData);
            if (findDataInTree.isPresent()){
                return findDataInTree;
            }
        }
        return Optional.empty();
    }


    /**
     * 递归删除tree节点的所有子节点
     * @param tree 树 或者 树的一个节点
     * @param toBeDeletedChildren 记录删除的节点
     * @param <T> 范型
     */
    public <T> void deleteAllLeaf(TreeNode<T> tree, List<TreeNode<T>> toBeDeletedChildren){
        tree.getChildren().forEach(each -> {
            if (each.getChildren().isEmpty()){
                toBeDeletedChildren.add(each);
            }
            deleteAllLeaf(each, toBeDeletedChildren);
        });
    }
}
