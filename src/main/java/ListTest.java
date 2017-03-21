import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 3/1/2017.
 */
public class ListTest {
    private String Name;
    private int Value;

    public ListTest(String Name, int Value){
        this.Name = Name;
        this.Value = Value;
    }

    public ListTest(){
        this.Name = Name;
        this.Value = Value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public List<ListTest> getListTest(){
        List<ListTest> L = new ArrayList<ListTest>();
        for (int i = 0; i < 5; i++) {
            ListTest t = new ListTest("a",i);
            L.add(t);
        }
        return L;
    }

    public static void main(String[] args) {
        ListTest t1 = new ListTest();
        List<ListTest> L1 = t1.getListTest();
        System.out.println(L1.size());
    }
}
