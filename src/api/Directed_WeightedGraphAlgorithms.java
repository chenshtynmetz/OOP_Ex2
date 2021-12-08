package api;

import com.google.gson.*;

import java.io.*;
import java.util.*;


public class Directed_WeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    private Directed_WeightedGraph graph;
    private static final int WHITE = 0;
    private static final int  GRAY = 11;
    private static final int BLACK = 2;
//    private double [][] matrix;

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

//    private void creatMatrix(Directed_WeightedGraph  g){
//        this.matrix= new double[this.graph.getMapOfNode().size()][this.graph.getMapOfNode().size()];
//        for(Integer s : this.graph.getMapOfSrc().keySet()){
//            for(Integer d : this.graph.getMapOfSrc().keySet()){
//                matrix[s][d]= this.graph.getMapOfSrc().get(s).get(d).getWeight();
//            }
//        }
//    }
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

    // TODO: 07/12/2021 maybe to do arraylist for all the options to beagining vertex.
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> path= new LinkedList<>();
        int minimumstart= 0;
        double mindis= Integer.MAX_VALUE;
        for(int i=0; i<cities.size(); i++){
            for(int j=1; j<cities.size(); j++){
                double dis= this.shortestPathDist(i, j);
                if(mindis > dis){
                    mindis = dis;
                    minimumstart= i;
                }
            }
        }
        double min= Integer.MAX_VALUE;
        int tempkey= -1;
        Node_Data tempnode= (Node_Data) cities.remove(minimumstart);
        path.add(tempnode);
        while (!cities.isEmpty()) {
            for (Integer s : this.graph.getMapOfSrc().get(tempnode.getKey()).keySet()) {
                if (cities.contains(this.graph.getNode(s))) {
                    double dis= this.shortestPathDist(tempnode.getKey(), s);
                    if (min > dis) {
                        min = dis;
                        tempkey= s;
                    }
                }
            }
            tempnode= (Node_Data) cities.remove(cities.indexOf(this.graph.getNode(tempkey)));
            path.add(tempnode);
        }
        return path;
    }

    @Override
    public boolean save(String file) {
        Gson json = new GsonBuilder().create();
        JsonArray nodes = new JsonArray();
        JsonArray edges = new JsonArray();
        JsonObject graph = new JsonObject();
        for (NodeData n : this.graph.getMapOfNode().values()) {
            JsonObject o = new JsonObject();
            double location = n.getLocation().x(), y = n.getLocation().y(), z = n.getLocation().z();
            o.addProperty("pos", location + "," + y + "," + z);
            o.addProperty("id", n.getKey());
            nodes.add(o);

//            for (EdgeData e : this.graph.edgeIter(n.getKey())){ //  .getE(n.getKey())) {
//                JsonObject edge = new JsonObject();
//                edge.addProperty("src", e.getSrc());
//                edge.addProperty("w", e.getWeight());
//                edge.addProperty("dest", e.getDest());
//                edges.add(edge);
//            }
        }
        graph.add("Nodes", nodes);
        graph.add("Edges", edges);
        File x = new File(file);
        try {
            FileWriter fileWriter = new FileWriter(x);
            fileWriter.write(json.toJson(graph));
            fileWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean load(String file) {
        DirectedWeightedGraph graph1 = new Directed_WeightedGraph();
        JsonObject graph;
        File f = new File(file);
        try {
            FileReader reader = new FileReader(f);
            graph = new JsonParser().parse(reader).getAsJsonObject();
            JsonArray nodes = graph.getAsJsonArray("Nodes");
            JsonArray edges = graph.getAsJsonArray("Edges");

            for (JsonElement n : nodes) {

                int id = ((JsonObject) n).get("id").getAsInt();
                double x1 = 0;
                double x2 = 0;
                double x3 = 0;
                String pos = ((JsonObject) n).get("pos").getAsString();
                String x="";
                int counter=0;
                for (int i = 0; i < pos.length(); i++) {
                    if(pos.charAt(i)==','){
                        if(counter==0) x1=Double.parseDouble(x);
                        if(counter==1) x2=Double.parseDouble(x);
                        if(counter==2) x3=Double.parseDouble(x);
                        counter++;
                        x="";
                    }
                    else{
                        x=x + pos.charAt(i);
                    }
                }
                GeoLocation location = new Geo_Location(x1, x2, x3);
                NodeData n1 = new Node_Data(id, (Geo_Location) location);
                graph1.addNode(n1);
            }

            for (JsonElement e : edges) {
                int src = ((JsonObject) e).get("src").getAsInt();
                double weight = ((JsonObject) e).get("w").getAsDouble();
                int dest = ((JsonObject) e).get("dest").getAsInt();

//                EdgeData e1 = new Edge_Data(src, dest, weight);
//                graph1.connect(e1.getSrc(), e1.getDest(), e1.getWeight());
            }
            this.graph = (Directed_WeightedGraph) graph1;
            return true;

        } catch (FileNotFoundException e) {
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
//            for(int i: graph.getMapOfSrc().get(TmpNode.getKey()).keySet()){
            for (int i = 0; i < graph.getMapOfSrc().get(TmpNode.getKey()).keySet().size(); i++) {
//                graph.getMapOfSrc().get(nodeSrc.getKey()).get(i).setTag(GRAY);
                // TODO: 07/12/2021 this is the problem. e==null 
                Edge_Data e= (Edge_Data) graph.getMapOfSrc().get(TmpNode.getKey()).get(i);
                e.getNodeDest().setTag(GRAY);
                queue.add(e.getNodeDest());
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
