package Math;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Point
 * ***********/
public class Point {
    private double x;
    private double y;


    /*************
     * Constructors
     * ***********/

    public Point(double xp, double yp) {
        x = xp;
        y = yp;
    }

    public Point(Point p) {
        x = p.getX();
        y = p.getY();
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


    /*************
     * Methods
     * ***********/

    /**
     * isEqual
     * Compare two points
     *
     * @param other to compare
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Point other) {
        return x == other.x && y == other.y;
    }

    /**
     * distance
     * Calculate distance between two points
     *
     * @param other to find distance
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        if (this.isEqual(other)) {
            return 0;
        }

        double dx = (x - other.x) * (x - other.x);
        double dy = (y - other.y) * (y - other.y);

        return Math.sqrt(dx + dy);
    }
}