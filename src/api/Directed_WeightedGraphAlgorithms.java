package api;

import java.io.File;
import java.util.List;

public class Directed_WeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms{
    private Directed_WeightedGraph graph;

    public Directed_WeightedGraphAlgorithms(Directed_WeightedGraph g){
//        this.graph= new Directed_WeightedGraph(g.getMapOfNode(), g.getMapOfEdge());
    }
    @Override
    public void init(api.DirectedWeightedGraph g) {

    }

    @Override
    public api.DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public api.DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
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



}
