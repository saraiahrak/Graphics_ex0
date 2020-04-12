public class Line {
    private Vector p0;
    private Vector p1;

    public Line(Vector v, Vector u) {
        this.p0 = v;
        this.p1 = u;
    }

    public Line(double vx, double vy, double vz, double ux, double uy, double uz) {
        this.p0 = new Vector(vx, vy, vz);
        this.p1 = new Vector(ux, uy, uz);
    }

    public Line getLine() {
        return new Line(this.p0, this.p1);
    }

    public Vector getP0() {
        return this.p0;
    }

    public Vector getP1() {
        return this.p1;
    }

    /**
     * @return true if the line is parallel to y, false otherwise
     */
    public boolean ifParallelY() {
        if (this.p0.getX() == this.p1.getX()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the point lies on the line.
     *
     * @param p - the start or end point coordinate of the other line
     *
     * @return true if the point lies on the line, otherwise false
     */
    public boolean pointOnLine(Point p) {
        if ((this.p0.getX() - 0.1 <= p.getX()) && (this.p1.getX() + 0.1 >= p.getX())
                || (this.p0.getX() + 0.1 >= p.getX()) && (this.p1.getX() - 0.1 <= p.getX())) {
            if ((this.p0.getY() - 0.1 <= p.getY()) && (this.p1.getY() + 0.1 >= p.getY())
                    || (this.p0.getY() + 0.1 >= p.getY()) && (this.p1.getY() - 0.1 <= p.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the lines are intersecting and find the intersecting point if exist.
     *
     * @param other - a line to find the intersecting point
     *
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double mThis, mOther, x, y;
        if (this.ifParallelY()) {
            x = this.p0.getX();
            mOther = (other.p1.getY() - other.p0.getY())
                    / (other.p1.getX() - other.p0.getX());
            y = mOther * (x - other.p0.getX()) + other.p0.getY();
        } else if (other.ifParallelY()) {
            x = other.p0.getX();
            mThis = (this.p1.getY() - this.p0.getY())
                    / (this.p1.getX() - this.p0.getX());
            y = mThis * (x - this.p0.getX()) + this.p0.getY();
        } else {
            mOther = (other.p1.getY() - other.p0.getY())
                    / (other.p1.getX() - other.p0.getX());
            mThis = (this.p1.getY() - this.p0.getY())
                    / (this.p1.getX() - this.p0.getX());
            if (mOther == mThis) {
                return null;
            }
            x = (mThis * this.p0.getX() - mOther * other.p0.getX() + other.p0.getY()
                    - this.p0.getY()) / (mThis - mOther);
            y = mThis * (x - this.p0.getX()) + this.p0.getY();
        }
        Point interPoint = new Point(x, y);
        if (this.pointOnLine(interPoint) && other.pointOnLine(interPoint)) {
            return interPoint;
        }
        return null;
    }
}