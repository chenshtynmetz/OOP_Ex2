package api;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Edge_DataTest {
    Geo_Location g1= new Geo_Location(2, 3, 5);
    Geo_Location g2= new Geo_Location(5, 7, 1);
    Node_Data n1= new Node_Data(1, g1, "", 0 );
    Node_Data n2= new Node_Data(2, g2, "", 0);
    Edge_Data e1= new Edge_Data(n1, n2, 3, "", 0);
    @org.junit.jupiter.api.Test
    void getSrc() {
        assertEquals(1, e1.getSrc());

    }

    @org.junit.jupiter.api.Test
    void getDest() {
        assertEquals(2, e1.getDest());
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        assertEquals(3, e1.getWeight());
    }

    @org.junit.jupiter.api.Test
    void getInfo() {
        assertTrue("".equals(e1.getInfo()));
    }

    @org.junit.jupiter.api.Test
    void setInfo() {
        e1.setInfo("aaa");
        assertTrue("aaa".equals(e1.getInfo()));
    }

    @org.junit.jupiter.api.Test
    void getTag() {
        assertEquals(0, e1.getTag());
    }

    @org.junit.jupiter.api.Test
    void setTag() {
        e1.setTag(1);
        assertEquals(1, e1.getTag());
    }

    @org.junit.jupiter.api.Test
    void getNodeSrc() {
        assertEquals(1, e1.getNodeSrc().getKey());
        assertEquals(0, e1.getNodeSrc().getTag());
        assertTrue("".equals(e1.getNodeSrc().getInfo()));
        assertEquals(2, e1.getNodeSrc().getLocation().x());
        assertEquals(3, e1.getNodeSrc().getLocation().y());
        assertEquals(5, e1.getNodeSrc().getLocation().z());
    }

    @org.junit.jupiter.api.Test
    void getNodeDest() {
        assertEquals(2, e1.getNodeDest().getKey());
        assertEquals(0, e1.getNodeDest().getTag());
        assertTrue("".equals(e1.getNodeDest().getInfo()));
        assertEquals(5, e1.getNodeDest().getLocation().x());
        assertEquals(7, e1.getNodeDest().getLocation().y());
        assertEquals(1, e1.getNodeDest().getLocation().z());
    }

    @org.junit.jupiter.api.Test
    void getId() {
        assertEquals(1, e1.getId().x);
        assertEquals(2, e1.getId().y);
    }

    @org.junit.jupiter.api.Test
    void setId() {
    }
}