package api;

public class Geo_Location implements GeoLocation {
    // properties
    private double x;
    private double y;
    private double z;

    // constractor
    public Geo_Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // defult constractor
    public Geo_Location() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    // Getters
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    // distance methood
    @Override
    public double distance(api.GeoLocation g) {
        double minusX = this.x - g.x();
        double minusY = this.y - g.y();
        double minusZ = this.z - g.z();
        double powX = Math.pow(minusX, 2);
        double powY = Math.pow(minusY, 2);
        double powZ = Math.pow(minusZ, 2);
        double distance = Math.sqrt(powX + powY + powZ);
        return distance;
    }

}
