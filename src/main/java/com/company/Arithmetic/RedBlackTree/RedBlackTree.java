package com.company.Arithmetic.RedBlackTree;

/**
 * Created by eli9 on 9/14/2017.
 */

/**
 * 在RBTree中包含了根节点mRoot和红黑树的相关API。
 * 在RBTree中包含了根节点mRoot和红黑树的相关API。
 * @param <T> T 表示范型，引用T类型的类型都要实现Comparable<>
 */
public class RedBlackTree<T extends Comparable<T>> {

    private RedBlackTreeNode<T> mRoot; //根节点

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree(){}
    /**
     * RBTNode是红黑树的节点类。
     */
    public static class RedBlackTreeNode<T extends Comparable<T>>{
        boolean color; //颜色
        T key;//键值
        RedBlackTreeNode<T> left; //左孩子
        RedBlackTreeNode<T> right; //右孩子
        RedBlackTreeNode<T> parent; //父节点

        public RedBlackTreeNode(
                T key,
                boolean color,
                RedBlackTreeNode<T> parent,
                RedBlackTreeNode<T> left,
                RedBlackTreeNode<T> right){
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    /**
    * 对红黑树的节点(x)进行左旋转
    *
    * 左旋示意图(对节点x进行左旋)：
    *      px                             px
    *     /                              /
    *    x                              y
    *   /  \      --(左旋)-.            / \                #
    *  lx   y                         x  ry
    *     /   \                      / \
    *    ly   ry                    lx ly
    *
    *
    */
    private void leftRotate(RedBlackTreeNode<T> x){

        RedBlackTreeNode<T> y = x.right;

        //y的左孩子设为X的右孩子
        x.right = y.left;
        //将X设为Y的左孩子的父亲
        if(y.left != null){
            y.left.parent = x;
        }

        //将X的父亲为Y的父亲赋值（y.p = px）
        y.parent = x.parent;

        //情况1 如果 x的父亲 是空节点， 则将Y设为根节点
        if(x.parent == null){
            this.mRoot = y;
        }else{
            //如果 X是它自己父节点的左孩子，将Y设为X父节点的左孩子
            if(x.parent.left == x){
                x.parent.left = y;
            }else{
                //如果 X是它父节点的右孩子，将Y也为X父节点的右孩子
                x.parent.right = y;
            }
        }

        //将X设为Y的左孩子
        y.left = x;
        //将 X的父节点设为Y
        x.parent = y;
    }

    /**
    * 对红黑树的节点(y)进行右旋转
    *
    * 右旋示意图(对节点y进行左旋)：
    *            py                               py
    *           /                                /
    *          y                                x
    *         /  \      --(右旋)-.             / \                     #
    *        x   ry                          lx  y
    *       / \                                 / \                   #
    *      lx  rx                              x  ry
    *
    */
    private void rigthRatate(RedBlackTreeNode<T> y){
        // 设置x是当前节点的左孩子。
        RedBlackTreeNode<T> x = y.left;

        // 将 “x的右孩子” 设为 “y的左孩子”；
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        y.left = x.right;
        if(x.right != null){
            x.right.parent = y;
        }

        // 将 “y的父亲” 设为 “x的父亲”
        x.parent = y.parent;

        if(y.parent == null){
            mRoot = x; // 如果 “y的父亲” 是空节点，则将x设为根节点
        }else{
            if(y.parent.left == y) {
                y.parent.left = x; // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
            }else{
                y.parent.right = x; // 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
            }
        }

        // 将 “y” 设为 “x的右孩子”
        x.right = y;
        // 将 “y的父节点” 设为 “x”
        y.parent = x;
    }

    /**
    * 将结点插入到红黑树中
    *
    * 参数说明：
    *     node 插入的结点        // 对应《算法导论》中的node
    */
    private void insert(RedBlackTreeNode<T> node){
        int cV;//compare value
        RedBlackTreeNode<T> y = null;
        RedBlackTreeNode<T> x = this.mRoot;//设置X为根节点

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while(x != null){
            y = x;//Y为要插入的节点
            cV = node.key.compareTo(x.key);
            if(cV < 0){
                x = x.left;
            }else{
                x = x.right;
            }
        }


        node.parent = y;
        if(y != null){
            //设置要插入的Node节点为Y节点的左节点还是右节点
            cV = node.key.compareTo(y.key);
            if(cV < 0){
                y.left = node;
            }else{
                y.right = node;
            }
        }else{
            this.mRoot = node;
        }

        // 2. 设置节点的颜色为红色
        node.color = RED;
        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);//修正二叉查找树
    }

    /**
    * 新建结点(key)，并将其插入到红黑树中
    *
    * 参数说明：
    *     key 插入结点的键值
    */
     void insert(T key) {

        RedBlackTreeNode<T> node =
                new RedBlackTreeNode<>(key,BLACK,null,null,null);
        insert(node);
    }

    /**
    * 红黑树插入修正函数
    *
    * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
    * 目的是将它重新塑造成一颗红黑树。
    *
    * 参数说明：
    *     node 插入的结点        // 对应《算法导论》中的z
    */
    private void insertFixUp(RedBlackTreeNode<T> node){

        RedBlackTreeNode<T> parent, gParent;

        while((((parent = parentOf(node)) != null) && isRed(parent) )){
            gParent = parentOf(parent);
            if(gParent == null) {
                return;
            }

            //若“父节点”是“祖父节点的左孩子”
            if((parent == gParent.left)){
                // Case 1条件：叔叔节点存在，是红色
                RedBlackTreeNode<T> uncle = gParent.right;
                if((uncle != null) && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }

                // Case 2条件：叔叔是黑色或不存在，且当前节点是右孩子
                if(parent.right == node){
                    RedBlackTreeNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node =tmp;
                }

                // Case 3条件：叔叔是黑色或不存在，且当前节点是左孩子。
                setBlack(parent);
                setRed(gParent);
                rigthRatate(gParent);
            }else{
                //若“父节点”是“祖父节点的右孩子”
                //// Case 1条件：叔叔节点是红色，且存在
                RedBlackTreeNode<T> uncle = gParent.left;
                if((uncle != null) && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }
                // Case 2条件：叔叔是黑色或不存在，且当前节点是左孩子
                if(parent.left == node){
                    RedBlackTreeNode<T> tmp;
                    rigthRatate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色或不存在，且当前节点是右孩子。
                setBlack(parent);
                setRed(gParent);
                leftRotate(gParent);
            }
        }

        // 将根节点设为黑色
        setBlack(this.mRoot);
    }

    private RedBlackTreeNode<T> parentOf(RedBlackTreeNode<T> node){
        return node.parent;
    }

    /*
     * 前序遍历"红黑树"
     */
    private void preOrder(RedBlackTreeNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
         preOrder(mRoot);
     }

    /**
     * 中序遍历"红黑树"
     */
     private void inOrder(RedBlackTreeNode<T> tree) {
        if(tree != null) {
             inOrder(tree.left);
             System.out.print(tree.key+" ");
             inOrder(tree.right);
         }
    }


    /**
      * 后序遍历"红黑树"
      */
     private void postOrder(RedBlackTreeNode<T> tree) {

         if (tree != null) {
             postOrder(tree.left);
             postOrder(tree.right);
             System.out.print(tree.key + " ");
         }
     }

    public void postOrder() {

        postOrder(mRoot);
    }

     public void inOrder() {

        inOrder(mRoot);
     }

     /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(RedBlackTreeNode<T> tree, T key, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree) ? "R" : "B", key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }

    private boolean isRed(RedBlackTreeNode<T> node){
        return !node.color;//RED is false; BLACK is true
    }

    private void setBlack(RedBlackTreeNode<T> node){
        node.color = BLACK;
    }

    private void setRed(RedBlackTreeNode<T> node){
        node.color = RED;
    }
}
