import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;

public class View {
    private Vector position;
    private Vector lookAt;
    private Vector up;
    private double[] window;
    private Pair<Integer, Integer> viewPort;
    private double windowWidth, windowHeight;

    public View(String fileName) {
        try{
            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);
            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);
            //read the first line
            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] params = line.split(" ");
                switch (params[0]) {
                    case "Position":
                        double[] posArr = {Double.parseDouble(params[1]),
                                Double.parseDouble(params[2]), Double.parseDouble(params[3]), 1};
                        this.position = new Vector(posArr, 4);
                        break;
                    case "LookAt":
                        double[] lookArr = {Double.parseDouble(params[1]),
                                Double.parseDouble(params[2]), Double.parseDouble(params[3]), 1};
                        this.lookAt = new Vector(lookArr, 4);
                        break;
                    case "Up":
                        double[] upArr = {Double.parseDouble(params[1]),
                                Double.parseDouble(params[2]), Double.parseDouble(params[3]), 1};
                        this.up = new Vector(upArr, 4);
                        break;
                    case "Window":
                        this.window = new double[]{Double.parseDouble(params[1]),
                                Double.parseDouble(params[2]), Double.parseDouble(params[3]),
                                Double.parseDouble(params[4])};
                        setBound();
                        break;
                    case "Viewport":
                        int xViewport = Integer.parseInt(params[1]);
                        int yViewport = Integer.parseInt(params[2]);
                        this.viewPort = new Pair<>(xViewport, yViewport);
                        break;
                }
            }
            //Close the buffer reader
            bufferReader.close();
            inputFile.close();
        } catch(Exception e){
            System.out.println("Error while reading file line by line");
        }
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector getLookAt() {
        return this.lookAt;
    }

    public Vector getUp() {
        return this.up;
    }

    public double leftBound() {
        return this.window[0];
    }

    public double rightBound() {
        return this.window[1];
    }

    public double bottomBound() {
        return this.window[2];
    }

    public double topBound() {
        return this.window[3];
    }

    public int getViewPortX() {
        return this.viewPort.getKey();
    }

    public int getViewPortY() {
        return this.viewPort.getValue();
    }

    public double getAbsoluteValue(double num) {
        if (num < 0) {
            return (-1) * num;
        }
        return num;
    }

    public void setBound() {
        this.windowWidth = getAbsoluteValue(leftBound()) + getAbsoluteValue(rightBound());
        this.windowHeight = getAbsoluteValue(topBound()) + getAbsoluteValue(bottomBound());
    }

    public double getWindowWidth() {
        return this.windowWidth;
    }

    public double getWindowHeight() {
        return this.windowHeight;
    }
}
