package api;

import java.io.File;
import java.util.*;


public class Directed_WeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    private Directed_WeightedGraph graph;
    private static final int WHITE = 0;
    private static final int  GRAY = 1;
    private static final int BLACK = 2;
    private double [][] matrix;

    public Directed_WeightedGraphAlgorithms(Directed_WeightedGraph g){
        this.graph= new Directed_WeightedGraph(g.getMapOfNode(), g.getMapOfEdge(), g.getMapOfSrc(), g.getMapOfDst());
    }

    // TODO: 06/12/2021 copy deep or not? check this
    @Override
    public void init(api.DirectedWeightedGraph g) {
        this.graph= (Directed_WeightedGraph) g;
//        creatMatrix(this.graph);
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
        double[] arr= new double[(this.graph.nodeSize()-1)*3];
        int i= 0;
        int start= -1;
        for(Integer s: this.graph.getMapOfNode().keySet()) {
            if(s == src) {
                start= i;
                i=i+3;
                continue;
            }
            arr[i] = s;
            if(this.graph.getMapOfSrc().get(src).containsKey(s))
                arr[i + 1] = this.graph.getMapOfSrc().get(src).get(s).getWeight();
            else
                arr[i+1] = Integer.MAX_VALUE;
            arr[i + 2] = src + 0.0;
            i = i + 3;
        }
//        int start= src;
        while (arr[start] != dest){
            int min= minInArr(arr)-1;
            arr[min+2]+= 0.1;
            start= min;
            i=0;
            for(Integer s: this.graph.getMapOfNode().keySet()){
                if(s==start){
                    i= i+3;
                    continue;
                }
                if(this.graph.getMapOfSrc().get(start).containsKey(s)){
                    int tag= (int) arr[i+2];
                    if(arr[i+2] - tag != 0) continue;
                    if(arr[i+1] > this.graph.getMapOfSrc().get(start).get(s).getWeight()){
                        arr[i+1]= this.graph.getMapOfSrc().get(start).get(s).getWeight();
                        arr[i+2]= arr[start]+ 0.0;
                    }
                }
                i= i+3;
            }
        }
        return arr[start+1];
    }

    private int minInArr(double[] arr){
        int min= 1;
        for(int i=4; i<arr.length; i+=3){
            if(arr[i] < arr[min])
                min= i;
        }
        return min;
    }
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> path= new LinkedList<>();
        path.add(this.graph.getNode(src));
        double[] arr= new double[(this.graph.nodeSize()-1)*3];
        int i= 0;
//        int srcindex= -1;
        int start= -1;
        for(Integer s: this.graph.getMapOfNode().keySet()) {
            if(s == src) {
                start= i;
                i=i+3;
                continue;
            }
            arr[i] = s;
            if(this.graph.getMapOfSrc().get(src).containsKey(s))
                arr[i + 1] = this.graph.getMapOfSrc().get(src).get(s).getWeight();
            else
                arr[i+1] = Integer.MAX_VALUE;
            arr[i + 2] = src + 0.0;
            i = i + 3;
        }
//        int start= src;
        while (arr[start] != dest){
            int min= minInArr(arr)-1;
            arr[min+2]+= 0.1;
            start= min;
            i=0;
            for(Integer s: this.graph.getMapOfNode().keySet()){
                if(s==start){
                    i= i+3;
                    continue;
                }
                if(this.graph.getMapOfSrc().get(start).containsKey(s)){
                    int tag= (int) arr[i+2];
                    if(arr[i+2] - tag != 0) continue;
                    if(arr[i+1] > this.graph.getMapOfSrc().get(start).get(s).getWeight()){
                        arr[i+1]= this.graph.getMapOfSrc().get(start).get(s).getWeight();
                        arr[i+2]= arr[start]+ 0.0;
                        if(s == dest)
                            path.add(this.graph.getNode((int) arr[start]));
                    }
                }
                i= i+3;
            }
        }
        path.add(0, this.graph.getNode(dest));
        return path;
    }

    @Override
    public NodeData center() {
        double min = Double.MAX_VALUE;
        ArrayList<Double> shortsPath = new ArrayList<Double>();
        ArrayList<Double> justTheShorts = new ArrayList<Double>();
        double shortPath = 0;
        int ind = 0;
        NodeData NodeTmp;
        for (int i: this.graph.getMapOfNode().keySet()){
            double maxShortPath = 0;
            for (int j: this.graph.getMapOfNode().keySet()){
                shortPath = shortestPathDist(i,j);
//                if (shortPath > maxShortPath) {
                maxShortPath = (shortPath > maxShortPath) ? shortPath:maxShortPath;
//                }
            }
            if (maxShortPath < min){
                min = maxShortPath;
                ind = i;
            }
        }
        NodeTmp = this.graph.getNode(ind);
        return NodeTmp;
    }

    private double minInArray (ArrayList<Double> arr){
        double min = arr.get(0);
        for (int i = 0; i < arr.size()-1; i++) {
            if (arr.get(i) > arr.get(i+1)){
                min = arr.get(i+1);
            }
        }
        return min;
    }

    private double maxInArray (ArrayList<Double> arr){
        double max = arr.get(0);
        for (int i = 0; i < arr.size()-1; i++) {
            if (arr.get(i) < arr.get(i+1)){
                max = arr.get(i+1);
            }
        }
        return max;
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
