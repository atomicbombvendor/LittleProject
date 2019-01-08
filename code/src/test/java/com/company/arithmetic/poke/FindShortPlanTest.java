package com.company.arithmetic.poke;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindShortPlanTest {

    @Test
    public void findShortPlan() {

        // String pokes = "A23456669JTTJQKKKK";
        String pokes = "A234";
        TreeNode<List<Integer>> rs = FindShortPlan.findShortPlan(pokes);
        TreeUtils.printTree(rs, " ");
    }
}