package api;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;

public class Directed_WeightedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> mapOfNode;
    // TODO: what is the key of the mapofedge?
    private HashMap<Point, EdgeData> mapOfEdge;
    private HashMap<Integer, HashMap<Integer, Node_Data>> mapOfSrc;
    private HashMap<Integer,HashMap<Integer, Node_Data>> mapOfDst;
    private int mc= 0;

     public Directed_WeightedGraph(){
         this.mapOfNode= new HashMap<Integer, NodeData>();
         this.mapOfEdge= new HashMap<Point, EdgeData>();
         this.mapOfSrc= new HashMap<Integer,HashMap<Integer, Node_Data>>();
         this.mapOfDst= new HashMap<Integer,HashMap<Integer, Node_Data>>();
     }

    // TODO: ask about hashmap.
     public Directed_WeightedGraph(HashMap<Integer, Node_Data> map, HashMap<Point, Edge_Data> map2, HashMap<Integer,HashMap<Integer, Node_Data>> map3, HashMap<Integer,HashMap<Integer, Node_Data>> map4){
         this.mapOfNode= new HashMap<Integer, NodeData>(map);
         this.mapOfEdge= new HashMap<Point, EdgeData>(map2);
         this.mapOfSrc= new HashMap<Integer,HashMap<Integer, Node_Data>>(map3);
         this.mapOfDst= new HashMap<Integer,HashMap<Integer, Node_Data>>(map4);
     }

     public Directed_WeightedGraph(Edge_Data e){
         this.mapOfNode= new HashMap<Integer, NodeData>();
         mapOfNode.put(e.getSrc(), e.getNodeSrc() );
         mapOfNode.put(e.getDest(), e.getNodeDest());
         this.mapOfEdge= new HashMap<Point, EdgeData>();
         mapOfEdge.put(e.getId(), e);
         this.mapOfSrc= new HashMap<Integer,HashMap<Integer, Node_Data>>();
         mapOfSrc.put(e.getSrc(), new HashMap<Integer,Node_Data>());
         mapOfSrc.get(e.getSrc()).put(e.getDest(), e.getNodeDest());
         this.mapOfDst= new HashMap<Integer,HashMap<Integer, Node_Data>>();
         mapOfDst.put(e.getDest(), new HashMap<Integer,Node_Data>());
         mapOfSrc.get(e.getDest()).put(e.getSrc(), e.getNodeSrc());
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
         if(this.mapOfNode.containsKey(n.getKey())) return;
        this.mapOfNode.put(n.getKey(), new Node_Data(n.getKey(), (Geo_Location) n.getLocation(), n.getInfo(), n.getTag()));
        this.mapOfSrc.put(n.getKey(), new HashMap<Integer, Node_Data>());
        this.mapOfDst.put(n.getKey(), new HashMap<Integer, Node_Data>());
        this.mc++;
    }

    // TODO: 05/12/2021 this correct? and what is it the info and the tag of the edge?
    @Override
    public void connect(int src, int dest, double w) {
        Edge_Data e= new Edge_Data((Node_Data) this.mapOfNode.get(src), (Node_Data) this.mapOfNode.get(dest), w, "", 0 );
        Point p= new  Point(src, dest);
        e.setId(p);
        this.mapOfEdge.put(e.getId(), e);
        this.mapOfSrc.get(src).put(dest, (Node_Data)this.mapOfNode.get(dest));
        this.mapOfDst.get(dest).put(src, (Node_Data) this.mapOfNode.get(src));
        this.mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() throws RuntimeException {
        Iterator<NodeData> iter = mapOfNode.values().iterator();
        return iter;
    }

    @Override
    public Iterator<api.EdgeData> edgeIter() throws RuntimeException{
         Iterator<EdgeData> iter= mapOfEdge.values().iterator();
        return iter;
    }

    // TODO: 06/12/2021 how to do casting? 
    @Override
    public Iterator<api.EdgeData> edgeIter(int node_id) throws RuntimeException{
//         Iterator<EdgeData> iter= mapOfEdge.values(new Point(node_id, )).iterator();
        Iterator<Node_Data> iter= mapOfSrc.get(node_id).values().iterator();
//        return (Iterator<EdgeData>) iter;
        return null;
    }

    // TODO: 06/12/2021 this function need to remove the nodes from 2 another hashmap. 
    @Override
    public NodeData removeNode(int key) {
        return this.mapOfNode.remove(key);
    }

    // TODO: 06/12/2021 this function need to  remove the connection between the nodes in 2 hashmap. 
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

    public HashMap<Integer,NodeData> getMapOfNode(){
         return this.mapOfNode;
    }

    public HashMap<Point, EdgeData> getMapOfEdge(){
        return this.mapOfEdge;
    }
}
