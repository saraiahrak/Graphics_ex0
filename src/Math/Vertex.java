package Math;

public class Vertex {

    private double x;
    private double y;
    private double z;
    private double w;

//    public Math.Vertex(double x, double y) {
//        this.x = x;
//        this.y = y;
//        this.z = 1;
//    }
    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        w = 1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    public double getW() {
        return w;
    }

    public boolean isEqual(Vertex p) {
        return p.getX() == this.getX() && p.getY() == this.getY();
    }

    public double at(int pos) {
        if (pos == 0){
            return x;
        }
        if (pos == 1) {
            return y;
        }
        if (pos == 2) {
            return z;
        } else {
            return w;
        }
    }

    public Vertex clone() {
        return new Vertex(x, y, z);
    }
}
