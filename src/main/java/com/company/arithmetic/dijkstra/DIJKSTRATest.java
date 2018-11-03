package com.company.arithmetic.dijkstra;


import java.math.BigDecimal;

public class DIJKSTRATest {
    public static void main(String[] args) {

//        String file = "../subway_processed.txt";
        // 文件的绝对路径
        String file = "D:\\Projects\\LittleProject\\src\\main\\java\\com\\company\\arithmetic\\dijkstra" +
                "\\subway_processed.txt";
        String fileContent = FileUtils.read(file);

        WeightedNonDirectedGraph graph = new WeightedNonDirectedGraph();

        graph.buildGraph(fileContent);

        // 给定站名，返回经过改站点的所有线路名称合集
        System.out.println("给定站名，返回经过改站点的所有线路名称合集");
        graph.printRoad("园博园北");
        System.out.println();

        // 给定线路名和终点方向，返回该线路中所有的站点和顺序列表
        System.out.println("给定线路名和终点方向，返回该线路中所有的站点和顺序列表");
        graph.printWholeWayStations("6号线","金银湖公园");
        System.out.println();
        System.out.println();

        // 找到最短路径
        System.out.println("找到最短路径");
        graph.findShortest("轻工大学", "园博园");
        graph.printShortest();
        System.out.println("需要乘坐" + graph.getShortest().size() + "站");
        System.out.println();

        // 计算单程票价
        BigDecimal singleTicketPrice = Tickets.calcNormalTicketPrice(graph.getShortest().size());
        System.out.println("单程票价" + singleTicketPrice.floatValue());
        System.out.println();

        // 计算武汉通票价
        BigDecimal wuhanTong = Tickets.calcWuHanTong(singleTicketPrice);
        System.out.println("武汉通票价" + wuhanTong.floatValue());
        System.out.println();

        // 计算日票票价
        BigDecimal dayTickets = Tickets.calcDayTickets(singleTicketPrice);
        System.out.println("日票" + dayTickets.floatValue());
        System.out.println();


        // https://www.cnblogs.com/hapjin/p/5654756.html 图
        // https://blog.csdn.net/zhang_zp2014/article/details/47863575 堆
    }
}
