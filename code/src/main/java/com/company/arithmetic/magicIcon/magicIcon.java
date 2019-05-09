package com.company.arithmetic.magicIcon;

public class magicIcon {

    public static void getSolution(int n){

        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        while (flag) {

            //逆推
            int coinCount = n;
            while (coinCount >=1){
                if (coinCount%2==0){
                    coinCount = (coinCount - 2) / 2;
                    System.out.println("Coin " + coinCount);
                    sb.append(1);
                }else{
                    coinCount = (coinCount - 1) / 2;
                    System.out.println("Coin " + coinCount);
                    sb.append(2);
                }
            }

            System.out.println(sb.reverse());
            flag = false;
        }
    }

    public static void main(String[] args) {
        getSolution(100);
    }
}
