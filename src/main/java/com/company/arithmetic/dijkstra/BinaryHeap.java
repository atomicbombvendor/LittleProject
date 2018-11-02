package com.company.arithmetic.dijkstra;

import java.util.NoSuchElementException;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    /** 默认容量 **/
    private static final int DEFAULT_CAPACITY = 10;

    /** 当前堆大小 **/
    private int currentSize;

    /** 数组 **/
    private AnyType[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity + 1];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(AnyType[] items) {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (AnyType item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    /**
     * 从任意排列的项目中建立堆，线性时间运行
     */
    protected void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * 堆内元素向下移动
     *
     * @param hole 下移的开始下标
     */
    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize
                    && array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }
            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    /**
     * 插入一个元素
     *
     * @param x 插入元素
     */
    public void insert(AnyType x) {
        if (isFull()) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    /**
     * 堆是否满
     *
     * @return 是否堆满
     */
    public boolean isFull() {
        return currentSize == array.length - 1;
    }

    /**
     * 堆是否空
     *
     * @return 是否堆空
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * 清空堆
     */
    @SuppressWarnings("unused")
    public void makeEmpay() {
        currentSize = 0;
        for (AnyType anyType : array) {
            anyType = null;
        }
    }

    /**
     * 找到堆中最小元素
     *
     * @return 最小元素
     */
    public AnyType findMin() {
        if (isEmpty()) {
            return null;
        }
        return array[1];
    }

    /**
     * 删除堆中最小元素
     *
     * @return 删除元素
     */
    public AnyType deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        AnyType minItem = findMin();
        array[1] = array[currentSize];
        array[currentSize--] = null;
        percolateDown(1);
        return minItem;
    }

    /**
     * 扩大数组容量
     *
     * @param newSize 新的容量
     */
    @SuppressWarnings("unchecked")
    private void enlargeArray(int newSize) {
        AnyType[] old = array;
        array = (AnyType[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }

    /**
     * 输出数组中的元素
     */
    public void printHeap() {
        for (AnyType anyType : array) {
            System.out.print(anyType + " ");
        }
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for (int i = 0; i < 20; i++) {
            heap.insert(i);
        }
        heap.deleteMin();
        heap.deleteMin();
        heap.deleteMin();
        heap.printHeap();
    }

}