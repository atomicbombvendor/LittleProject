package com.company.Arithmetic;

/**
 * Created by eli9 on 12/12/2017.
 */
public class Rotation {

    public static boolean chkRotation(String a, int lena, String b, int lenb){
        if(a == null || b == null || lena != lenb){
            return false;
        }

        String b2 = b + b;
        //return b2.contains(a); ??contains??????
        return indexOf(b2, a) != -1;
    }

    //??b?a?????
    private static int indexOf(String a, String b){
        char[] source = a.toCharArray();
        char[] target = b.toCharArray();
        int sourceOffset = 0;
        int targetOffset = 0;
        int sourceCount = source.length;
        int targetCount = target.length;

        int fromIndex = 0;

        char first = target[targetOffset];//?????
        int max = sourceOffset + (sourceCount - targetCount);//???????source????????

        for (int i = sourceOffset + fromIndex; i <= max ; i++) {
            /* Look for first character. */
            if(source[i] != first){
                while(++i <= max && source[i] != first);
            }

             /* Found first character, now look at the rest of v2 */
            if(i <= max){
                int j = i + 1;
                int end = j + targetCount - 1;//?????
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
