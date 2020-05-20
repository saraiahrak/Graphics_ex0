package Math;

import World.Drawable;

import java.awt.*;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Edge
 * ***********/
public class Edge implements Drawable {

    /**
     * Edge is represented as parametric equation
     * p0- start of edge
     * p1- end of edge
     * v- vector from p0 to p1
     */
    private Vertex p0;
    private Vertex p1;
    private Vector v;

    /**
     * Constructor
     * Set p0, p1 and calculate the vector
     *
     * @param v1 start vertex
     * @param v2 end vertex
     */
    public Edge(Vertex v1, Vertex v2) {
        setVertexes(v1, v2);
        v = new Vector(p1.getX() - p0.getX(), p1.getY() - p0.getY(), p1.getZ() - p0.getZ());
    }


    /*********
     * Setters
     * *******/


    /**
     * SetVertexes
     * Set edge vertices according to x position
     *
     * @param v1 first vertex
     * @param v2 second vertex
     */
    private void setVertexes(Vertex v1, Vertex v2) {

        if (v1.getX() < v2.getX()) {
            p0 = v1;
            p1 = v2;
        } else if (v1.getX() == v2.getX()) {
            p0 = getSmall(v1, v2, "y");
            p1 = getBig(v1, v2, "y");
        } else {
            p0 = v2;
            p1 = v1;
        }
    }


    /*********
     * Getters
     * *******/

    public Vertex getP0() {
        return p0;
    }

    public Vertex getP1() {
        return p1;
    }

    public Vector getV() {
        return v;
    }


    /*********
     * Methods
     *********/


    /**
     * getSmall
     * Compare vertices according to given component
     *
     * @param v1 first vertex
     * @param v2 second vertex
     * @return the smaller vertex
     */
    private Vertex getSmall(Vertex v1, Vertex v2, String component) {
        if (component.equals("y")) {
            if (v1.getY() < v2.getY()) {
                return v1;
            }
            return v2;
        } else if (component.equals("x")) {
            if (v1.getX() < v2.getX()) {
                return v1;
            }
            return v2;
        } else {
            if (v1.getZ() < v2.getZ()) {
                return v1;
            }
            return v2;
        }
    }

    /**
     * getBig
     * Compare vertices according to given component
     *
     * @param v1 first vertex
     * @param v2 second vertex
     * @return the bigger vertex
     */
    private Vertex getBig(Vertex v1, Vertex v2, String component) {
        if (component.equals("y")) {
            if (v1.getY() > v2.getY()) {
                return v1;
            }
            return v2;
        } else if (component.equals("x")) {
            if (v1.getX() > v2.getX()) {
                return v1;
            }
            return v2;
        } else {
            if (v1.getZ() > v2.getZ()) {
                return v1;
            }
            return v2;
        }
    }


    /**
     * isPointOnLine
     * Check if given vertex is on this edge
     *
     * @param p vertex
     * @return boolean
     */
    public boolean isPointOnLine(Vertex p) {
        if (isParallelToX()) {
            return p.getY() == p0.getY() && p.getX() >= p0.getX() && p.getX() <= p1.getX();
        }

        if (isParallelToY()) {
            return p.getX() == p0.getX() && p.getY() >= p0.getY() && p.getY() <= p1.getY();
        }

        double xT = (p.getX() - p0.getX()) / v.getX();
        double yT = (p.getY() - p0.getY()) / v.getY();
        return xT == yT;
    }

    /**
     * getPointOnLine
     * calculate a vertex on this edge given a t (0 <= t <= 1)
     *
     * @param t parameter
     * @return the vertex on the edge
     */
    public Vertex getPointOnLine(double t) {
        return new Vertex(p0.getX() + t * v.getX(), p0.getY() + t * v.getY(), p0.getZ() + t * v.getZ());
    }

    /**
     * isIntersecting
     * Check if this edge and a given edge intersect
     *
     * @param other edge
     * @return boolean
     */
    public boolean isIntersecting(Edge other) {
        return !isParallel(other) && (intersectionWith(other) != null);
    }

    /**
     * intersectionWith
     * get vertex of intersection between this edge and
     * given edge, using line parametric equation
     *
     * @param other edge
     * @return intersection vertex
     */
    public Vertex intersectionWith(Edge other) {
        double t = calculateT(other);
        double s = 0;
        if (!other.isParallelToX() && !other.isParallelToY()) {
            s = calculateS(other, t);
        }

        if (t < 0 || s < 0 || t > 1 || s > 1) {
            return null;
        }

        return getPointOnLine(t);
    }

    /**
     * calculateT
     * Help method to calculate the parameter for the
     * intersection vertex
     *
     * @param other edge
     * @return t parameter
     */
    private double calculateT(Edge other) {
        double x0 = p0.getX();
        double y0 = p0.getY();
        double vx = v.getX();
        double vy = v.getY();

        double m0 = other.p0.getX();
        double k0 = other.p0.getY();
        double wx = other.v.getX();
        double wy = other.v.getY();

        double beta = ((wy * vx) - (wx * vy));
        double t = (wx * (y0 - k0)) - (wy * (x0 - m0));
        return t / beta;
    }

    /**
     * calculateS
     * Help method to calculate the parameter for the
     * intersection vertex
     *
     * @param other edge
     * @param t     calculated parameter
     * @return s parameter
     */
    private double calculateS(Edge other, double t) {
        double x0 = this.p0.getX();
        double y0 = this.p0.getY();
        double vx = this.v.getX();
        double vy = this.v.getY();

        double m0 = other.p0.getX();
        double k0 = other.p0.getY();
        double wx = other.v.getX();
        double wy = other.v.getY();

        double s = x0 + t * vx - m0;
        return s / wx;
    }

    /**
     * isParallelToY
     * check if this edge is parallel to Y axis
     *
     * @return true if parallel, false otherwise
     */
    public boolean isParallelToY() {
        return p0.getX() == p1.getX();
    }

    /**
     * isParallelToX
     * check if this edge is parallel to X axis
     *
     * @return true if parallel, false otherwise
     */
    public boolean isParallelToX() {
        return p0.getY() == p1.getY();
    }

    /**
     * isParallelToZ
     * check if this edge is parallel to Z axis
     *
     * @return true if parallel, false otherwise
     */
    public boolean isParallelToZ() {
        return p0.getZ() == p1.getZ();
    }

    /**
     * isParallel
     * check if this edge is parallel to other edge
     *
     * @param other edge
     * @return true if parallel, false otherwise
     */
    public boolean isParallel(Edge other) {
        return other.v.getX() / this.v.getX() == other.v.getY() / this.v.getY();
    }

    /**
     * isEqual
     * check if this edge is equal to other edge
     *
     * @param other edge
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Edge other) {
        return p0.isEqual(other.p0) && p1.isEqual(other.p1);
    }

    /**
     * clone
     * create deep copy of this edge
     *
     * @return clone
     */
    public Edge clone() {
        return new Edge(p0.clone(), p1.clone());
    }

    /**
     * draw
     * Drawable method
     *
     * @param g graphics
     */
    @Override
    public void draw(Graphics g) {
        g.drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(), (int) p1.getY());
    }
}
