package api;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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
        boolean f = alg1.isConnected();
        assertEquals(true, alg1.isConnected());
        alg1.getGraph().removeEdge(4,1);
        f= alg1.isConnected();
        assertEquals(false, f);
        alg1.getGraph().connect(4, 1, 1.7);
        alg1.getGraph().removeEdge(1, 2);
        f= alg1.isConnected();
        assertEquals(false, f);
    }

    @Test
    void shortestPathDist() {
        alg1.getGraph().addNode(n3);
        alg1.getGraph().addNode(n4);
        alg1.getGraph().addNode(n5);
        alg1.getGraph().connect(3, 1, 0.7);
        alg1.getGraph().connect(2, 5, 5.64);
        alg1.getGraph().connect(1,4, 2.33);
        alg1.getGraph().connect(5,3, 8.1);
        alg1.getGraph().connect(4,1,1.7);
        alg1.getGraph().connect(4,2, 0.3);
        assertEquals(2.63, alg1.shortestPathDist(1,2));
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
        alg1.getGraph().addNode(n3);
        alg1.getGraph().addNode(n4);
        alg1.getGraph().addNode(n5);
        alg1.getGraph().connect(3, 1, 0.7);
        alg1.getGraph().connect(2, 5, 5.64);
        alg1.getGraph().connect(1,4, 2.33);
        alg1.getGraph().connect(5,3, 8.1);
        alg1.getGraph().connect(4,1,1.7);
        int ans = alg1.center().getKey();
        assertEquals(ans,4);
    }

    @Test
    void tsp() {
        alg1.getGraph().addNode(n3);
        alg1.getGraph().addNode(n4);
        alg1.getGraph().addNode(n5);
        alg1.getGraph().connect(3, 1, 0.7);
        alg1.getGraph().connect(2, 5, 5.64);
        alg1.getGraph().connect(1,4, 2.33);
        alg1.getGraph().connect(5,3, 8.1);
        alg1.getGraph().connect(4,1,1.7);
        List<NodeData> cities= new LinkedList<>();
        cities.add(alg1.getGraph().getNode(1));
        cities.add(alg1.getGraph().getNode(2));
        cities.add(alg1.getGraph().getNode(3));
        cities.add(alg1.getGraph().getNode(4));
        cities.add(alg1.getGraph().getNode(5));
        List tsp= alg1.tsp(cities);
        List<NodeData> ans= new LinkedList<>();
        ans.add(alg1.getGraph().getNode(3));
        ans.add(alg1.getGraph().getNode(1));
        ans.add(alg1.getGraph().getNode(4));
        ans.add(alg1.getGraph().getNode(2));
        ans.add(alg1.getGraph().getNode(5));
        for(int i=0; i<ans.size(); i++){
            assertEquals(ans.get(i), tsp.get(i));
        }
    }

    @Test
    void save() {
        alg1.getGraph().addNode(n3);
        alg1.getGraph().addNode(n4);
        alg1.getGraph().addNode(n5);
        alg1.getGraph().connect(3, 1, 0.7);
        alg1.getGraph().connect(2, 5, 5.64);
        alg1.getGraph().connect(1,4, 2.33);
        alg1.getGraph().connect(5,3, 8.1);
        alg1.getGraph().connect(4,1,1.7);
        String file= "test_save.json";
        alg1.save(file);

    }

    @Test
    void load() {
        Node_Data ln0=new Node_Data(0, new Geo_Location(35.19589389346247,32.10152879327731,0.0));
        Node_Data ln1=new Node_Data(1, new Geo_Location(35.20319591121872,32.10318254621849,0.0));
        Node_Data ln2=new Node_Data(2, new Geo_Location(35.20752617756255,32.1025646605042,0.0));
        Node_Data ln3=new Node_Data(3, new Geo_Location(35.21007339305892,32.10107446554622,0.0));
        Node_Data ln4=new Node_Data(4, new Geo_Location(35.21310882485876,32.104636394957986,0.0));
        Node_Data ln5=new Node_Data(5, new Geo_Location(35.212111165456015,32.106235628571426,0.0));
        Node_Data ln6=new Node_Data(6, new Geo_Location(35.20797194027441,32.104854472268904,0.0));
        Node_Data ln7=new Node_Data(7, new Geo_Location(35.205764353510894,32.106326494117646,0.0));
        Node_Data ln8=new Node_Data(8, new Geo_Location(35.20154022114608,32.10594485882353,0.0));
        Node_Data ln9=new Node_Data(9, new Geo_Location(35.19805902663438,32.10525428067227,0.0));
        Node_Data ln10=new Node_Data(10, new Geo_Location(35.197400995964486,32.10510889579832,0.0));
        Node_Data ln11=new Node_Data(11, new Geo_Location(35.19351649233253,32.1061811092437,0.0));
        Node_Data ln12=new Node_Data(12, new Geo_Location(35.18950462792575,32.10788938151261,0.0));
        Node_Data ln13=new Node_Data(13, new Geo_Location(35.189568308313156,32.106617263865544,0.0));
        Node_Data ln14=new Node_Data(14, new Geo_Location(35.18869800968523,32.104927164705884,0.0));
        Node_Data ln15=new Node_Data(15, new Geo_Location(35.187594216303474,32.10378225882353,0.0));
        Node_Data ln16=new Node_Data(16, new Geo_Location(35.19381366747377,32.102419275630254,0.0));
        Directed_WeightedGraph lg1= new Directed_WeightedGraph();
        lg1.addNode(ln0);
        lg1.addNode(ln1);
        lg1.addNode(ln2);
        lg1.addNode(ln3);
        lg1.addNode(ln4);
        lg1.addNode(ln5);
        lg1.addNode(ln6);
        lg1.addNode(ln7);
        lg1.addNode(ln8);
        lg1.addNode(ln9);
        lg1.addNode(ln10);
        lg1.addNode(ln11);
        lg1.addNode(ln12);
        lg1.addNode(ln13);
        lg1.addNode(ln14);
        lg1.addNode(ln15);
        lg1.addNode(ln16);
        lg1.connect(0,16,1.3118716362419698);
        lg1.connect(0,1,1.232037506070033);
        lg1.connect(1,0,1.8635670623870366);
        lg1.connect(1,2,1.8015954015822042);
        lg1.connect(2,1,1.5784991011275615);
        lg1.connect(2,3,1.0631605142699874);
        lg1.connect(2,6,1.7938753352369698);
        lg1.connect(3,2,1.440561778177153);
        lg1.connect(3,4,1.2539385028794277);
        lg1.connect(4,3,1.8418222744214585);
        lg1.connect(4,5,1.1422264879958028);
        lg1.connect(5,4,1.5855912911662344);
        lg1.connect(5,6,1.734311926030133);
        lg1.connect(6,2,1.8474047229605628);
        lg1.connect(6,5,1.4964304236123005);
        lg1.connect(6,7,1.237565124536135);
        lg1.connect(7,6,1.5786081900467002);
        lg1.connect(7,8,1.3717352984705653);
        lg1.connect(8,7,1.2817370911337442);
        lg1.connect(8,9,1.5328553219807337);
        lg1.connect(9,8,1.9855087252581762);
        lg1.connect(9,10,1.2861739185896588);
        lg1.connect(10,9,1.5815006562559664);
        lg1.connect(10,11,1.4962204797190428);
        lg1.connect(11,10,1.3784147388591739);
        lg1.connect(11,12,1.9316059913913906);
        lg1.connect(12,11,1.0666986438224981);
        lg1.connect(12,13,1.5484109702862576);
        lg1.connect(13,12,1.823489852982211);
        lg1.connect(13,14,1.011071987085077);
        lg1.connect(14,13,1.3207562671517605);
        lg1.connect(14,15,1.118950355920981);
        lg1.connect(15,16,1.8726071511162605);
        lg1.connect(15,14,1.635946027210021);
        lg1.connect(16,0,1.4418017651347552);
        lg1.connect(16,15,1.5677693324851103);
















    }
}