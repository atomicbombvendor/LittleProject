package com.company.Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupTest {

    public static void main(String[] args) {
        BazList bazs = mock();

        Map<String, BazList> result = bazs.stream().collect(Collectors.groupingBy(Baz::getTyp))
                .entrySet()
        .stream().map(k -> {
            BazList bazs2 = new BazList();
            bazs2.setGroupKey(k.getValue().get(0).getType());
            bazs2.setPartitionKey(k.getValue().get(0).getTyp());
            bazs2.addAll(k.getValue());
            return bazs2;
        }).collect(Collectors.toMap(BazList::getPartitionKey,
                        a -> a, (a, b) -> { a.addAll(b); return a;}
                        ));

        System.out.println(result);
    }

    private static BazList mock(){
        BazList bazs = new BazList();
        bazs.add(new Baz("A1", "B"));
        bazs.add(new Baz("B1", "B"));
        bazs.add(new Baz("C1", "B"));
        bazs.add(new Baz("A2", "C"));
        bazs.add(new Baz("A2", "C"));
        bazs.add(new Baz("A3", "D"));
        bazs.add(new Baz("A3", "D"));
        bazs.add(new Baz("A4", "E"));
        bazs.add(new Baz("A4", "E"));
        bazs.add(new Baz("A5", "F"));
        bazs.add(new Baz("A5", "F"));
        return bazs;
    }
}

class BazList extends ArrayList<Baz>{

    private String partitionKey;
    private String groupKey;

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }
}
