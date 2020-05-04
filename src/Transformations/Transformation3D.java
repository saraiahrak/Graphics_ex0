package Transformations;

import Math.*;

public class Transformation3D {

    public static Matrix translate(double dx, double dy, double dz) {
        Matrix transMat = new Matrix(4, 4);
        transMat.set(0, 3, dx);
        transMat.set(1, 3, dy);
        transMat.set(2, 3, dz);
        return transMat;
    }

//    public static Math.Matrix scale(double sF, double sY) {
//        Math.Matrix scaleMat = new Math.Matrix(4, 4);
//        scaleMat.set(0, 0, sF);
//        scaleMat.set(1, 1, sY);
//        scaleMat.set(2, 2, sF);
//        return scaleMat;
//    }


    public static Matrix scale(double sx, double sy, double sz) {
        Matrix scaleMat = new Matrix(4, 4);
        scaleMat.set(0, 0, sx);
        scaleMat.set(1, 1, sy);
        scaleMat.set(2, 2, sz);
        return scaleMat;
    }

    public static Matrix scale(double sF) {
        Matrix scaleMat = new Matrix(4, 4);
        scaleMat.set(0, 0, sF);
        scaleMat.set(1, 1, sF);
        scaleMat.set(2, 2, sF);
        return scaleMat;
    }

    public static Matrix rotate(double theta) {
        Matrix rotateMat = new Matrix(4, 4);
        rotateMat.set(0, 0, Math.cos(theta));
        rotateMat.set(2, 2, Math.cos(theta));
        rotateMat.set(0, 2, (-Math.sin(theta)));
        rotateMat.set(2, 0, Math.sin(theta));
        return rotateMat;
    }

}



