package api;

public class Node_Data implements NodeData{
    private int key;
    private Geo_Location location;
    private double weight= 0;
    private String info;
    private int tag;

    public Node_Data(){
        this.key = 0;
        this.location= new Geo_Location();
        this.info= "";
        this.tag= 0;
    }

    public Node_Data(int key, Geo_Location g, String info, int tag){
        this.key= key;
        this.location= g;
//        this.weight= w;
        this.info= info;
        this.tag= tag;
    }

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

    @Override
    public void setTag(int t) {
        this.tag= t;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location= (Geo_Location) p;
    }
}