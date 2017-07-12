package com.company.ExceptionOrder;

/**
 * Created by eli9 on 6/29/2017.
 */
public class ExceptionTest {
    static String a[]={"123","abc",null};
    public static void main (String args[]) {
        for (int i = 0; i < 3; i++) {

            try {
                int x = Integer.parseInt(a[i]);
                System.out.print(  "Result: " + x + " ");
            }
            catch(NullPointerException e) {
                System.out.print("error null：" + " ");
            }
            catch (NumberFormatException e){
                System.out.print("error ：abc"+" ");
            }
            finally{
                System.out.print ("In "+ i +"th loop" + " ");
            }
        }
    }
}
