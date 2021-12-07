package api;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Directed_WeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    private Directed_WeightedGraph graph;
    private static final int WHITE = 0;
    private static final int  GRAY = 1;
    private static final int BLACK = 2;
    private double [][] matrix;

    public Directed_WeightedGraphAlgorithms(Directed_WeightedGraph g){
        this.graph= new Directed_WeightedGraph(g.getMapOfNode(), g.getMapOfEdge(), g.getMapOfSrc(), graph.getMapOfDst());
    }

    // TODO: 06/12/2021 copy deep or not? check this
    @Override
    public void init(api.DirectedWeightedGraph g) {
        this.graph= (Directed_WeightedGraph) g;
        creatMatrix(this.graph);
    }

    @Override
    public api.DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public api.DirectedWeightedGraph copy() {
        Directed_WeightedGraph g= new Directed_WeightedGraph(this.graph);
        return (DirectedWeightedGraph) g;
    }

    @Override
    public boolean isConnected() {
        for(Integer s : this.graph.getMapOfNode().keySet()){
            boolean path= BFS(this.graph, (Node_Data) this.graph.getMapOfNode().get(s));
            this.cleanTag(this.graph);
            if(!path) return false;
        }
        return true;
    }

    private void creatMatrix(Directed_WeightedGraph  g){
        this.matrix= new double[this.graph.getMapOfNode().size()][this.graph.getMapOfNode().size()];
        for(Integer s : this.graph.getMapOfSrc().keySet()){
            for(Integer d : this.graph.getMapOfSrc().keySet()){
                matrix[s][d]= this.graph.getMapOfSrc().get(s).get(d).getWeight();
            }
        }
    }
    @Override
    public double shortestPathDist(int src, int dest) {
//        while (this.graph.edgeIter(src).hasNext()){
//            if(this.graph.getMapOfSrc().get(src).get(dest).getWeight() < path)
//                path= this.graph.getMapOfSrc().get(src).get(dest).getWeight();
//        }
        // TODO: 06/12/2021 this like the task on c, check if this good. 
//        for(int k=0; k<this.matrix.length; k++) {
//            for (int i = 0; i < this.matrix.length; i++) {
//                for (int j = 0; j < this.matrix.length; j++) {
//                    if(i==j) continue;
//                    matrix[i][j]= Math.min(matrix[i][j], (matrix[i][k]+matrix[k][j]));
//                }
//            }
//        }
        return matrix[src][dest];
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> path= new LinkedList<>();
        path.add(this.graph.getNode(src));

        return path;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            File f= new File(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean BFS (Directed_WeightedGraph graph, Node_Data nodeSrc){
        Queue<Node_Data> queue = new LinkedList<>();
        queue.add(nodeSrc);
        graph.getMapOfNode().get(nodeSrc.getKey()).setTag(GRAY);
        //for
        while (!(queue.isEmpty())){
            Node_Data TmpNode = queue.poll();
            for (int i = 0; i < graph.getMapOfSrc().get(TmpNode.getKey()).keySet().size(); i++) {
                graph.getMapOfSrc().get(nodeSrc.getKey()).get(i).setTag(GRAY);
                queue.add((Node_Data) graph.getMapOfSrc().get(nodeSrc.getKey()).get(i));
            }
            TmpNode.setTag(BLACK);
        }

        for (int i : graph.getMapOfNode().keySet()) {
            if (graph.getMapOfNode().get(i).getTag() == WHITE) return false;
        }
        return true;
    }

//    public double BFS_Revers(Directed_WeightedGraph graph, Node_Data nodeSrc){
//        return 0;
//    }

    public void cleanTag(Directed_WeightedGraph g){
        for(Integer s : this.graph.getMapOfNode().keySet()){
            this.graph.getMapOfNode().get(s).setTag(WHITE);
        }
    }


}
