package com.company.arithmetic.dijkstra;


import java.util.*;

public class NonDirectedGraph {

    /** 内部类 **/
    private class Vertex{
        /**
         * 顶点标识，顶点名字
         */
        private String vertexLabel;

        private List<Edge> adjEdges;

        private int distinct;

        private Vertex preNode;

        public Vertex(String vertexLabel){
            this.vertexLabel = vertexLabel;
            adjEdges = new LinkedList<>();
            distinct = Integer.MAX_VALUE;
            preNode = null;
        }
    }

    private class Edge{
        private Vertex endVertex;
        public Edge(Vertex endVertex){
            this.endVertex = endVertex;
        }
    }

    /** 无向图 **/
    private Map<String, Vertex> nonDirectedGraph;

    /** 图的起始顶点 **/
    private Vertex startVertex;

    public NonDirectedGraph(String graphContent){
        // 使用链表Map
        nonDirectedGraph = new LinkedHashMap<>();
        buildGraph(graphContent);
    }

    private void buildGraph(String graphContent){
       // 使用换行符分割文件内容
        String[] lines = graphContent.split("\n");
        String startNodeLabel, endNodeLabel;

        Vertex startNode, endNode;

        for (int i=0; i < lines.length; i++){
            // 站点名称||站点1||站点2||站点间距离
            String[] nodesInfo = lines[i].split("\\|\\|");
            String roadLable = nodesInfo[0];
            startNodeLabel = nodesInfo[1];
            endNodeLabel = nodesInfo[2];
            double distinctR = Double.valueOf(nodesInfo[3]);

            endNode = nonDirectedGraph.get(endNodeLabel);
            if (endNode == null){
                endNode = new Vertex(endNodeLabel);
                nonDirectedGraph.put(endNodeLabel, endNode);
            }

            startNode = nonDirectedGraph.get(startNodeLabel);
            if (startNode == null){
                startNode = new Vertex(startNodeLabel);
                nonDirectedGraph.put(startNodeLabel, startNode);
            }

            Edge e = new Edge(endNode);
            endNode.adjEdges.add(e);
            startNode.adjEdges.add(e);
        }

        // 总是以文件中第一行第二列的那个标识顶点作为源点
        startVertex = nonDirectedGraph.get(lines[0].split(",")[1]);
    }

    public void unweightedShortestPath(){
        unweightedShortestPath(startVertex);
    }

    /**
     * 计算源点S到无向图中各个顶点的最短路径;
     * 需要一个队列来保存图中的顶点，初始时，源点入队列，然后以广度优先遍历的方式寻找最短的路径
     */
    private void unweightedShortestPath(Vertex start){
        Queue<Vertex> queue = new LinkedList<>();
        start.distinct = 0;

        // 将源点start的distinct设置为0
        queue.offer(start);

        while(!queue.isEmpty()){
            // 取出
            Vertex v = queue.poll();
            //遍历所有的边
            for (Edge e : v.adjEdges){
                // have not been visited
                if (e.endVertex.distinct == Integer.MAX_VALUE){
                    e.endVertex.distinct = v.distinct + 1;
                    queue.offer(e.endVertex);
                    e.endVertex.preNode = v;
                } // end if
            } // end for
        } // end while
    }

    public void showDistinct(){
        Collection<Vertex> vertexs = nonDirectedGraph.values();

        for (Vertex vertex : vertexs){
            System.out.println(vertex.vertexLabel + "<--");
            Vertex tempPreVertex = vertex.preNode;
            while(tempPreVertex != null){
                System.out.println(tempPreVertex.vertexLabel + "<--");
                tempPreVertex = tempPreVertex.preNode;
            }
            System.out.println("distinct=" + vertex.distinct);
        }
    }
}
