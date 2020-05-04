package View;

import Transformations.Transformations;

import java.util.ArrayList;

import Math.*;
import Utils.*;

public class View {
    private static Vector position;
    private static Vector lookAt;
    private static Vector up;
    public static int viewPortWidth;
    public static int viewPortHeight;
    public static double left;
    public static double right;
    public static double top;
    public static double bottom;
    public static double windowWidth;
    public static double windowHeight;
    public static String rotation;
    private Matrix TT, VM1, VM2, AT, CT, Pro;
    private Vector xAxis;
    private Vector yAxis;
    private Vector zAxis;


    public View(String filename) {
        initView(filename);
        initTransformationMatrix();
        initAxes();
        initVM1();
        initVM2();
        rotation = "z";
    }


    public Matrix getTT() {
        return TT;
    }

    public Matrix getAT() {
        return AT;
    }

    public Matrix getCT() {
        return CT;
    }

    public Matrix getVM1() {
        return VM1;
    }

    public Matrix getVM2() {
        return VM2;
    }

    public String getRotation() {
        return rotation;
    }

    public Vector getxAxis() {
        return xAxis;
    }

    public Vector getyAxis() {
        return yAxis;
    }

    public Vector getzAxis() {
        return zAxis;
    }

    public Matrix getPro() {
        return Pro;
    }

    public void setTT(Matrix tt) {
        TT = tt;
    }

    public void setVM1(Matrix vm1) {
        VM1 = vm1;
    }

    public void setAT(Matrix at) {
        AT = at;
    }

    public void setCT(Matrix ct) {
        CT = ct;
    }

    public void setPro(Matrix pro) {
        Pro = pro;
    }

    public void initAxes() {
        // form viewing coordinate system
        // Z-Axis
        Vector z = position.sub(lookAt);
        zAxis = z.scalar(1 / z.getSize());
        // X-Axis
        Vector x = up.cross(zAxis);
        xAxis = x.scalar(1 / x.getSize());
        // Y-Axis
        yAxis = zAxis.cross(xAxis);
    }


    public void initVM1() {
        //view matrix
        Matrix t1 = Transformations.translate(-position.getX(),
                -position.getY(), -position.getZ());
        Matrix r = new Matrix(new double[][]{
                {xAxis.getX(), xAxis.getY(), xAxis.getZ(), 0},
                {yAxis.getX(), yAxis.getY(), yAxis.getZ(), 0},
                {zAxis.getX(), zAxis.getY(), zAxis.getZ(), 0},
                {0, 0, 0, 1}
        });

        VM1 = r.mult(t1);
    }

    public void initVM2() {

        double sF = viewPortWidth / windowWidth;
        double sY = viewPortHeight / windowHeight;

        Matrix s = Transformations.scale(sF, -sY, 1);
        Matrix t1 = Transformations.translate(-(left + (windowWidth / 2)),
                -(bottom + (windowHeight / 2)), 0);
        Matrix t2 = Transformations.translate((double) (viewPortWidth / 2) + 20, (double) (viewPortHeight
                / 2) + 20, 0);

        VM2 = t2.mult(s).mult(t1);
    }


    public void initTransformationMatrix() {
        //total transformations matrix
        setTT(new Matrix(4, 4));
        //accumulated transformations matrix
        setAT(new Matrix(4, 4));
        //current transformation matrix
        setCT(new Matrix(4, 4));
        //projection matrix
        setPro(new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        }));
    }


    public void setRotation(String axis) {
//        switch (axis) {
//            case "z":
//                rotation = zAxis;
//                break;
//            case "x":
//                rotation = xAxis;
//                break;
//            case "y":
//                rotation = yAxis;
//            default:
//                rotation = zAxis;
//        }
        rotation = axis;
    }

    private void initView(String filename) {
        ArrayList<String> lines = Reader.readLines(filename);
        setComponents(lines);
    }

    private void setComponents(ArrayList<String> lines) {
        for (String line : lines) {
            String[] tokens = line.split(" ");

            switch (tokens[0]) {
                case "Position":
                    position = new Vector(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;
                case "LookAt":
                    lookAt = new Vector(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;
                case "Up":
                    up = new Vector(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;
                case "Window":
                    left = Double.parseDouble(tokens[1]);
                    right = Double.parseDouble(tokens[2]);
                    bottom = Double.parseDouble(tokens[3]);
                    top = Double.parseDouble(tokens[4]);
                    setBound();
                    break;
                case "Viewport":
                    viewPortWidth = Integer.parseInt(tokens[1]);
                    viewPortHeight = Integer.parseInt(tokens[2]);
                    break;
                default:

            }
        }
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getLookAt() {
        return lookAt;
    }

    public Vector getUp() {
        return up;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getBottom() {
        return bottom;
    }

    public double getTop() {
        return top;
    }

    public int getViewPortWidth() {
        return viewPortWidth;
    }

    public int getViewPortHeight() {
        return viewPortHeight;
    }

    public void setWindowWidth(double width) {
        windowWidth = width;
    }

    public void setWindowHeight(double height) {
        windowHeight = height;
    }

    public void setBound() {
        windowWidth = Utils.getAbsoluteValue(left) + Utils.getAbsoluteValue(right);
        windowHeight = Utils.getAbsoluteValue(top) + Utils.getAbsoluteValue(bottom);
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }
}
