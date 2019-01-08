package com.company.arithmetic.dijkstra;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class WeightedNonDirectedGraph{

    /** 存储图(各个顶点) **/
    private Map<String, Vertex> weightedGraph;

    /**  单源最短路径的起始顶点 **/
    private Vertex startVertex;
    private Vertex endVertex;

    private List<Vertex> shortest;

    private class Vertex implements Comparable<Vertex> {
        /** 顶点标识 */
        private String vertexLabel;

        /** 线路标识 **/
        private String roadLabel;

        /** 顶点的所有邻接边(点) **/
        private List<Edge> adjEdges;

        /**  顶点到源点的最短距离 **/
        private double dist;

        private boolean visited;

        /** 配合dist，记录上一个节点到当前点的距离 **/
        private Vertex preNode;

        public Vertex(String roadLabel, String vertexLabel){
            this.roadLabel = roadLabel;
            this.vertexLabel = vertexLabel;
            adjEdges = new LinkedList<>();
            dist = Double.MAX_VALUE;
            preNode = null;
            visited = false;
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

        public String getVertexLabel() {
            return vertexLabel;
        }

        public void setMultipleRoadLabel(String anotherRoadLabel){
            if (this.roadLabel != null && !roadLabel.contains(anotherRoadLabel)){
                this.roadLabel = this.roadLabel + "||" + anotherRoadLabel;
            }
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "vertexLabel='" + vertexLabel + '\'' +
                    ", roadLabel='" + roadLabel + '\'' +
                    ", dist='" + BigDecimal.valueOf(dist)
                    .setScale(2, RoundingMode.HALF_UP).floatValue() + '\'' +
                    '}';
        }

        public String getRoadInfo(){
            return roadLabel + " " + vertexLabel;
        }

    }

    private class Edge{
        /** 权重 距离 **/
        private double weight;
        private Vertex edgeVertex;
        public Edge(double weight, Vertex endVertex) {
            this.weight = weight;
            this.edgeVertex = endVertex;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", edgeVertex=" + edgeVertex +
                    '}';
        }
    }

    /**
     * 图的信息保存在文件中,从文件中读取成字符串graphContent
     */
    public WeightedNonDirectedGraph() {
        weightedGraph = new LinkedHashMap<>();
        shortest = new LinkedList<>();
    }

    /**
     * 构造无向加权图; 图保存在 weightedGraph
     * @param graphContent content read from file
     */
    public void buildGraph(String graphContent){
        String[] lines = graphContent.split("\n");

        String startNodeLabel, endNodeLabel, roadLabel;
        Vertex startNode, endNode;
        double weight;
        for(int i = 0; i < lines.length; i++){
            String[] nodesInfo = lines[i].split("\\|\\|");
            roadLabel = nodesInfo[0];
            startNodeLabel = nodesInfo[1];
            endNodeLabel = nodesInfo[2];
            weight = Double.valueOf(nodesInfo[3]);

            endNode = weightedGraph.get(endNodeLabel);
            if(endNode == null){
                endNode = new Vertex(roadLabel, endNodeLabel);
                weightedGraph.put(endNodeLabel, endNode);
            }

            startNode = weightedGraph.get(startNodeLabel);
            if(startNode == null){
                startNode = new Vertex(roadLabel, startNodeLabel);
                weightedGraph.put(startNodeLabel, startNode);
            }else{
                startNode.setMultipleRoadLabel(roadLabel);
            }

            // 地铁是无向图
            endNode.adjEdges.add(new Edge(weight, startNode));
            startNode.adjEdges.add(new Edge(weight, endNode));
        }

        System.out.println("所有的地铁站点已经导入。");
    }

    public void findShortest(String startStationName, String endStationName){
        if (startStationName.isEmpty() ||
                endStationName.isEmpty() ||
                Objects.isNull(weightedGraph.get(startStationName)) ||
                Objects.isNull(weightedGraph.get(endStationName))){
            System.out.println("检查输入站点名是否存在或者为空");
        }
        startVertex = weightedGraph.get(startStationName);
        dijkstra();
        endVertex = weightedGraph.get(endStationName);
    }

    public void printShortest(){
        System.out.println(startVertex.getVertexLabel() + " 到 " + endVertex.vertexLabel);
        printRoute(endVertex);
    }

    public List<Vertex> getShortest() {
        return shortest;
    }

    /**
     * 最短优先路径
     */
    private void dijkstra(){
        BinaryHeap<Vertex> heap = new BinaryHeap<>();
        //inital heap
        init(heap);

        while(!heap.isEmpty())
        {
            Vertex v = heap.deleteMin();
            //获取v的所有邻接点
            List<Edge> adjEdges = v.adjEdges;
            for (Edge e : adjEdges) {
                Vertex adjNode = e.edgeVertex;
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

    private void printRoute(Vertex end){

        Stack<Vertex> route = new Stack<>();
        Vertex index = end;
        System.out.print("最短路线: ");
        while(index != null) {
            route.push(index);
            index = index.preNode;
        }

        while(!route.empty()){
            Vertex current = route.pop();
            Vertex next = route.empty()?null:route.peek();
            shortest.add(current);
            System.out.print(formatRoute(current, next) + " >> ");
        }
        System.out.println("结束");
        System.out.println("距离是" + BigDecimal.valueOf(end.dist).setScale(2, RoundingMode.HALF_UP).floatValue() + "KM");
    }

    private String formatRoute(Vertex current, Vertex next){
        if (next == null){
            return current.getRoadInfo();
        }

        if (current.roadLabel.split("\\|\\|").length > 1){
            return current.vertexLabel + "换乘" + next.roadLabel;
        }else{
            return current.getRoadInfo();
        }
    }

    /**
     * 打印出所有经过某站点的地铁线路名称
     * @param subwayStationName 地铁站名称
     */
    public void printRoad(String subwayStationName){

        Vertex station = weightedGraph.get(subwayStationName);
        if (station == null){
            System.out.println("抱歉，不存在该站点。");
            return;
        }

        List<Edge> edges = weightedGraph.get(subwayStationName).adjEdges;
        if (edges == null || edges.size() == 0){
            System.out.println("抱歉，当前站没有线路。");
            return;
        }


        System.out.println(String.format("所有经过 %s 有 %d 条线路。",
                subwayStationName,
                station.roadLabel.split("\\|\\|").length));
        System.out.println(Arrays.asList(station.roadLabel.split("\\|\\|")));

    }

    /**
     * 没有判断是不是终点方向，默认输入为终点方向。
     * @param roadLabel 线路名
     * @param subwayStationName 终点方向
     */
    public void printWholeWayStations(String roadLabel, String subwayStationName) {

        Stack<Vertex> stack = new Stack<>();
        Stack<String> stationNames = new Stack<>();

        Vertex startStation = weightedGraph.get(subwayStationName);
        if (startStation == null) {
            System.out.println("抱歉，不存在该站点。");
            return;
        }

        if (!startStation.roadLabel.contains(roadLabel)) {
            System.out.println(String.format("不存在%s %s方向", roadLabel, subwayStationName));
        }

        if (!checkStationIsEndPoint(roadLabel, startStation)){
            System.out.println(String.format("当前%s不是终点站", subwayStationName));
            return;
        }


        stack.push(startStation);
        stationNames.push(startStation.getVertexLabel());

        Vertex preStation = getPreStation(roadLabel, startStation);
        stack.push(preStation);
        stationNames.push(preStation.getVertexLabel());

        while (!checkStationIsEndPoint(roadLabel, preStation)) {
            List<Edge> edges = preStation.adjEdges;
            for (Edge edge : edges) {
                Vertex preEdge = edge.edgeVertex;
                if (preEdge.roadLabel.contains(roadLabel) && !stationNames.contains(preEdge.vertexLabel)) {

                    stack.push(preEdge);
                    stationNames.push(preEdge.vertexLabel);

                    preStation = preEdge;
                }
            }
        }

        System.out.println(String.format("%s %s方向:", roadLabel, subwayStationName));
        while (!stationNames.empty()){
            String info = stationNames.pop();
            System.out.print(info + " ");
        }
    }

    /**
     * 查找是不是当前线路的终点站
     * 如果是终点站，返回true.
     */
    private boolean checkStationIsEndPoint(String roadName, Vertex vertex){
        List<Edge> edges = vertex.adjEdges;
        int lastStationFlag = 0;
        for (Edge edge : edges) {
            // 找到终点站的上一站
            if (edge.edgeVertex.roadLabel.contains(roadName)) {
                lastStationFlag++;
            }
        }

        if (lastStationFlag > 1) {
            return false;
        }
        return true;
    }

    /**
     * 找到某条线路终点站的上一站
     */
    private Vertex getPreStation(String roadLabel, Vertex vertex){

        Vertex preStation = null;

        List<Edge> edges = vertex.adjEdges;
        for (Edge edge : edges) {
            // 找到此终点站的上一站
            if (edge.edgeVertex.roadLabel.contains(roadLabel)) {
                preStation = edge.edgeVertex;
            }
        }
        return preStation;
    }
}
