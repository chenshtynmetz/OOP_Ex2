package api;

import java.util.HashMap;
import java.util.Iterator;

public class Directed_WeightedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, Node_Data> mapOfNode;
    // TODO: what is the key of the mapofedge?
    private HashMap<Integer, Edge_Data> mapOfEdge;

     public Directed_WeightedGraph(){
         this.mapOfNode= new HashMap<Integer, Node_Data>();
         this.mapOfEdge= new HashMap<Integer, Edge_Data>();
     }

    // TODO: ask about hashmap.
     public Directed_WeightedGraph(HashMap<Integer, Node_Data> map){
         this.mapOfNode= new HashMap<Integer, Node_Data>(map);
     }

    @Override
    public NodeData getNode(int key) {
        return this.mapOfNode.get(key);
    }

    // TODO: ask about return objcet instead interface (api.EdgeDate-> Edge_Data).
    @Override
    public Edge_Data getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        this.mapOfNode.put(n.getKey(), new Node_Data(n.getKey(), (Geo_Location) n.getLocation(), n.getInfo(), n.getTag()));
    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<api.EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<api.EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return this.mapOfNode.remove(key);
    }

    @Override
    public api.EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return this.mapOfNode.size();
    }

    @Override
    public int edgeSize() {
        return this.mapOfEdge.size();
    }

    @Override
    public int getMC() {
        return 0;
    }
}
