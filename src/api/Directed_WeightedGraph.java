package api;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;

public class Directed_WeightedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, Node_Data> mapOfNode;
    // TODO: what is the key of the mapofedge?
    private HashMap<Point, Edge_Data> mapOfEdge;
    private int mc= 0;

     public Directed_WeightedGraph(){
         this.mapOfNode= new HashMap<Integer, Node_Data>();
         this.mapOfEdge= new HashMap<Point, Edge_Data>();
     }

    // TODO: ask about hashmap.
     public Directed_WeightedGraph(HashMap<Integer, Node_Data> map, HashMap<Point, Edge_Data> map2){
         this.mapOfNode= new HashMap<Integer, Node_Data>(map);
         this.mapOfEdge= new HashMap<Point, Edge_Data>(map2);
     }

     public Directed_WeightedGraph(Edge_Data e){
         this.mapOfNode= new HashMap<Integer, Node_Data>();
         mapOfNode.put(e.getSrc(), e.getNodeSrc() );
         mapOfNode.put(e.getDest(), e.getNodeDest());
         this.mapOfEdge= new HashMap<Point, Edge_Data>();
         mapOfEdge.put(e.getId(), e);
     }

    @Override
    public NodeData getNode(int key) {
        return this.mapOfNode.get(key);
    }

    // TODO: ask about return objcet instead interface (api.EdgeDate-> Edge_Data).
    @Override
    public EdgeData getEdge(int src, int dest) {
        Point p= new Point(src, dest);
        return this.mapOfEdge.get(p);
    }

    @Override
    public void addNode(NodeData n) {
        this.mapOfNode.put(n.getKey(), new Node_Data(n.getKey(), (Geo_Location) n.getLocation(), n.getInfo(), n.getTag()));
        this.mc++;
    }

    // TODO: 05/12/2021 this correct? and what is it the info and the tag of the edge?
    @Override
    public void connect(int src, int dest, double w) {
        Edge_Data e= new Edge_Data(this.mapOfNode.get(src), this.mapOfNode.get(dest), w, "", 0 );
        Point p= new  Point(src, dest);
        e.setId(p);
        this.mapOfEdge.put(e.getId(), e);
        this.mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() throws RuntimeException {
//        Iterator<NodeData> iter = mapOfNode.(NodeData)values().iterator();
//        return iter;
        return null;
    }

    @Override
    public Iterator<api.EdgeData> edgeIter() throws RuntimeException{
//         Iterator<EdgeData> iter= mapOfEdge.values().iterator();
//        return iter;
        return null;
    }

    @Override
    public Iterator<api.EdgeData> edgeIter(int node_id) throws RuntimeException{
//         Iterator<EdgeData> iter= mapOfEdge.values(new Point(node_id, )).iterator();
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return this.mapOfNode.remove(key);
    }

    @Override
    public api.EdgeData removeEdge(int src, int dest) {
         Point p= new Point(src, dest);
        return this.mapOfEdge.remove(p);
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
        return this.mc;
    }

}
