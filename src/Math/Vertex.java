package Math;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Vertex
 * ***********/
public class Vertex {

    private double x;
    private double y;
    private double z;
    private double w;


    /*************
     * Constructors
     * ***********/
    public Vertex(double xv, double yv, double zv) {
        x = xv;
        y = yv;
        z = zv;
        w = 1;
    }


    /*************
     * Getters
     * ***********/

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


    /*************
     * Methods
     * ***********/

    /**
     * isEqual
     * Compare two points
     *
     * @param p to compare
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Vertex p) {
        return p.getX() == x && p.getY() == y;
    }

    /**
     * at
     * value at position
     *
     * @param pos position
     * @return value
     */
    public double at(int pos) {
        if (pos == 0) {
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

    /**
     * clone
     * Deep copy vertex
     *
     * @return clone
     */
    public Vertex clone() {
        return new Vertex(x, y, z);
    }
}
