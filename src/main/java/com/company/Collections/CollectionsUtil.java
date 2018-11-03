package com.company.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by atomic on 5/16/2017.
 */
public class CollectionsUtil {
    /**
     * 比较两个list，返回不同的值；
     * 原理是用map来比较，数量级在常量级
     */
    public static List<String> getDiff(List<String> source, List<String> target){
        if(source.size()==0 || target.size()==0){
            return null;
        }
        if(source==null || target==null){
            return null;
        }
        int frequency = 1;
        List<String> max;
        List<String> min;
        List<String> diff = new ArrayList<>();
        if(source.size()>target.size()){
            max = source;
            min = target;
        }else {
            max = target;
            min = source;
        }

        Map<String, Integer> map = new HashMap<>();
        max.stream().forEach(s-> map.put(s,frequency));

        min.stream().forEach(s -> {
            if(map.containsKey(s)){
                map.put(s, map.get(s)+1);//rewrite value
            }else{
                map.put(s,frequency);
            }
        });

        //get value=1
        map.forEach((k,v) -> {
            if(v == 1){
                diff.add(k);
            }
        });

        return diff;
    }

    /**
     * 比较两个map,返回不同的value
     */
    public static List<String> checkMapDiff(Map<String, List<String>> m1, Map<String, List<String>> m2){
        List<String> diff = new ArrayList<>();
        //m2-m1
        for (String k:m1.keySet()) {
            if(m2.containsKey(k)){
                diff.addAll(getDiff(m1.get(k), m2.get(k)));
            }else{//m1 have, but m2 don't
                diff.addAll(m1.get(k));
            }
        }

        //m1-m2
        for (String k:m2.keySet()) {
            if(!m1.containsKey(k)){//m2 have, but m1 don't
                diff.addAll(m2.get(k));
            }
        }

        return diff;
    }
}
