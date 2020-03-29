import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;

public class View {
    private Vector origin;
    private double direction;
    private Pair<Double, Double> size;
    private Pair<Integer, Integer> resolution;

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
                    case "Origin":
                        double[] res = {Integer.parseInt(params[1]), Integer.parseInt(params[2]), 1};
                        this.origin = new Vector(res, 3);
                        break;
                    case "Direction":
                        this.direction = Integer.parseInt(params[1]);
                        break;
                    case "Size":
                        double xSize = Integer.parseInt(params[1]);
                        double ySize = Integer.parseInt(params[2]);
                        this.size = new Pair<>(xSize, ySize);
                        break;
                    case "Resolution":
                        int xResolution = Integer.parseInt(params[1]);
                        int yResolution = Integer.parseInt(params[2]);
                        this.resolution = new Pair<>(xResolution, yResolution);
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

    public Vector getOrigin() {
        return this.origin;
    }

    public double getSizeX() {
        return this.size.getKey();
    }

    public double getSizeY() {
        return this.size.getValue();
    }

    public double getDirection() {
        return this.direction;
    }
}
