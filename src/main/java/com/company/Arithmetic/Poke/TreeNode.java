package com.company.Arithmetic.Poke;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeNode<T> {

    private T data;
    private TreeNode<T> parent;
    private List<TreeNode> children;

    public TreeNode(T data){
        this.data = data;
    }

    public TreeNode<T> addChild(TreeNode<T> child){
        if (Objects.isNull(children)){
            this.children = new ArrayList<>(0);
        }
        //为要添加的子节点设置父节点
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChild(List<TreeNode<T>> children){
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public TreeNode getRoot(){
        if (Objects.isNull(parent)){
            return this;
        }
        return parent.getRoot();
    }

    /**
     * 删除当前节点
     */
    public void deleteTreeNode(){
        if (Objects.isNull(parent)){
            // 得到当前节点在父节点的孩子中的位置
            int index = this.parent.getChildren().indexOf(this);
            //移除当前节点
            this.parent.getChildren().remove(index);
            // 把当前节点的所有子节点重新设置父节点
            for (TreeNode<T> each : this.getChildren()) {
                each.setParent(this.parent);
            }
            // 把当前节点所有子节点，加入到当前节点父节点的子节点中
            this.parent.getChildren().addAll(index, this.getChildren());
        }else {
            // 如果当前节点是根节点
            deleteRootNode();
        }
    }

    /**
     * 删除根节点，把第一个子节点当做根节点
     * @return 新的根节点
     */
    public TreeNode<T> deleteRootNode(){
        if (Objects.nonNull(parent)){
            throw new IllegalStateException("deleteRootNode not called on root");
        }
        TreeNode<T> newParent = null;
        if (!this.getChildren().isEmpty()){
            newParent = this.getChildren().get(0);
            newParent.setParent(null);
            this.getChildren().remove(0);
            for (TreeNode<T> each: getChildren()) {
                each.setParent(newParent);
            }
            newParent.getChildren().addAll(getChildren());
        }
        // 清空原来根节点下的子节点，这些节点已经被加入（深复制）到新的根节点的子节点中了。
        this.getChildren().clear();
        return newParent;
    }


}
