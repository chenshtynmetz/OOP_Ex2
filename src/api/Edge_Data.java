package api;

import java.awt.*;

public class Edge_Data implements EdgeData {
    // properties
    private Node_Data src;
    private Node_Data dst;
    private double weight;
    private String info;
    private int tag;
    private Point id;
    // final variable for tag
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    // defult constructor
    public Edge_Data() {
        this.src = new Node_Data();
        this.dst = new Node_Data();
        this.weight = 0.0;
        this.info = "";
        this.tag = 0;
        this.id = new Point(0, 0);
    }

    // constructors
    public Edge_Data(Node_Data src, Node_Data dst, double weight, String info, int tag) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
        this.info = info;
        this.tag = tag;
        this.id = new Point(src.getKey(), dst.getKey());
    }

    public Edge_Data(Node_Data src, Node_Data dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
        this.info = "";
        this.tag = WHITE;
        this.id = new Point(src.getKey(), dst.getKey());
    }

    // Getters and Setters
    @Override
    public int getSrc() {
        return this.src.getKey();
    }

    @Override
    public int getDest() {
        return this.dst.getKey();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public Node_Data getNodeSrc() {
        return this.src;
    }

    public Node_Data getNodeDest() {
        return this.dst;
    }

    public Point getId() {
        return this.id;
    }

    public void setId(Point p) {
        this.id.x = p.x;
        this.id.y = p.y;
    }
}
