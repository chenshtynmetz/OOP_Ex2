package api;

// TODO: האם הפרטים של הצלע כמו המשקל והאינפו מושפעים מהקודקודים או שהם בפני עצמם?
public class Edge_Data implements EdgeData{
    private Node_Data src;
    private Node_Data dst;
    private double weight;
    private String info;
    private int tag;

    public Edge_Data(){
        this.src= new Node_Data();
        this.dst= new Node_Data();
        this.weight= 0.0;
        this.info= "";
        this.tag= 0;
    }

    // TODO: deep copy or not?
    public Edge_Data(Node_Data src, Node_Data dst, double weight, String info, int tag){
        this.src= src;
        this.dst= dst;
        this.weight= weight;
        this.info= info;
        this.tag= tag;
    }

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
}
