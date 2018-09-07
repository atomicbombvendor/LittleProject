package com.company.arithmetic.poke;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public class TreeUtils {

    /**
     * 递归打印Tree
     * @param tree 树
     * @param appender 每一个节点的之间的分割
     * @param <T> 范型
     */
    public static <T> void printTree(TreeNode<T> tree, String appender){
        System.out.println(appender + "\\" +tree.getData());
        if (Objects.isNull(tree.getChildren())){return;}
        tree.getChildren().forEach(each ->
                printTree(each, appender + appender)
        );
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

    /**
     * 使用树形结构显示
     */
    public static void displayTree(TreeNode root){
        Stack globalStack=new Stack();
        globalStack.push(root);
        // int nBlank=32;
        int nBlank=32;
        boolean isRowEmpty=false;
        String dot="............................";
        System.out.println(dot+dot+dot);
        while (!isRowEmpty){
            Stack localStack=new Stack();
            isRowEmpty=true;
            for (int j=0;j<nBlank;j++){
                System.out.print("-");
            }
            while (!globalStack.isEmpty()){
                //里面的while循环用于查看全局的栈是否为空
                TreeNode temp=(TreeNode)globalStack.pop();
                if (temp!=null){
                    System.out.print(temp.getData());

                    //如果当前的节点下面还有子节点，则必须要进行下一层的循环
                    if (Objects.nonNull(temp.getChildren()) || !temp.getChildren().isEmpty()){
                        temp.getChildren().forEach(localStack::push);
                        isRowEmpty=false;
                    }
                }else {
                    //如果全局的栈则不为空
                    System.out.print("#!");
                    localStack.push(null);
                    localStack.push(null);

                }


                //打印一些空格
                for (int j=0;j<nBlank*2-2;j++){
                    //System.out.print("&");
                    System.out.print("*");
                }




            }//while end


            System.out.println();
            nBlank/=2;
            //这个while循环用来判断，local栈是否为空,不为空的话，则取出来放入全局栈中
            while (localStack.isEmpty()==false){
                globalStack.push(localStack.pop());
            }

            // }
        }//大while循环结束之后，输出换行
        System.out.println(dot+dot+dot);

    }

}
