package Math;

import World.*;
import java.awt.*;

public class Edge implements Drawable {

    private Vertex p0;
    private Vertex p1;
    private Vector v;

    public Edge(Vertex v1, Vertex v2) {
        setVertexes(v1, v2);
        v = new Vector(p1.getX() - p0.getX(), p1.getY() - p0.getY(), p1.getZ() - p0.getZ());
    }

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


    public Edge(Vertex a, Vector vector) {
        p0 = a;
        v = vector;
        p1 = new Vertex(p0.getX() + vector.getX(), p0.getY() + vector.getY(), p0.getZ() + vector.getZ());
    }


    public boolean isPointOnLine(Vector p) {
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

    public Vertex getPointOnLine(double t) {
        return new Vertex(p0.getX() + t * v.getX(), p0.getY() + t * v.getY(), p0.getZ() + t * v.getZ());
    }

    public boolean isIntersecting(Edge other) {
        return !isParallel(other) && (intersectionWith(other) != null);
    }

    public Vertex intersectionWith(Edge other) {
        double t = calculateT(other);
        double s = 0;
        if (!other.isParallelToX() && !other.isParallelToY()) {
            s = calculateS(other, t);
        }

        if (t < 0 || s < 0 || t > 1 || s > 1) {
            return null;
        }

        Vertex intersection = this.getPointOnLine(t);
        //Math.Vertex inter2 = other.getPointOnLine(s);

//        if (inter2.isEqual(intersection)) {
//            return inter2;
//        }

        return intersection;
    }

    private double calculateT(Edge other) {
        double x0 = this.p0.getX();
        double y0 = this.p0.getY();
        double vx = this.v.getX();
        double vy = this.v.getY();

        double m0 = other.p0.getX();
        double k0 = other.p0.getY();
        double wx = other.v.getX();
        double wy = other.v.getY();

        double beta = ((wy * vx) - (wx * vy));
        double t = (wx * (y0 - k0)) - (wy * (x0 - m0));
        return t / beta;
    }

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

    public Vertex getP0() {
        return p0;
    }

    public Vertex getP1() {
        return p1;
    }

    public Vector getV() {
        return v;
    }

    public boolean isParallelToY() {
        return p0.getX() == p1.getX();
    }

    public boolean isParallelToX() {
        return p0.getY() == p1.getY();
    }

    public boolean isParallelToZ() {
        return p0.getZ() == p1.getZ();
    }

    public boolean isParallel(Edge other) {
        return other.v.getX() / this.v.getX() == other.v.getY() / this.v.getY();
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(), (int) p1.getY());
    }

    public boolean isEqual(Edge other) {
        return this == other;
    }

    public Edge clone() {
        return new Edge(p0.clone(), p1.clone());
    }
}
