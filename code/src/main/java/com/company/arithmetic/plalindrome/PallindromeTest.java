package com.company.arithmetic.plalindrome;

public class PallindromeTest {

    public static void main(String[] args) {

        char[] con = "character".toCharArray();
        System.out.println("Max Length: " + getMaxLength(con, 0, con.length-1));

        getPallindrome(con);
    }

    private static int getMaxLength(char[] con, int i, int j){

        if (i==j){
            return 1;
        }

        if (i>j){
            return 0;
        }

        if (con[i] == con[j]){
            return getMaxLength(con, i+1, j-1)+2;
        }else{
            return Math.max(getMaxLength(con, i+1, j), getMaxLength(con, i, j-1));
        }
    }

    private static void getPallindrome(final char[] con){

        int l = con.length;
        int[][] db = new int[l][l];

        // 初始化回文长度表格
        for (int i=0; i<con.length; i++){

            for (int j=i; j<con.length && j>=0; j++){

                db[i][j] = getMaxLength(con, i, j);
            }
        }

        for (int i=0; i<con.length; i++){

            for (int j=0; j<con.length; j++){

                System.out.print(db[i][j] + " ");
            }
            System.out.println();
        }

        // 保存回文
        char[] res = new char[db[0][con.length-1]];
        int m=0;
        int n=res.length-1;

        int i=0;
        int j=l-1;
        int index=res.length-1;

        while (index>=0){
            // 相等，表示此区间db[i][j] db[i+1][j]是不一样的
            if (i<l && db[i][j] == db[i+1][j]){
                i++;
            }else if (j>0 && db[i][j]==db[i][j-1]){
                j--;
            }else{
                res[m] = con[i];
                res[n] = con[j];
                i++;
                j--;
                m++;
                n--;
                index = index-2;
            }
        }

        System.out.println(String.valueOf(res));
    }
}
