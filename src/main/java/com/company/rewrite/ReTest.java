package com.company.rewrite;

/**
 * Created by eli9 on 3/31/2017.
 */
public class ReTest {
    private int num;
    private String data;

    public ReTest(int num, String data){
        this.num = num;
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        ReTest re = (ReTest) obj;
        return num == re.num && (data == re.data || (data != null && data.equals(re.data)));
    }

    //hashCode()方法用来给对象获取唯一的一个整数。这个整数被存储在HashTable类似的结构中的位置。
    // 默认的，Object类的hashCode()方法返回这个对象存储的内存地址的编号。
    @Override
    public int hashCode() {
        return this.hashCode();
    }

    public static void main(String[] args) {
        ReTest test = new ReTest(1,"data");
        ReTest test2 = new ReTest(1,"data");
        ReTest test3 = new ReTest(2,"data");
        System.out.println(test.equals(test2));
        System.out.println(test.equals(test3));
    }
}
