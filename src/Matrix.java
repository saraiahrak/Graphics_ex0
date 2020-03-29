public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;

    public Matrix(double[][] mat) {
        this.matrix = mat;
        this.rows = mat.length;
        this.cols = mat[0].length;
    }

    public Matrix(int row, int col) {
        this.rows = row;
        this.cols = col;
        this.matrix = new double[row][col];
        //initialize to be the identity Matrix
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

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix mult(Matrix m) {
        double[][] res = new double[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                for (int k = 0; k < m.rows; k++) {
                    res[i][j] += this.at(i, k) * m.at(k, j);
                }
            }
        }

        return new Matrix(res);
    }

    public Vector mult(Vector v) {
        double[] res = new double[this.rows];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                res[i] += this.at(i, j) * v.at(j);
            }
        }
        return new Vector(res, res.length);
    }

    public Matrix add(Matrix m) {
        double[][] mat = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                mat[i][j] = m.at(i, j) + this.at(i, j);
            }
        }
        return new Matrix(mat);
    }

    public Matrix sub(Matrix m) {
        double[][] res = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                res[i][j] = this.at(i, j) - m.at(i, j);
            }
        }
        return new Matrix(res);
    }

    public Matrix scalar(double scalar) {
        double[][] res = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                res[i][j] = this.at(i, j) * scalar;
            }
        }
        return new Matrix(res);
    }

    public boolean isEquals(Matrix m) {
        if (this.cols != m.cols || this.rows != m.rows) {
            return false;
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.at(i, j) != m.at(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public double at(int row, int col) {
        return this.matrix[row][col];
    }

    public void set(int row, int col, double num) {
        this.matrix[row][col] = num;
    }
}
