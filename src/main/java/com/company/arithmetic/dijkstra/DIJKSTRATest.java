package com.company.arithmetic.dijkstra;

public class DIJKSTRATest {
    public static void main(String[] args) {

//        String file = "../subway_processed.txt";
        // 文件的绝对路径
        String file = "E:\\Project\\Java\\LittleProject\\src\\main\\java\\com\\company\\arithmetic\\dijkstra\\subway_processed.txt";
        String fileContent = FileUtils.read(file);

        WeightedNonDirectedGraph graph = new WeightedNonDirectedGraph(fileContent);
        graph.dijkstra();

        graph.showDistance();
    }
}
