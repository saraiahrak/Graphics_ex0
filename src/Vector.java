import java.lang.Math.*;

public class Vector {

    private int dim;
    private double[] vector;

    public Vector(int d) {
        double[] v = new double[d];
        for (double elem : v) {
            elem = 0;
        }
        setDim(d);
        setVector(v);
    }

    public Vector(double[] v) {
        setVector(v);
        setDim(v.length);
    }

    public int getDim() {
        return dim;
    }

    public double[] getVector() {
        return vector;
    }

    public double getSize() {
        return Math.sqrt(this.dot(this));
    }

    public void setDim(int s) {
        this.dim = s;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public Vector cross(Vector v) {

        double[] res = new double[this.dim];

        res[0] = this.vector[1] * v.vector[2] - this.vector[2] * v.vector[1];
        res[1] = this.vector[2] * v.vector[0] - this.vector[0] * v.vector[2];

        if (this.dim > 2) {
            res[2] = this.vector[0] * v.vector[1] - this.vector[1] * v.vector[0];
        } else {
            res[2] = 0;
        }

        return new Vector(res);
    }

    public double dot(Vector v) {
        double res = 0;
        for (int i = 0; i < this.dim; i++) {
            res += this.vector[i] * v.vector[i];
        }
        return res;
    }

    public double dot(Vector v, double theta) {
        return this.getSize() * v.getSize() * Math.cos(theta);
    }

    public Vector add(Vector v) {
        Vector res = new Vector(this.dim);
        for (int i = 0; i < this.dim; i++) {
            res.vector[i] = this.vector[i] + v.vector[i];
        }
        return res;
    }

    public Vector sub(Vector v) {
        Vector res = new Vector(this.dim);
        for (int i = 0; i < this.dim; i++) {
            res.vector[i] = this.vector[i] - v.vector[i];
        }
        return res;
    }

    public Vector scalar(double scalar) {
        Vector res = new Vector(this.dim);
        for (int i = 0; i < this.dim; i++) {
            res.vector[i] = this.vector[i] * scalar;
        }
        return res;
    }

    //find the projection of this vector on vector v
    public Vector proj(Vector v) {

        double scalar = v.dot(this) / Math.pow(v.getSize(), 2);
        return v.scalar(scalar);
    }

    public boolean isEqual(Vector v) {
        if (v.getDim() != this.getDim()) {
            return false;
        }

        for (int i = 0; i < this.dim; i++) {
            if (this.vector[i] != v.vector[i]) {
                return false;
            }
        }
        return true;
    }

    public double at(int pos) {
        return this.vector[pos];
    }

    public Vector normalize() {
        return this.scalar(1 / this.getSize());
    }

    public double getTheta(Vector v) {
        return Math.acos(this.dot(v) / (this.getSize() * v.getSize()));
    }
}

