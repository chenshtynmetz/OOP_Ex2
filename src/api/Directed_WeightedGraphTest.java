package api;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Directed_WeightedGraphTest {
    Geo_Location g1 = new Geo_Location(2, 3, 5);
    Geo_Location g2 = new Geo_Location(5, 7, 1);
    Node_Data n1 = new Node_Data(1, g1, "", 0);
    Node_Data n2 = new Node_Data(2, g1, "", 0);
    Node_Data n3 = new Node_Data(3, g2, "", 0);
    Node_Data n4 = new Node_Data(4, g2, "", 0);
    Node_Data n5 = new Node_Data(5, g1, "", 0);
    Edge_Data e1 = new Edge_Data(n1, n2, 3.44, "", 0);
    Edge_Data e2 = new Edge_Data(n1, n4, 2.33, "", 0);
    Edge_Data e3 = new Edge_Data(n2, n5, 5.64, "", 0);
    Edge_Data e4 = new Edge_Data(n5, n3, 8.1, "", 0);
    Edge_Data e5 = new Edge_Data(n3, n1, 0.7, "", 0);
    Directed_WeightedGraph gr1 = new Directed_WeightedGraph(e1);

    @Test
    void getNode() {
        NodeData ans = gr1.getNode(1);
        assertEquals(n1.getKey(), ans.getKey());
        assertEquals(n1.getLocation().x(), ans.getLocation().x());
        assertEquals(n1.getLocation().y(), ans.getLocation().y());
        assertEquals(n1.getLocation().z(), ans.getLocation().z());
        assertEquals(n1.getTag(), ans.getTag());
        assertTrue(n1.getInfo().equals(ans.getInfo()));
    }

    @Test
    void getEdge() {
        EdgeData ans = gr1.getEdge(1, 2);
        assertEquals(e1.getSrc(), ans.getSrc());
        assertEquals(e1.getDest(), ans.getDest());
        assertEquals(e1.getWeight(), ans.getWeight());
        assertEquals(e1.getTag(), ans.getTag());
        assertTrue(e1.getInfo().equals(ans.getInfo()));
    }

    @Test
    void addNode() {
        gr1.addNode(n3);
        NodeData ans = gr1.getNode(3);
        assertEquals(n3.getKey(), ans.getKey());
        assertEquals(n3.getLocation().x(), ans.getLocation().x());
        assertEquals(n3.getLocation().y(), ans.getLocation().y());
        assertEquals(n3.getLocation().z(), ans.getLocation().z());
        assertEquals(n3.getTag(), ans.getTag());
        assertTrue(n3.getInfo().equals(ans.getInfo()));
    }

    @Test
    void connect() {
        gr1.addNode(n3);
        gr1.connect(n3.getKey(), n1.getKey(), 0.7);
        EdgeData ans = gr1.getEdge(n3.getKey(), n1.getKey());
        assertEquals(e5.getSrc(), ans.getSrc());
        assertEquals(e5.getDest(), ans.getDest());
        assertEquals(e5.getWeight(), ans.getWeight());
        assertEquals(e5.getTag(), ans.getTag());
        assertTrue(e5.getInfo().equals(ans.getInfo()));
    }

    @Test
    void removeNode() {
        gr1.addNode(n3);
        gr1.connect(n3.getKey(), n1.getKey(), 0.7);
        gr1.removeNode(3);
        assertEquals(false, gr1.getMapOfNode().containsKey(3));
        assertEquals(false, gr1.getMapOfEdge().containsKey(new Point(3, 1)));
        assertEquals(false, gr1.getMapOfSrc().containsKey(3));
        assertEquals(false, gr1.getMapOfDst().containsKey(3));
        Directed_WeightedGraphAlgorithms alg1 = new Directed_WeightedGraphAlgorithms(gr1);
        alg1.load("G1.json");
        Directed_WeightedGraph gr2 = (Directed_WeightedGraph) alg1.getGraph();
        gr2.removeNode(2);
        assertEquals(false, gr2.getMapOfNode().containsKey(2));

    }

    @Test
    void removeEdge() {
        gr1.addNode(n3);
        gr1.connect(n3.getKey(), n1.getKey(), 0.7);
        gr1.removeEdge(3, 1);
        assertEquals(false, gr1.getMapOfEdge().containsKey(new Point(3, 1)));
        assertEquals(false, gr1.getMapOfSrc().get(3).containsKey(1));
        assertEquals(false, gr1.getMapOfDst().get(1).containsKey(3));
    }

    @Test
    void nodeSize() {
        assertEquals(2, gr1.nodeSize());
        gr1.addNode(n3);
        gr1.addNode(n4);
        assertEquals(4, gr1.nodeSize());
    }

    @Test
    void edgeSize() {
        assertEquals(1, gr1.edgeSize());
        gr1.addNode(n3);
        gr1.connect(n3.getKey(), n1.getKey(), 0.7);
        assertEquals(2, gr1.edgeSize());
    }

    @Test
    void getMC() {
        assertEquals(0, gr1.getMC());
        gr1.addNode(n3);
        gr1.connect(n3.getKey(), n1.getKey(), 0.7);
        assertEquals(2, gr1.getMC());
        gr1.removeNode(3);
        assertEquals(3, gr1.getMC());
    }

}