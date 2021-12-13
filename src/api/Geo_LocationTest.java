package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Geo_LocationTest {
    Geo_Location g1 = new Geo_Location(2, 3, 5);
    Geo_Location g2 = new Geo_Location(5, 7, 1);

    @Test
    void x() {
        assertEquals(2, g1.x());
        assertEquals(5, g2.x());
    }

    @Test
    void y() {
        assertEquals(3, g1.y());
        assertEquals(7, g2.y());
    }

    @Test
    void z() {
        assertEquals(5, g1.z());
        assertEquals(1, g2.z());
    }

    @Test
    void distance() {
        assertEquals(6.4031242374328486864882176746218, g1.distance(g2));
    }
}