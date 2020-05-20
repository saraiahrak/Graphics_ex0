package Transformations;

import Math.Matrix;
import View.View;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/***********************
 * Class Transformations
 * *********************/

public class Transformations {

    /**
     * translate
     * create translate matrix
     *
     * @param dx dx
     * @param dy dy
     * @param dz dz
     * @return translate matrix
     */
    public static Matrix translate(double dx, double dy, double dz) {
        Matrix transMat = new Matrix(4, 4);
        transMat.set(0, 3, dx);
        transMat.set(1, 3, dy);
        transMat.set(2, 3, dz);
        return transMat;
    }

    /**
     * scale
     * create scale matrix
     *
     * @param sx sx
     * @param sy sy
     * @param sz sz
     * @return scale matrix
     */
    public static Matrix scale(double sx, double sy, double sz) {
        Matrix scaleMat = new Matrix(4, 4);
        scaleMat.set(0, 0, sx);
        scaleMat.set(1, 1, sy);
        scaleMat.set(2, 2, sz);
        return scaleMat;
    }


    /**
     * scale
     * create scale matrix
     *
     * @param sF scale factor
     * @return scale matrix
     */
    public static Matrix scale(double sF) {
        Matrix scaleMat = new Matrix(4, 4);
        scaleMat.set(0, 0, sF);
        scaleMat.set(1, 1, sF);
        scaleMat.set(2, 2, sF);
        return scaleMat;
    }


    /**
     * rotate
     * create rotate matrix relative to axis
     *
     * @param theta angle
     * @return rotate matrix
     */
    public static Matrix rotate(double theta) {
        String axis = View.rotation;
        Matrix rotate;
        switch (axis) {
            case "x":
                rotate = new Matrix(new double[][]{
                        {1, 0, 0, 0},
                        {0, Math.cos(theta), -Math.sin(theta), 0},
                        {0, Math.sin(theta), Math.cos(theta), 0},
                        {0, 0, 0, 1}
                });
                break;
            case "y":
                rotate = new Matrix(new double[][]{
                        {Math.cos(theta), 0, Math.sin(theta), 0},
                        {0, 1, 0, 0},
                        {-Math.sin(theta), 0, Math.cos(theta), 0},
                        {0, 0, 0, 1}
                });
                break;
            case "z":
                rotate = new Matrix(new double[][]{
                        {Math.cos(theta), -Math.sin(theta), 0, 0},
                        {Math.sin(theta), Math.cos(theta), 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                });
                break;
            default:
                rotate = new Matrix(new double[][]{
                        {Math.cos(theta), -Math.sin(theta), 0, 0},
                        {Math.sin(theta), Math.cos(theta), 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                });
                break;

        }
        return rotate;
    }

}



