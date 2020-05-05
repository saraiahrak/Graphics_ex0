package Math;

import java.util.Arrays;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Matrix
 * ***********/
public class Matrix {
    private int rows;
    private int cols;
    private double[][] matrix;


    /*************
     * Constructors
     * ***********/

    public Matrix(double[][] mat) {
        matrix = mat;
        rows = mat.length;
        cols = mat[0].length;
    }

    public Matrix(int row, int col) {
        rows = row;
        cols = col;
        matrix = new double[row][col];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (i == j) {
                    this.matrix[i][j] = 1;
                } else {
                    this.matrix[i][j] = 0;
                }
            }
        }
    }

    public Matrix(Vector vecX, Vector vecY, Vector vecZ) {
        rows = 4;
        cols = 4;
        matrix = new double[4][4];
        matrix[0][0] = vecX.getX();
        matrix[0][1] = vecX.getY();
        matrix[0][2] = vecX.getZ();
        matrix[1][0] = vecY.getX();
        matrix[1][1] = vecY.getY();
        matrix[1][2] = vecY.getZ();
        matrix[2][0] = vecZ.getX();
        matrix[2][1] = vecZ.getY();
        matrix[2][2] = vecZ.getZ();
        matrix[3] = new double[]{0, 0, 0, 1};
    }


    /*************
     * Setters
     * ***********/
    public void setCols(int col) {
        cols = col;
    }

    public void setMatrix(double[][] mat) {
        matrix = mat;
    }

    public void setRows(int row) {
        rows = row;
    }


    /*************
     * Getters
     * ***********/
    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double[][] getMatrix() {
        return matrix;
    }


    /*************
     * Methods
     * ***********/

    /**
     * mult
     * Multiply matrices
     *
     * @param m matrix
     * @return the result matrix
     */
    public Matrix mult(Matrix m) {
        double[][] res = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                for (int k = 0; k < m.rows; k++) {
                    res[i][j] += this.at(i, k) * m.at(k, j);
                }
            }
        }
        return new Matrix(res);
    }

    /**
     * Mult
     * Apply matrix to vertex by multiply
     *
     * @param v vertex
     * @return result vertex
     */
    public Vertex mult(Vertex v) {
        double[] res = new double[rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i] += this.at(i, j) * v.at(j);
            }
        }
        return new Vertex(res[0], res[1], res[2]);
    }

    /**
     * Add
     * Add two matrices
     *
     * @param m matrix
     * @return addition matrix
     */
    public Matrix add(Matrix m) {
        double[][] mat = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = m.at(i, j) + this.at(i, j);
            }
        }
        return new Matrix(mat);
    }

    /**
     * Sub
     * Subtract m matrix from this matrix
     *
     * @param m matrix
     * @return subtraction matrix
     */
    public Matrix sub(Matrix m) {
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = this.at(i, j) - m.at(i, j);
            }
        }
        return new Matrix(res);
    }

    /**
     * scalar
     * Multiply matrix by scalar
     *
     * @param scalar scalar
     * @return result matrix
     */
    public Matrix scalar(double scalar) {
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = this.at(i, j) * scalar;
            }
        }
        return new Matrix(res);
    }

    /**
     * isEquals
     * Compare two matrices
     *
     * @param m matrix
     * @return true if equal, false otherwise
     */
    public boolean isEquals(Matrix m) {
        if (cols != m.cols || rows != m.rows) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.at(i, j) != m.at(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * at
     * Return value of matrix at given position
     *
     * @param row row
     * @param col row
     * @return value
     */
    public double at(int row, int col) {
        return matrix[row][col];
    }

    /**
     * set
     * Set given value at given position
     *
     * @param row row position
     * @param col col position
     * @param num set value
     */
    public void set(int row, int col, double num) {
        matrix[row][col] = num;
    }

    /**
     * clone
     * Deep copy this matrix
     *
     * @return the clone
     */
    public Matrix clone() {
        double[][] matCopy = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            double[] rowCopy = Arrays.copyOf(matrix[i], matrix[i].length);
            matCopy[i] = rowCopy;
        }
        return new Matrix(matCopy);
    }
}