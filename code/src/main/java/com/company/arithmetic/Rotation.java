package com.company.arithmetic;

/**
 * Created by eli9 on 12/12/2017./
 如果对于一个字符串A，将A的前面任意一部分挪到后边去形成的字符串称为A的旋转词。
 比如A="12345",A的旋转词有"12345","23451","34512","45123"和"51234"。
 对于两个字符串A和B，请判断A和B是否互为旋转词。
 给定两个字符串A和B及他们的长度lena，lenb，请返回一个bool值，代表他们是否互为旋转词。
 */
public class Rotation {

    public static boolean chkRotation(String a, int lena, String b, int lenb){
        if(a == null || b == null || lena != lenb){
            return false;
        }

        String b2 = b + b;
        //return b2.contains(a); 使用contains方法可以实现
        return indexOf(b2, a) != -1;
    }

    //查找b在a中存在吗？
    private static int indexOf(String a, String b){
        char[] source = a.toCharArray();
        char[] target = b.toCharArray();
        int sourceOffset = 0;
        int targetOffset = 0;
        int sourceCount = source.length;
        int targetCount = target.length;

        int fromIndex = 0;

        char first = target[targetOffset];//第一个字符
        int max = sourceOffset + (sourceCount - targetCount);//第一个字符能在source中出现的最大位置

        for (int i = sourceOffset + fromIndex; i <= max ; i++) {
            /* Look for first character. */
            if(source[i] != first){
                while(++i <= max && source[i] != first);
            }

             /* Found first character, now look at the rest of v2 */
            if(i <= max){
                int j = i + 1;
                int end = j + targetCount - 1;//结束的位置
                for(int k = targetOffset + 1; j < end && source[j] == target[k]; j++, k++);
                if(j == end){
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        String a = "abecd";
        String b = "cdabe";
        System.out.println(chkRotation(a,5,  b, 5));

    }
}
