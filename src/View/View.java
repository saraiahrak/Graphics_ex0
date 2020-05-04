package View;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import Math.*;
import Utils.*;

public class View {
    private Vector position;
    private Vector lookAt;
    private Vector up;
    private int viewPortWidth;
    private int viewPortHeight;
    private double left;
    private double right;
    private double top;
    private double bottom;
    private double windowWidth;
    private double windowHeight;

    public View(String filename) {
        initView(filename);
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
