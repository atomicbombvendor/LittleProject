package com.company.arithmetic.eightqueue;

import java.util.LinkedList;
import java.util.List;

public class EightQueue2 {

    List<List<String>> resultList = new LinkedList<>();

    public List<List<String>> solveNQueues(int n){

        //在那一列有Queue
        boolean[] cols = new boolean[n];
//        “\”对角线斜率为-1，对应方程为y = -x + b，其中b为截距。
//        对于线上任意一点，均有y + x = b，即row + i = b;
//        同理，定义数组main_diag，将b作为下标，当main_diag[row + i] = true时，
//        表示相应对角线上已经放置棋子。
        boolean[] main_diag = new boolean[2 * n];
//        “/”对角线斜率为1，对应方程为y = x + b，其中b为截距。
//        对于线上任意一点，均有y - x = b，即row - i = b;
//        定义一个布尔类型数组anti_diag，将b作为下标，当anti_diag[b] = true时，表示相应对角线上已经放置棋子。
//        但row - i有可能为负数，负数不能作为数组下标，row - i 的最小值为-n（当row = 0，i = n时），可以加上n作为数组下标，即将row -i + n 作为数组下标。
//        row - i + n 的最大值为 2n（当row = n，i = 0时），故anti_diag的容量设置为 2n 即可。
        boolean[] anti_diag = new boolean[2 * n];
        LinkedList<Integer> result = new LinkedList<>();
        dfs(result, 0, cols, main_diag, anti_diag, n);
        return resultList;
    }

    /**
     *
     * @param result list index作为row，list.get(index)作为columns
     * @param row
     * @param cols
     * @param main_diag
     * @param anti_diag
     * @param n
     */
    void dfs(LinkedList<Integer> result, int row, boolean[] cols, boolean[] main_diag, boolean[] anti_diag, int n){

        if (row == n){
            List<String> list = new LinkedList<>();
            for(int x=0; x<n; ++x){
                StringBuilder sb = new StringBuilder();
                for(int y=0; y<n; ++y){
                    sb.append(result.get(x) == y ? "Q" : ".");
                    list.add(sb.toString());
                }
            }

            resultList.add(list);
        }

        // n表示列数
        for (int i=0; i<n; i++){
            if (cols[i] || main_diag[row + i] || anti_diag[row - i + n]){
                continue;
            }

            result.add(i);
            cols[i] = true;
            main_diag[row + i] = true;
            anti_diag[row - i + n] = true;
            dfs(result, row + 1, cols, main_diag, anti_diag, n);
            result.removeLast();
            cols[i] = false;
            main_diag[row + i] = false;
            anti_diag[row - i + n] = false;
        }
    }
}
