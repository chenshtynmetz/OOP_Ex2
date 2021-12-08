package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Directed_WeightedGraphAlgorithmsTest {
    Geo_Location g1= new Geo_Location(2, 3, 5);
    Geo_Location g2= new Geo_Location(5, 7, 1);
    Node_Data n1= new Node_Data(1, g1, "", 0);
    Node_Data n2= new Node_Data(2, g1, "", 0);
    Node_Data n3= new Node_Data(3, g2, "", 0);
    Node_Data n4= new Node_Data(4, g2, "", 0);
    Node_Data n5= new Node_Data(5, g1, "", 0);
    Edge_Data e1= new Edge_Data(n1, n2, 3.44, "", 0);
    Edge_Data e2= new Edge_Data(n1, n4, 2.33, "", 0);
    Edge_Data e3= new Edge_Data(n2, n5, 5.64, "", 0);
    Edge_Data e4= new Edge_Data(n5, n3, 8.1, "", 0);
    Edge_Data e5= new Edge_Data(n3, n1, 0.7, "", 0);
    Edge_Data e6= new Edge_Data(n4, n1, 1.7, "", 0);
    Directed_WeightedGraph gr1= new Directed_WeightedGraph(e1);
    Directed_WeightedGraphAlgorithms alg1= new Directed_WeightedGraphAlgorithms(gr1);

    @Test
    void init() {
    }

    @Test
    void getGraph() {
        assertEquals(n1, alg1.getGraph().getNode(1));

    }

    @Test
    void copy() {
        DirectedWeightedGraph ans= alg1.copy();
        assertEquals(gr1.getNode(1), ans.getNode(1));
        assertEquals(gr1.getNode(2), ans.getNode(2));
        assertEquals(gr1.getEdge(1,2), ans.getEdge(1,2));
    }

    @Test
    void isConnected() {
        alg1.getGraph().addNode(n3);
        alg1.getGraph().addNode(n4);
        alg1.getGraph().addNode(n5);
        alg1.getGraph().connect(3, 1, 0.7);
        alg1.getGraph().connect(2, 5, 5.64);
        alg1.getGraph().connect(1,4, 2.33);
        alg1.getGraph().connect(5,3, 8.1);
        alg1.getGraph().connect(4,1,1.7);
        assertEquals(true, alg1.isConnected());
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}