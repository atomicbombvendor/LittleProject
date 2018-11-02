package com.company.arithmetic.dijkstra;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeightedNonDirectedGraph{

    /** 存储图(各个顶点) **/
    private Map<String, Vertex> weightedGraph;
    /**  单源最短路径的起始顶点 **/
    private Vertex startVertex;

    private class Vertex implements Comparable<Vertex> {
        /** 顶点标识 */
        private String vertexLabel;

        /** 线路标识 **/
        private String roadLabel;

        /** 顶点的所有邻接边(点) **/
        private List<Edge> adjEdges;

        /**  顶点到源点的最短距离 **/

        private double dist;

        /** 配合dist，记录上一个节点 **/
        private Vertex preNode;

        public Vertex(String roadLabel, String vertexLabel){
            this.roadLabel = roadLabel;
            this.vertexLabel = vertexLabel;
            adjEdges = new LinkedList<>();
            dist = Double.MAX_VALUE;
            preNode = null;
        }

        @Override
        public int compareTo(Vertex v) {

            if(this.dist >  v.dist) {
                return 1;
            } else if(this.dist < v.dist) {
                return -1;
            }
            return 0;
        }
    }

    private class Edge{
        /** 权重 距离 **/
        private double weight;
        private Vertex endVertex;
        public Edge(double weight, Vertex endVertex) {
            this.weight = weight;
            this.endVertex = endVertex;
        }
    }

    /**
     * 图的信息保存在文件中,从文件中读取成字符串graphContent
     */
    public WeightedNonDirectedGraph(String graphContent) {
        weightedGraph = new LinkedHashMap<>();
        buildGraph(graphContent);
    }

    /**
     * 构造无向加权图
     * @param graphContent content read from file
     */
    private void buildGraph(String graphContent){
        String[] lines = graphContent.split("\n");

        String startNodeLabel, endNodeLabel, roadLable;
        Vertex startNode, endNode;
        double weight;
        for(int i = 0; i < lines.length; i++){
            String[] nodesInfo = lines[i].split("\\|\\|");
            roadLable = nodesInfo[0];
            startNodeLabel = nodesInfo[1];
            endNodeLabel = nodesInfo[2];
            weight = Double.valueOf(nodesInfo[3]);

            endNode = weightedGraph.get(endNodeLabel);
            if(endNode == null){
                endNode = new Vertex(roadLable, endNodeLabel);
                weightedGraph.put(endNodeLabel, endNode);
            }

            startNode = weightedGraph.get(startNodeLabel);
            if(startNode == null){
                startNode = new Vertex(roadLable, startNodeLabel);
                weightedGraph.put(startNodeLabel, startNode);
            }
            Edge e = new Edge(weight, endNode);

            //对于无向图而言,起点和终点都要添加边
            endNode.adjEdges.add(e);
            startNode.adjEdges.add(e);
        }
        //总是以文件中第一行第二列的那个标识顶点作为源点
        startVertex = weightedGraph.get(lines[0].split("\\|\\|")[1]);
    }

    /**
     * 最短优先路径
     */
    public void dijkstra(){
        BinaryHeap<Vertex> heap = new BinaryHeap<>();
        //inital heap
        init(heap);

        while(!heap.isEmpty())
        {
            Vertex v = heap.deleteMin();
            //获取v的所有邻接点
            List<Edge> adjEdges = v.adjEdges;
            for (Edge e : adjEdges) {
                Vertex adjNode = e.endVertex;
                //update
                if(adjNode.dist > e.weight + v.dist){
                    adjNode.dist = e.weight + v.dist;
                    adjNode.preNode = v;
                }
            }//end for

            //更新之后破坏了堆序性质,需要进行堆调整,这里直接重新构造堆(相当于decreaseKey)
            heap.buildHeap();
        }

    }

    private void init(BinaryHeap<Vertex> heap){
        //源点到其自身的距离为0
        startVertex.dist = 0.0;
        for (Vertex v : weightedGraph.values()) {
            heap.insert(v);
        }
    }

    public void showDistance(){
        for (Vertex v : weightedGraph.values()) {
            printPath(v);
            System.out.println();
            System.out.println(v.roadLabel + " " + v.vertexLabel + "站 -> " + startVertex.roadLabel +
                    " " + startVertex.vertexLabel + " 距离: " + v.dist);
        }
    }

    /**
     * 打印源点到 end 顶点的 最短路径
     */
    private void printPath(Vertex end)
    {
        if(end.preNode != null) {
            printPath(end.preNode);
        }
//        System.out.print(end.vertexLabel + "--> ");
    }
}
