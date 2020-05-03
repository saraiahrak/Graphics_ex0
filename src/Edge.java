
import java.awt.*;
import java.util.ArrayList;

public class Edge implements Drawable {

    private Vertex p0;
    private Vertex p1;
    private Vector v;

    public Edge(Vertex v1, Vertex v2) {
        p0 = v1;
        p1 = v2;
        v = new Vector(p1.getX() - p0.getX(), p1.getY() - p0.getY(), p1.getZ() - p0.getZ());
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
        double s = calculateS(other, t);

        if (t < 0 || s < 0 || t > 1 || s > 1) {
            return null;
        }

        Vertex intersection = this.getPointOnLine(t);
        Vertex inter2 = other.getPointOnLine(s);

        if (inter2.isEqual(intersection)) {
            return inter2;
        }

        return null;
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


}
