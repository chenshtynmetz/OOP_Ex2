package api;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;

public class Directed_WeightedGraph implements DirectedWeightedGraph {
    // properties
    private HashMap<Integer, NodeData> mapOfNode;
    private HashMap<Point, EdgeData> mapOfEdge;
    private HashMap<Integer, HashMap<Integer, EdgeData>> mapOfSrc;
    private HashMap<Integer,HashMap<Integer, EdgeData>> mapOfDst;
    private int mc= 0;
    // final variable for tag
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    // defult constructor
     public Directed_WeightedGraph(){
         this.mapOfNode= new HashMap<Integer, NodeData>();
         this.mapOfEdge= new HashMap<Point, EdgeData>();
         this.mapOfSrc= new HashMap<Integer,HashMap<Integer, EdgeData>>();
         this.mapOfDst= new HashMap<Integer,HashMap<Integer, EdgeData>>();
     }

    // TODO: 06/12/2021 deep copy or not?
    // copy constructor
     public Directed_WeightedGraph(Directed_WeightedGraph other){
         this.mapOfNode= other.getMapOfNode();
         this.mapOfSrc= other.getMapOfSrc();
         this.mapOfDst= other.getMapOfDst();
         this.mapOfEdge= other.getMapOfEdge();
     }

    // TODO: ask about hashmap.
    // constructors
     public Directed_WeightedGraph(HashMap<Integer, NodeData> map, HashMap<Point, EdgeData> map2, HashMap<Integer,HashMap<Integer, EdgeData>> map3, HashMap<Integer,HashMap<Integer, EdgeData>> map4){
         this.mapOfNode= new HashMap<Integer, NodeData>(map);
         this.mapOfEdge= new HashMap<Point, EdgeData>(map2);
         this.mapOfSrc= new HashMap<Integer,HashMap<Integer, EdgeData>>(map3);
         this.mapOfDst= new HashMap<Integer,HashMap<Integer, EdgeData>>(map4);
     }

     public Directed_WeightedGraph(Edge_Data e){
         this.mapOfNode= new HashMap<Integer, NodeData>();
         mapOfNode.put(e.getSrc(), e.getNodeSrc() );
         mapOfNode.put(e.getDest(), e.getNodeDest());
         this.mapOfEdge= new HashMap<Point, EdgeData>();
         mapOfEdge.put(e.getId(), e);
         this.mapOfSrc= new HashMap<Integer,HashMap<Integer, EdgeData>>();
         mapOfSrc.put(e.getSrc(), new HashMap<Integer,EdgeData>());
         mapOfSrc.get(e.getSrc()).put(e.getDest(), e);
         mapOfSrc.put(e.getDest(), new HashMap<Integer,EdgeData>());
         this.mapOfDst= new HashMap<Integer,HashMap<Integer, EdgeData>>();
         mapOfDst.put(e.getDest(), new HashMap<Integer,EdgeData>());
         mapOfDst.get(e.getDest()).put(e.getSrc(), e);
         mapOfDst.put(e.getSrc(), new HashMap<Integer,EdgeData>());
     }

     // Getters and Setters
    @Override
    public NodeData getNode(int key) {
        return this.mapOfNode.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Point p= new Point(src, dest);
        return this.mapOfEdge.get(p);
    }

    // add node to the graph
    @Override
    public void addNode(NodeData n) {
         if(this.mapOfNode.containsKey(n.getKey())) return;
        this.mapOfNode.put(n.getKey(), new Node_Data(n.getKey(), (Geo_Location) n.getLocation(), n.getInfo(), n.getTag()));
        this.mapOfSrc.put(n.getKey(), new HashMap<Integer, EdgeData>());
        this.mapOfDst.put(n.getKey(), new HashMap<Integer, EdgeData>());
        this.mc++;
    }

    // TODO: 05/12/2021 this correct? and what is it the info and the tag of the edge?
    // create edge between src and dest and add this edge to the graph
    @Override
    public void connect(int src, int dest, double w) {
        Edge_Data e= new Edge_Data((Node_Data) this.mapOfNode.get(src), (Node_Data) this.mapOfNode.get(dest), w, "", 0 );
        Point p= new Point(src, dest);
        e.setId(p);
        this.mapOfEdge.put(e.getId(), e);
        this.mapOfSrc.get(src).put(dest, e);
        this.mapOfDst.get(dest).put(src, e);
        this.mc++;
    }

    // create iterators
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
        Iterator<EdgeData> iter= mapOfSrc.get(node_id).values().iterator();
        return iter;
    }

    // remove items from the graph
    @Override
    public NodeData removeNode(int key) {
         if(this.mapOfSrc.get(key) != null) {
             for (Integer s : this.mapOfSrc.get(key).keySet()) {
                 this.mapOfEdge.remove(this.mapOfSrc.get(key).get(s));
             }
         }
         this.mapOfSrc.remove(key);
         if (this.mapOfDst.get(key) != null) {
             for (Integer d : this.mapOfDst.get(key).keySet()) {
                 this.mapOfEdge.remove(this.mapOfDst.get(key).get(d));
             }
         }
        this.mapOfDst.remove(key);
        this.mapOfSrc.remove(key);
        this.mapOfDst.remove(key);
        Edge_Data e= null;
        for (int i = this.mapOfEdge.size()-1; i >= 0 ; i--) {
            if(this.mapOfEdge.get(i) == null) continue;
            if(this.mapOfEdge.get(i).getSrc() == key || this.mapOfEdge.get(i).getDest() == key)
                this.mapOfEdge.remove(new Point(this.mapOfEdge.get(i).getSrc(),this.mapOfEdge.get(i).getDest()));
        }

//        Iterator iter= this.edgeIter();
//        while (iter.hasNext()){
////        for(Point p: this.mapOfEdge.keySet()){
//            e= (Edge_Data) iter.next();
//            if(e.getSrc() == key || e.getDest() == key)
//                this.mapOfEdge.remove(new Point(e.getSrc(), e.getDest()));
//            if(p.x == key || p.y == key)
//                this.mapOfEdge.remove(p);
//        }
        this.mc++;
         return this.mapOfNode.remove(key);
    }

    @Override
    public api.EdgeData removeEdge(int src, int dest) {
        this.mapOfSrc.get(src).remove(dest);
        this.mapOfDst.get(dest).remove(src);
        this.mc++;
        Point p = new Point(src, dest);
        return this.mapOfEdge.remove(p);
    }
    // more getters
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
    
    public HashMap<Integer, HashMap<Integer, EdgeData>> getMapOfSrc(){
         return this.mapOfSrc;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getMapOfDst(){
        return this.mapOfDst;
    }
}
