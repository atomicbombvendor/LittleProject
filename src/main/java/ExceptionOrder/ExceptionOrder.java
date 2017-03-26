package ExceptionOrder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.in;

/**
 * Created by ZZ on 2017/3/26.
 */
public class ExceptionOrder {
    public static String file = "cdctables.properties";

    public static String test1(){
        try{
            //顺序：没有异常-> 先执行try块内的内容，遇到return直接返回。
            //在try catch块外面的返回，属于公共的return。当try或者catch内没有返回时，调用最外面的返回。
            //所以正确的写法应该吧return放在最后。
            InputStream in = new FileInputStream("a.txt");
            System.out.println("In Try");
            //return "In Try";
        } catch (FileNotFoundException e) {
            System.out.println("Exception Start");
            e.printStackTrace();
            System.out.println("Exception End");
            return "In catch";
        }
        return null;
    }

    public static String test2(){
        try{
            InputStream in = new FileInputStream(file);
            System.out.println("In Try");
            //return "No Exception";
        } catch (FileNotFoundException e) {
            System.out.println("Exception Start");
            e.printStackTrace();
            System.out.println("Exception End");
            return "In catch";
        }
        return null;
    }

    /**
     * 会先返回return，再抛出异常
     * @return
     * return应该在语句最后和catch块里面
     */
    public static InputStream test3(){
        InputStream in;
        try{
            in = new FileInputStream("a.txt");
            //return in;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return in;
    }

    public static InputStream test4(){
        InputStream in;
        try{
            in = new FileInputStream(file);
            //return in;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return in;
    }


    public static void main(String[] args) {
        //System.out.println(test1());
        //System.out.println(test2());
        System.out.println(test3());
        //System.out.println(test4());
    }
}
