package api;

import com.google.gson.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Directed_WeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    // properties
    private Directed_WeightedGraph graph;
    // final variable for tag
    private static final int WHITE = 0;
    private static final int  GRAY = 1;
    private static final int BLACK = 2;

    // constructors;
    public Directed_WeightedGraphAlgorithms(Directed_WeightedGraph g){
        this.graph= new Directed_WeightedGraph(g.getMapOfNode(), g.getMapOfEdge(), g.getMapOfSrc(), g.getMapOfDst());
    }

    // constructor using load methood
    public Directed_WeightedGraphAlgorithms(String file){
        this.load(file);
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

    // in this function we send the first node to BFS algo.
    //
    @Override
    public boolean isConnected() {
        List<NodeData> haveedge = new LinkedList<>();
        List<NodeData> nothavedge = new LinkedList<>();

        for (Integer s : this.graph.getMapOfNode().keySet()) {
            nothavedge.add(this.graph.getNode(s));
//            boolean path= BFS(this.graph, (Node_Data) this.graph.getMapOfNode().get(s));
//            this.cleanTag(this.graph);
//            if(!path) return false;
        }
        NodeData head = nothavedge.remove(0);
        cleanTag(this.graph);
        boolean path = BFS(this.graph, (Node_Data) head);
        cleanTag(this.graph);
        if (!path) return false;
        NodeData temp;
        for (int i = 0; i < nothavedge.size(); ) {
            if (this.graph.getMapOfSrc().get(nothavedge.get(i).getKey()).containsKey(head.getKey())) {
                temp = nothavedge.remove(i);
                haveedge.add(temp);
            }
            else i++;
        }
        if (nothavedge.isEmpty()) return true;
        while (!nothavedge.isEmpty()){
//            int i=0;
            int size= nothavedge.size();
            boolean enter= false;
            for (int i=0; i<nothavedge.size() && enter==false; ){
                for (int j=0; j<haveedge.size() && i<nothavedge.size(); j++){
                    if(this.graph.getMapOfSrc().get(nothavedge.get(i).getKey()).containsKey(haveedge.get(j).getKey())) {
                        temp = nothavedge.remove(i);
                        haveedge.add(temp);
                        enter = true;
                        break;
                    }
                }
                if(enter == false) i++;
//                else {
//                    enter= false;
////                    i=0;
//                }
            }
                if (size == nothavedge.size()) return false;
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
        if(src== dest) return 0;
        double[] arr= new double[(this.graph.nodeSize())*3];
        int i= 0;
        int start= -1;
        for(Integer s: this.graph.getMapOfNode().keySet()) {
            if(s == src) {
                start= i+1;
                arr[i]= src;
                arr[i+1]= 0;
                arr[i+2]= src+0.1;
                this.graph.getMapOfNode().get(s).setTag(1);
                i=i+3;
                continue;
            }
            arr[i] = s;
            if(this.graph.getMapOfSrc().get(src).containsKey(s))
                arr[i + 1] = this.graph.getMapOfSrc().get(src).get(s).getWeight();
            else
                arr[i+1] = Integer.MAX_VALUE-10;
            arr[i + 2] = src + 0.0;
            i = i + 3;
        }
//        int start= src;
        int min= minInArr(arr);
        arr[min+1]+= 0.1;
        this.graph.getMapOfNode().get((int)arr[min-1]).setTag(1);
        start= min;
        while (arr[start-1] != dest){
            i=0;
            for(Integer s: this.graph.getMapOfNode().keySet()){
                if(s==arr[start-1] || this.graph.getMapOfNode().get(s).getTag() == 1){
                    i= i+3;
                    continue;
                }
//                if(!this.graph.getMapOfSrc().get(start).isEmpty()){
                if(this.graph.getMapOfSrc().get((int)arr[start-1]).containsKey(s)){
                    int tag= (int) arr[i+2];
                    if(arr[i+2] - tag != 0) continue;
                    if(arr[i+1] > this.graph.getMapOfSrc().get((int)arr[start-1]).get(s).getWeight()){
                        arr[i+1]= arr[start]+ this.graph.getMapOfSrc().get((int)arr[start-1]).get(s).getWeight();
                        arr[i+2]= arr[start-1]+ 0.0;
                    }
                }
//                }
                i= i+3;
            }
            min= minInArr(arr);
            arr[min+1]+= 0.1;
            this.graph.getMapOfNode().get((int)arr[min-1]).setTag(1);
            start= min;
        }
        return arr[start];
    }

    private int minInArr(double[] arr){
        int min= 1;
        while ((arr[min+1]- (int) arr[min+1]) != 0) {
            min = min + 3;
        }
        for(int i=(min+3); i<arr.length; i+=3){
            if(arr[i] < arr[min] && (arr[i+1]-(int) arr[i+1]) == 0)
                min= i;
        }
        return min;
    }
    @Override
    // TODO: 08/12/2021 fix the function.
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> path= new LinkedList<>();
        path.add(this.graph.getNode(dest));
        double[] arr= new double[(this.graph.nodeSize())*3];
        int i= 0;
        int start= -1;
        for(Integer s: this.graph.getMapOfNode().keySet()) {
            if(s == src) {
                start= i;
                arr[i]= src;
                arr[i+1]= 0;
                arr[i+2]= src+0.1;
                this.graph.getMapOfNode().get(s).setTag(1);
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
        int min= minInArr(arr);
        arr[min+1]+= 0.1;
        this.graph.getMapOfNode().get((int)arr[min-1]).setTag(1);
        start= min;
        while (arr[start-1] != dest){
            i=0;
            for(Integer s: this.graph.getMapOfNode().keySet()){
                if(s==arr[start-1] || this.graph.getMapOfNode().get(s).getTag() == 1){
                    i= i+3;
                    continue;
                }
//                if(!this.graph.getMapOfSrc().get(start).isEmpty()){
                if(this.graph.getMapOfSrc().get((int)arr[start-1]).containsKey(s)){
                    int tag= (int) arr[i+2];
                    if(arr[i+2] - tag != 0) continue;
                    if(arr[i+1] > this.graph.getMapOfSrc().get((int)arr[start-1]).get(s).getWeight()){
                        arr[i+1]= arr[start]+ this.graph.getMapOfSrc().get((int)arr[start-1]).get(s).getWeight();
                        arr[i+2]= arr[start-1]+ 0.0;
                        //                        if(s == dest)
//                            path.add(this.graph.getNode((int) arr[start]));
//                    }
                    }
                }
//                }
                i= i+3;
            }
            min= minInArr(arr);
            arr[min+1]+= 0.1;
            this.graph.getMapOfNode().get((int)arr[min-1]).setTag(1);
            start= min;
        }
//        while(arr[start-1] != src){
//            path.add(this.graph.getNode((int)arr[start+1]));
//            start=
//        }
//        double[] arr= new double[(this.graph.nodeSize()-1)*3];
//        int i= 0;
////        int srcindex= -1;
//        int start= -1;
//        for(Integer s: this.graph.getMapOfNode().keySet()) {
//            if(s == src) {
//                start= i;
//                i=i+3;
//                continue;
//            }
//            arr[i] = s;
//            if(this.graph.getMapOfSrc().get(src).containsKey(s))
//                arr[i + 1] = this.graph.getMapOfSrc().get(src).get(s).getWeight();
//            else
//                arr[i+1] = Integer.MAX_VALUE;
//            arr[i + 2] = src + 0.0;
//            i = i + 3;
//        }
////        int start= src;
//        while (arr[start] != dest){
//            int min= minInArr(arr)-1;
//            arr[min+2]+= 0.1;
//            start= min;
//            i=0;
//            for(Integer s: this.graph.getMapOfNode().keySet()){
//                if(s==start){
//                    i= i+3;
//                    continue;
//                }
//                if(this.graph.getMapOfSrc().get(start).containsKey(s)){
//                    int tag= (int) arr[i+2];
//                    if(arr[i+2] - tag != 0) continue;
//                    if(arr[i+1] > this.graph.getMapOfSrc().get(start).get(s).getWeight()){
//                        arr[i+1]= this.graph.getMapOfSrc().get(start).get(s).getWeight();
//                        arr[i+2]= arr[start]+ 0.0;
//                        if(s == dest)
//                            path.add(this.graph.getNode((int) arr[start]));
//                    }
//                }
//                i= i+3;
//            }
//        }
//        path.add(0, this.graph.getNode(dest));
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
            int temp= -1;
            for (int j: this.graph.getMapOfNode().keySet()){
                if (i != j) {
                    cleanTag(this.graph);
                    shortPath = shortestPathDist(j, i);
//                if (shortPath > maxShortPath) {
                    if (shortPath > maxShortPath){
                        maxShortPath = shortPath;
                        temp = j;
                    }
//                    maxShortPath = (shortPath > maxShortPath) ? shortPath : maxShortPath;
//                    temp=j;
//                }
                }
            }
            if (maxShortPath < min){
                min = maxShortPath;
                ind = temp;
            }
        }
        NodeTmp = this.graph.getNode(ind);
        return NodeTmp;
    }

//    private double minInArray (ArrayList<Double> arr){
//        double min = arr.get(0);
//        for (int i = 0; i < arr.size()-1; i++) {
//            if (arr.get(i) > arr.get(i+1)){
//                min = arr.get(i+1);
//            }
//        }
//        return min;
//    }
//
//    private double maxInArray (ArrayList<Double> arr){
//        double max = arr.get(0);
//        for (int i = 0; i < arr.size()-1; i++) {
//            if (arr.get(i) < arr.get(i+1)){
//                max = arr.get(i+1);
//            }
//        }
//        return max;
//    }

    // TODO: 07/12/2021 maybe to do arraylist for all the options to beagining vertex.
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> path= new LinkedList<>();
        Point minimumstart= new Point(0,0);
        double mindis= Integer.MAX_VALUE;
        for(Point p: this.graph.getMapOfEdge().keySet()){
            if(mindis> this.graph.getMapOfEdge().get(p).getWeight()){
                mindis=this.graph.getMapOfEdge().get(p).getWeight();
                minimumstart= p;
            }

        }

//        for(int i=0; i<cities.size(); i++){
//            for(int j=1; j<cities.size(); j++){
//                double dis= this.shortestPathDist(i, j);
//                if(mindis > dis){
//                    mindis = dis;
//                    minimumstart= i;
//                }
//            }
//        }
        double min= Integer.MAX_VALUE;
        int tempkey= -1;
        Node_Data tempnode= (Node_Data) cities.remove(minimumstart.x-1);
        path.add(tempnode);
        while (!cities.isEmpty()) {
            for(int s=0; s<cities.size(); s++){
//            for (Integer s : this.graph.getMapOfSrc().get(tempnode.getKey()).keySet()) {
//                if (cities.contains(this.graph.getNode(s))) {
                    double dis= this.shortestPathDist(tempnode.getKey(), cities.get(s).getKey());
                    if (min > dis) {
                        min = dis;
                        tempkey= cities.get(s).getKey();
                    }
                }
//            }
            tempnode= (Node_Data) cities.remove(cities.indexOf(this.graph.getNode(tempkey)));
            path.add(tempnode);
            min= Integer.MAX_VALUE;
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

            for (EdgeData e : this.graph.getMapOfSrc().get(n.getKey()).values()){ //  .getE(n.getKey())) {
                JsonObject edge = new JsonObject();
                edge.addProperty("src", e.getSrc());
                edge.addProperty("w", e.getWeight());
                edge.addProperty("dest", e.getDest());
                edges.add(edge);
            }
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

            this.graph = (Directed_WeightedGraph) graph1;
            for (JsonElement e : edges) {
                int src = ((JsonObject) e).get("src").getAsInt();
                double weight = ((JsonObject) e).get("w").getAsDouble();
                int dest = ((JsonObject) e).get("dest").getAsInt();

                EdgeData e1 = new Edge_Data((Node_Data) this.graph.getNode(src), (Node_Data) this.graph.getNode(dest), weight);
                graph1.connect(src, dest, weight);
            }

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean BFS (Directed_WeightedGraph graph, Node_Data nodeSrc){
        Queue<Node_Data> queue = new LinkedList<>();
        queue.add(nodeSrc);
        //graph.getMapOfNode().get(nodeSrc.getKey()).setTag(GRAY);
        //for
        while (!(queue.isEmpty())) {
            Node_Data TmpNode = queue.poll();
            for (int i : graph.getMapOfSrc().get(TmpNode.getKey()).keySet()) {
                if (TmpNode.getTag() == WHITE) {
//            for (int i = 0; i < graph.getMapOfSrc().get(TmpNode.getKey()).size(); i++) {
//                graph.getMapOfSrc().get(nodeSrc.getKey()).get(i).setTag(GRAY);
                    // TODO: 07/12/2021 this is the problem. e==null
                    Edge_Data e = (Edge_Data) graph.getMapOfSrc().get(TmpNode.getKey()).get(i);
                    //e.getNodeDest().setTag(GRAY);
                    queue.add(e.getNodeDest());
                }

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
