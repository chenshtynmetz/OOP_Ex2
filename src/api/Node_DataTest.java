package api;

import static org.junit.jupiter.api.Assertions.*;

class Node_DataTest {

    Geo_Location g1= new Geo_Location(2, 3, 5);
    Geo_Location g2= new Geo_Location(5, 7, 1);
    Node_Data n1= new Node_Data(1, g1, "", 0 );
    Node_Data n2= new Node_Data(2, g2, "", 0);

    @org.junit.jupiter.api.Test
    void getKey() {
        assertEquals(1, n1.getKey());
        assertEquals(2, n2.getKey());
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        assertEquals(2, n1.getLocation().x());
        assertEquals(3, n1.getLocation().y());
        assertEquals(5, n1.getLocation().z());
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        assertEquals(0,n1.getWeight());
        assertEquals(0, n2.getWeight());
    }

    @org.junit.jupiter.api.Test
    void setWeight() {
        n1.setWeight(0);
        assertEquals(0, n1.getWeight());
    }

    @org.junit.jupiter.api.Test
    void getInfo() {
        assertTrue("".equals(n1.getInfo()));
        assertTrue("".equals(n2.getInfo()));
    }

    @org.junit.jupiter.api.Test
    void setInfo() {
        n1.setInfo("aaa");
        assertTrue("aaa".equals(n1.getInfo()));
    }

    @org.junit.jupiter.api.Test
    void getTag() {
        assertEquals(0, n1.getTag());
    }

    @org.junit.jupiter.api.Test
    void setTag() {
        n2.setTag(2);
        assertEquals(2, n2.getTag());
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        n1.setLocation(g2);
        assertEquals(5, n1.getLocation().x());
        assertEquals(7, n1.getLocation().y());
        assertEquals(1, n1.getLocation().z());
    }
}