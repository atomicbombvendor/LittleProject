package com.company.arithmetic.eightqueue;

/**
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * 下面使用递归方法解决
 * */
public class EightQueue {

    private static final short N = 8;
    private static int count = 0;

    public static void main(String[] args) {

        Long start = System.currentTimeMillis();
        short chess[][] = new short[8][8];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){

                chess[i][j] = 0;
            }
        }

        putQueenAtRow(chess, 0);

        Long end = System.currentTimeMillis();
        System.out.println("Takes " + (end-start)/1000 + "s");
    }

    private static void putQueenAtRow(short[][] chess, int row){

        if (row == N){
            count++;
            System.out.println("solution " + count);
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(chess[i][j]+" ");
                }
                System.out.println();
            }
            return;
        }

        short[][] chessTemp = chess.clone();

        /**
         * 向这一行的每一个位置尝试排放皇后
         * 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
         */
        for (int i=0; i<N; i++){
            //摆放这一行的皇后，之前要清掉所有这一行摆放的记录，防止污染棋盘
            for (int j=0; j<N; j++){
                chessTemp[row][j]=0;
            }
            chessTemp[row][i]=1;

            if (isSafety(chessTemp, row, i)){
                putQueenAtRow(chessTemp, row+1);
            }
        }
    }

    private static boolean isSafety(short[][] chess, int row, int col){

        int step = 1;

        while (row-step>=0){

            if (chess[row-step][col] == 1){
                return false;
            }
            if (col-step>=0 && chess[row-step][col-step]==1){
                return false;
            }
            if (col+step<N && chess[row-step][col+step]==1){
                return false;
            }

            step++;
        }
        return true;
    }
}
