package api;

import com.google.gson.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Directed_WeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    // properties.
    private Directed_WeightedGraph graph;
    // final variable for tag
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    // constructors;
    public Directed_WeightedGraphAlgorithms(Directed_WeightedGraph g) {
        this.graph = new Directed_WeightedGraph(g.getMapOfNode(), g.getMapOfEdge(), g.getMapOfSrc(), g.getMapOfDst());
    }

    // constructor using load methood
    public Directed_WeightedGraphAlgorithms(String file) {
        this.load(file);
    }

    //this function init the graph
    @Override
    public void init(api.DirectedWeightedGraph g) {
        this.graph = (Directed_WeightedGraph) g;
    }

    @Override
    public api.DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    //this function copy the graph
    @Override
    public api.DirectedWeightedGraph copy() {
        Directed_WeightedGraph g = new Directed_WeightedGraph(this.graph);
        return (DirectedWeightedGraph) g;
    }

    // in this function we send the first node to BFS algo.
    //and after that it check if to all the vertex have a path to this vertex
    @Override
    public boolean isConnected() {
        NodeData head = this.graph.getNode(0);
        cleanTag(this.graph);
        boolean path = BFS(this.graph, (Node_Data) head);
        cleanTag(this.graph);
        if (!path)
            return false;
        path = reversBFS(this.graph, (Node_Data) head);
        return path;
    }

    //this function return the length of the short path between 2 vertex. based on dijkstra algorithm.
    // return double - the min weight of dest node.
    @Override
    public double shortestPathDist(int src, int dest) {
        Queue<NodeData> q = new LinkedList<>();
        for (int i : this.graph.getMapOfNode().keySet()) {
            this.graph.getNode(i).setInfo("" + Double.POSITIVE_INFINITY);
        }
        this.cleanTag(this.graph);
        this.graph.getNode(src).setInfo("" + 0);
        q.add(this.graph.getNode(src));
        while (!q.isEmpty()) {
            NodeData temp = q.poll();
            for (int i : this.graph.getMapOfSrc().get(temp.getKey()).keySet()) {
                if (Double.parseDouble(temp.getInfo()) + this.graph.getEdge(temp.getKey(), i).getWeight() < Double.parseDouble(this.graph.getNode(i).getInfo())) {
                    double dis = Double.parseDouble(temp.getInfo()) + this.graph.getEdge(temp.getKey(), i).getWeight();
                    this.graph.getNode(i).setInfo("" + dis);
                    this.graph.getNode(i).setTag(temp.getKey());
                    q.add(this.graph.getNode(i));
                }
            }
        }
        return Double.parseDouble(this.graph.getNode(dest).getInfo());
    }

    //this function return a list of the short path between tow vertex. based on dijkstra algorithm.
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = new LinkedList<>();
        Queue<NodeData> q = new LinkedList<>();
        for (int i : this.graph.getMapOfNode().keySet()) {
            this.graph.getNode(i).setInfo("" + Double.POSITIVE_INFINITY);
        }
        this.cleanTag(this.graph);
        this.graph.getNode(src).setInfo("" + 0);
        q.add(this.graph.getNode(src));
        while (!q.isEmpty()) {
            NodeData temp = q.poll();
            for (int i : this.graph.getMapOfSrc().get(temp.getKey()).keySet()) {
                if (Double.parseDouble(temp.getInfo()) + this.graph.getEdge(temp.getKey(), i).getWeight() < Double.parseDouble(this.graph.getNode(i).getInfo())) {
                    double dis = Double.parseDouble(temp.getInfo()) + this.graph.getEdge(temp.getKey(), i).getWeight();
                    this.graph.getNode(i).setInfo("" + dis);
                    this.graph.getNode(i).setTag(temp.getKey());
                    q.add(this.graph.getNode(i));
                }
            }
        }
        ans.add(0, this.graph.getNode(dest));
        NodeData temp = this.graph.getNode(this.graph.getNode(dest).getTag());
        while (temp.getKey() != src) {
            ans.add(0, temp);
            temp = this.graph.getNode(this.graph.getNode(temp.getKey()).getTag());
        }
        ans.add(0, this.graph.getNode(src));
        return ans;
    }

    /*  this function return the center of the graph.
        using help function of shortPathDist
        for all vertexes find the shortPathDist and take the max path.
        from all the path take the min value path
        this is the center of the graph */
    @Override
    public NodeData center() {
        double min = Double.MAX_VALUE;
        int ind = -1;
        for (int i : this.graph.getMapOfNode().keySet()) {
            if (i == 0) {
                shortestPathDist(i, 1);
                double maxshortpath = Double.MIN_VALUE;
                for (int j : this.graph.getMapOfNode().keySet()) {
                    if (Double.parseDouble(this.graph.getMapOfNode().get(j).getInfo()) > maxshortpath) {
                        maxshortpath = Double.parseDouble(this.graph.getMapOfNode().get(j).getInfo());
                    }
                }
                if (min > maxshortpath) {
                    min = maxshortpath;
                    ind = i;
                }
            } else {
                shortestPathDist(i, 0);
                double maxshortpath = Double.MIN_VALUE;
                for (int j : this.graph.getMapOfNode().keySet()) {
                    if (Double.parseDouble(this.graph.getMapOfNode().get(j).getInfo()) > maxshortpath) {
                        maxshortpath = Double.parseDouble(this.graph.getMapOfNode().get(j).getInfo());
                    }
                }
                if (min > maxshortpath) {
                    min = maxshortpath;
                    ind = i;
                }
            }
        }
        return this.graph.getNode(ind);
    }

    //this function return a list of the path between a list of cities
    // using greedy algorithm
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> path = new LinkedList<>();
        Point minimumstart = new Point(0, 0);
        double mindis = Integer.MAX_VALUE;
        double min = Integer.MAX_VALUE;
        int tempkey = -1;
        Node_Data tempnode = (Node_Data) cities.remove(0);
        path.add(tempnode);
        while (!cities.isEmpty()) {
            for (int s = 0; s < cities.size(); s++) {
                double dis = this.shortestPathDist(tempnode.getKey(), cities.get(s).getKey());
                if (min > dis) {
                    min = dis;
                    tempkey = cities.get(s).getKey();
                }
            }
            tempnode = (Node_Data) cities.remove(cities.indexOf(this.graph.getNode(tempkey)));
            path.add(tempnode);
            min = Integer.MAX_VALUE;
        }
        return path;
    }

    //this function save the graph on json file.
    @Override
    public boolean save(String file) {
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        JsonArray nodes = new JsonArray();
        JsonArray edges = new JsonArray();
        JsonObject graph = new JsonObject();
        for (NodeData n : this.graph.getMapOfNode().values()) {
            JsonObject o = new JsonObject();
            double location = n.getLocation().x(), y = n.getLocation().y(), z = n.getLocation().z();
            o.addProperty("pos", location + "," + y + "," + z);
            o.addProperty("id", n.getKey());
            nodes.add(o);
            for (EdgeData e : this.graph.getMapOfSrc().get(n.getKey()).values()) { //  .getE(n.getKey())) {
                JsonObject edge = new JsonObject();
                edge.addProperty("src", e.getSrc());
                edge.addProperty("w", e.getWeight());
                edge.addProperty("dest", e.getDest());
                edges.add(edge);
            }
        }
        graph.add("Nodes", nodes);
        graph.add("Edges", edges);
        File x = new File(file);
        try {
            FileWriter fileWriter = new FileWriter(x);
            fileWriter.write(json.toJson(graph));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    //this function load a graph from a json file.
    @Override
    public boolean load(String file) {
        DirectedWeightedGraph graph1 = new Directed_WeightedGraph();
        JsonObject graph;
        File f = new File(file);
        try {
            FileReader reader = new FileReader(f);
            graph = new JsonParser().parse(reader).getAsJsonObject();
            JsonArray nodes = graph.getAsJsonArray("Nodes");
            JsonArray edges = graph.getAsJsonArray("Edges");
            for (JsonElement n : nodes) {
                int id = ((JsonObject) n).get("id").getAsInt();
                double x1 = 0;
                double x2 = 0;
                double x3 = 0;
                String pos = ((JsonObject) n).get("pos").getAsString();
                String x = "";
                int counter = 0;
                for (int i = 0; i < pos.length(); i++) {
                    if (pos.charAt(i) == ',') {
                        if (counter == 0) x1 = Double.parseDouble(x);
                        if (counter == 1) x2 = Double.parseDouble(x);
                        if (counter == 2) x3 = Double.parseDouble(x);
                        counter++;
                        x = "";
                    } else {
                        x = x + pos.charAt(i);
                    }
                }
                GeoLocation location = new Geo_Location(x1, x2, x3);
                NodeData n1 = new Node_Data(id, (Geo_Location) location);
                graph1.addNode(n1);
            }
            this.graph = (Directed_WeightedGraph) graph1;
            for (JsonElement e : edges) {
                int src = ((JsonObject) e).get("src").getAsInt();
                double weight = ((JsonObject) e).get("w").getAsDouble();
                int dest = ((JsonObject) e).get("dest").getAsInt();
                EdgeData e1 = new Edge_Data((Node_Data) this.graph.getNode(src), (Node_Data) this.graph.getNode(dest), weight);
                graph1.connect(src, dest, weight);
            }

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    //a BFS algorithm.
    public boolean BFS(Directed_WeightedGraph graph, Node_Data nodeSrc) {
        Queue<Node_Data> queue = new LinkedList<>();
        queue.add(nodeSrc);
        while (!(queue.isEmpty())) {
            Node_Data TmpNode = queue.poll();
            for (int i : graph.getMapOfSrc().get(TmpNode.getKey()).keySet()) {
                if (TmpNode.getTag() == WHITE) {
                    Edge_Data e = (Edge_Data) graph.getMapOfSrc().get(TmpNode.getKey()).get(i);
                    queue.add(e.getNodeDest());
                }
            }
            TmpNode.setTag(BLACK);
        }

        for (int i : graph.getMapOfNode().keySet()) {
            if (graph.getMapOfNode().get(i).getTag() == WHITE) return false;
        }
        return true;
    }

    public boolean reversBFS(Directed_WeightedGraph graph, Node_Data nodeSrc) {
        Queue<Node_Data> queue = new LinkedList<>();
        queue.add(nodeSrc);
        while (!(queue.isEmpty())) {
            Node_Data TmpNode = queue.poll();
            for (int i : graph.getMapOfDst().get(TmpNode.getKey()).keySet()) {
                if (TmpNode.getTag() == WHITE) {
                    Edge_Data e = (Edge_Data) graph.getMapOfDst().get(TmpNode.getKey()).get(i);
                    queue.add(e.getNodeSrc());
                }
            }
            TmpNode.setTag(BLACK);
        }

        for (int i : graph.getMapOfNode().keySet()) {
            if (graph.getMapOfNode().get(i).getTag() == WHITE) return false;
        }
        return true;
    }


    //this function clean the tag of all the nodes on the graph.
    public void cleanTag(Directed_WeightedGraph g) {
        for (Integer s : this.graph.getMapOfNode().keySet()) {
            this.graph.getMapOfNode().get(s).setTag(WHITE);
        }
    }


}
