package com.company.arithmetic.firstlettersearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int A = 65;
    private static List<String> testList;

    private static List<Node> result;
    private static List<String> resultStrs;

    private static String searchText;

    public static void main(String[] args) {

//  String text = "BHD,CZN,EGB,EHW,LMO,MJO,NZB,RPN,TOX,ZWV";
//  testList = Arrays.asList(text.split(","));

        final int listCount = (int) Math.pow(10, 7); // pow(n, m) = n ^ m 指的是所有的list长度
        final int charCount = 3;
        searchText = getRandomSearchText(2);

        log("生成的字符总数: " + listCount
                + "  生成的字符长度: " + charCount
                + "  要查询的字符: " + searchText
        );

        if (testList == null) {
            log("generateList time: " + testTime(() -> testList = generateList(listCount, charCount)));
        }

        //这是一个Collections的方法
        log("sortList time: " + testTime(() -> Collections.sort(testList)));

        final LetterTree lt = new LetterTree();
        log("buildTree time: " + testTime(() -> lt.buildTree(testList)));

//        logTree(lt.getRoot(), 0);

        long time = testTime(() -> result = lt.treeSearch(searchText));

        List<Integer> indexList = new ArrayList<>();
        int currentIndex = 0;
        for (Node item : result) {
            for (int i = item.getStartPosition(); i >= currentIndex && i < item.getEndPosition(); i++) {
                indexList.add(i);
            }
            currentIndex = Math.max(currentIndex, item.getEndPosition());
        }

        log("   treeSearch   count: " + indexList.size() + "  time: " + time);


        time = testTime(() -> resultStrs = LetterTree.forListSearch(searchText, testList));
        log("forListSearch   count: " + resultStrs.size() + "  time: " + time);


        log("All -----------------------");
//        for (String item : testList) {
//            log(item);
//        }

        log("searchText -----------------------" + searchText);

        // 在搜索结果中, 随机抽取5个字符, 看是否一样.
        List<Integer> randomIndexList = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < 5 && i < resultStrs.size(); i++) {
            randomIndexList.add(rd.nextInt(resultStrs.size()));
        }

        log("treeSearch -----------------------");

        for (int i = 0; i < randomIndexList.size(); i++) {
            Integer index = randomIndexList.get(i);
            index = indexList.get(index);
            log((testList.get(index)));
        }

        log("forListSearch -----------------------");
        for (int i = 0; i < randomIndexList.size(); i++) {
            Integer index = randomIndexList.get(i);
            String item = resultStrs.get(index);
            log(item);
        }
    }

    public static void log(Object obj) {
        if (obj == null) obj = "null";
        System.out.println(obj.toString());
    }

    private static void logTree(Node node, int indent) {
        if (node == null) return;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append(' ');
        }
        log(sb.toString() + node.getLetter() + "___________________" + node.getStartPosition() + "-" + node.getEndPosition());
        for (Node item : node.getList()) {
            if (item == null) continue;
            logTree(item, indent + 4);
        }
    }

    /**
     * 计算方法消耗的时间
     * @param runnable 参数
     * @return
     */
    public static long testTime(Runnable runnable) {
        long time = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - time;
    }

    /**
     * 包含的相同的字符，这很正常
     * @param listCount 所有的姓名缩写总数
     * @param charCount 字符长度
     * @return 所有的姓名缩写
     */
    public static List<String> generateList(int listCount, int charCount) {
        List<String> list = new ArrayList<>(listCount);
        Random rd = new Random();
        for (int i = 0; i < listCount; i++) {
            StringBuilder sb = new StringBuilder(charCount);
            for (int j = 0; j < charCount; j++) {
                sb.append((char) (rd.nextInt(26) + A));
            }
            list.add(sb.toString());
        }
        return list;
    }

    /**
     * 生成随机长度的字符
     * @param charCount 字符的长度
     * @return
     */
    public static String getRandomSearchText(int charCount) {
        StringBuilder sb = new StringBuilder(charCount);
        Random rd = new Random();
        for (int j = 0; j < charCount; j++) {
            //生成0=< x <charCount Z是90 A是65 所以生成的在 A-Z之间的字符
            sb.append((char) (rd.nextInt(26) + A));
        }
        return sb.toString();
    }

}
