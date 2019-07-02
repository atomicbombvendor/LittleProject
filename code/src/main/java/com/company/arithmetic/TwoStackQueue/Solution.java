package com.company.arithmetic.TwoStackQueue;

import java.util.Stack;

public class Solution {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node){

        stack1.push(node);
    }

    public Integer pop(){

        if (stack1.empty() && stack2.empty()){
            throw new RuntimeException("Queue is empty");
        }

        if (stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
