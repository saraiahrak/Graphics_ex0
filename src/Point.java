public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean equals(Point other) {
        if (other.x == this.x) {
            if (other.y == this.y) {
                return true;
            }
        }
        return false;
    }

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