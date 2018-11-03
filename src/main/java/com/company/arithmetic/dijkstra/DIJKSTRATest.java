package com.company.arithmetic.dijkstra;


public class DIJKSTRATest {
    public static void main(String[] args) {

//        String file = "../subway_processed.txt";
        // 文件的绝对路径
        String file = "D:\\Projects\\LittleProject\\src\\main\\java\\com\\company\\arithmetic\\dijkstra" +
                "\\subway_processed_test.txt";
        String fileContent = FileUtils.read(file);

        WeightedNonDirectedGraph graph = new WeightedNonDirectedGraph();

        graph.buildGraph(fileContent);

        // 给定站名，返回经过改站点的所有线路名称合集
        //graph.printRoad("园博园北");

        // 给定线路名和终点方向，返回该线路中所有的站点和顺序列表
        //graph.printWholeWayStations("6号线","轻工大学");

        // 找到最短路径
        graph.dijkstra();
        graph.printRoute("园博园");

        //
        // https://www.cnblogs.com/hapjin/p/5654756.html 图
        // https://blog.csdn.net/zhang_zp2014/article/details/47863575 堆
    }
}
