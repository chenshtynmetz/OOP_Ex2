package api;

public class Node_Data implements NodeData{
    // properties
    private int key;
    private Geo_Location location;
    private double weight= 0;
    private String info;
    private int tag;
    // final variable for tag
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    // defult constructor
    public Node_Data(){
        this.key = 0;
        this.location= new Geo_Location();
        this.info= "";
        this.tag= WHITE;
    }

    // constructors
    public Node_Data(int key, Geo_Location g, String info, int tag){
        this.key= key;
        this.location= g;
        this.info= info;
        this.tag= tag;
    }
    public Node_Data(int key, Geo_Location g){
        this.key= key;
        this.location= g;
        this.info= "";
        this.tag= WHITE;
    }

        // Getters
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return (GeoLocation) this.location;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {
        this.weight= w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info= s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    // Setters
    @Override
    public void setTag(int t) {
        this.tag= t;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location= (Geo_Location) p;
    }
}
