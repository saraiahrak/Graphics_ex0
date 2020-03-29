public class Point {
    private double x;
    private double y;
    /**
     * Construct a point given x and y coordinates.
     *
     * @param x - the x coordinate
     * @param y - the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Construct a point.
     *
     * @param p - the x and y coordinates of the point
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }
    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
    /**
     * Checks if the points are equals.
     *
     * @param other - a point to compare
     *
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other.getX() == this.x) {
            if (other.getY() == this.y) {
                return true;
            }
        }
        return false;
    }
    /**
     * Calculate the distance between two points.
     *
     * @param other - a point to measure the distance to
     *
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        if (this.equals(other)) {
            return 0;
        }
        double xDistance = (this.x - other.x) * (this.x - other.x);
        double yDistance = (this.y - other.y) * (this.y - other.y);
        double result = xDistance + yDistance;
        double distance = Math.sqrt(result);
        return distance;
    }
}