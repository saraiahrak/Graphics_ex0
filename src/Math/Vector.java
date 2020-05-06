package Math;

import java.util.Arrays;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Vector
 * ***********/
public class Vector {

    private double[] vector;
    private int dim;


    /*************
     * Constructors
     * ***********/

    public Vector(double[] res) {
        vector = res;
        dim = res.length;
    }

    public Vector(int num) {
        vector = new double[]{1, 1, 1, 1};
        dim = num;
    }

    public Vector(double x, double y, double z) {
        vector = new double[]{x, y, z, 1};
        dim = 4;
    }


    /*************
     * Getters
     * ***********/
    public double[] getVector() {
        return vector;
    }

    public double getX() {
        return vector[0];
    }

    public double getY() {
        return vector[1];
    }

    public double getZ() {
        return vector[2];
    }

    public double getDim() {
        return dim;
    }

    public double getSize() {
        return Math.sqrt(this.dot(this));
    }


    /*************
     * Methods
     * ***********/

    /**
     * cross
     * Calculate cross product with v vector
     *
     * @param v other vector
     * @return cross product vector
     */
    public Vector cross(Vector v) {
        double[] res = new double[dim];

        res[0] = vector[1] * v.vector[2] - vector[2] * v.vector[1];
        res[1] = vector[2] * v.vector[0] - vector[0] * v.vector[2];
        res[2] = vector[0] * v.vector[1] - vector[1] * v.vector[0];
        res[3] = 1;

        return new Vector(res);
    }

    /**
     * dot
     * Calculate dot product with v vector
     *
     * @param v other vector
     * @return dot product
     */
    public double dot(Vector v) {
        double res = 0;
        for (int i = 0; i < dim; i++) {
            res += vector[i] * v.vector[i];
        }
        return res;
    }

    /**
     * dot
     * Calculate dot product with v vector
     *
     * @param v     other vector
     * @param theta angle
     * @return dot product
     */
    public double dot(Vector v, double theta) {
        return getSize() * v.getSize() * Math.cos(theta);
    }

    /**
     * add
     * Calculate addition of two vectors
     *
     * @param v other vector
     * @return addition vector
     */
    public Vector add(Vector v) {
        Vector res = new Vector(dim);
        for (int i = 0; i < dim; i++) {
            res.vector[i] = vector[i] + v.vector[i];
        }
        return res;
    }

    /**
     * sub
     * Calculate subtraction of two vectors
     *
     * @param v other vector
     * @return subtraction vector
     */
    public Vector sub(Vector v) {
        Vector res = new Vector(dim);
        for (int i = 0; i < dim; i++) {
            res.vector[i] = vector[i] - v.vector[i];
        }
        return res;
    }

    /**
     * sub
     * multiply vector by scalar
     *
     * @param scalar scalar
     * @return vector
     */
    public Vector scalar(double scalar) {
        Vector res = new Vector(dim);
        for (int i = 0; i < dim; i++) {
            res.vector[i] = this.at(i) * scalar;
        }
        return res;
    }

    /**
     * proj
     * find the projection of this vector on vector v
     *
     * @param v vector
     * @return projection vector
     */
    public Vector proj(Vector v) {
        double scalar = v.dot(this) / Math.pow(v.getSize(), 2);
        return v.scalar(scalar);
    }

    /**
     * isEqual
     * Compare two vectors
     *
     * @param v to compare
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Vector v) {
        if (v.getDim() != getDim()) {
            return false;
        }

        for (int i = 0; i < dim; i++) {
            if (this.at(i) != v.vector[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * at
     * Return value of vector at given position
     *
     * @param pos position
     * @return value
     */
    public double at(int pos) {
        return vector[pos];
    }

    /**
     * normalize
     * normalize vector
     *
     * @return normalized vector
     */
    public Vector normalize() {
        return scalar(1 / getSize());
    }

    /**
     * getTheta
     * Calculate angle between two vectors
     *
     * @return angle
     */
    public double getTheta(Vector v) {
        return Math.acos(this.dot(v) / (getSize() * v.getSize()));
    }

    /**
     * clone
     * Deep copy this vector
     *
     * @return the clone
     */
    public Vector clone() {
        double[] copy = Arrays.copyOf(vector, vector.length);
        return new Vector(copy);
    }
}