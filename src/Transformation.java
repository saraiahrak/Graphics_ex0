public class Transformation {
    private String type;

    public Transformation() {};

    public Matrix translate(double dx, double dy) {
        Matrix transMat = new Matrix(3,3);
        transMat.set(0, 2, dx);
        transMat.set(1, 2, dy);
        return transMat;
    }

    public Matrix scale(double sx, double sy) {
        Matrix scaleMat = new Matrix(3,3);
        scaleMat.set(0, 0, sx);
        scaleMat.set(1, 1, sy);
        return scaleMat;
    }

    public Matrix rotate(double theta) {
        Matrix rotateMat = new Matrix(3,3);
        rotateMat.set(0, 0, Math.cos(theta));
        rotateMat.set(1, 1, Math.cos(theta));
        rotateMat.set(1, 0, (-Math.sin(theta)));
        rotateMat.set(0, 1, Math.sin(theta));
        return rotateMat;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String t) {
        this.type = t;
    }
}


