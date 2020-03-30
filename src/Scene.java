import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<Vector> vertexList = new ArrayList<>();
    private List<Pair<Integer, Integer>> edgesList = new ArrayList<>();

    public Scene(String fileName) {
        try{
            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);
            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);
            //read the first line
            String line = bufferReader.readLine();
            int counter = Integer.parseInt(line);
            int i = 0;
            while (i < counter) {
                line = bufferReader.readLine();
                String[] params = line.split(" ");
                double[] res = {(double)Integer.parseInt(params[0]), (double)Integer.parseInt(params[1]), 1};
                Vector vector = new Vector(res, 3);
                this.vertexList.add(i, vector);
                i++;
            }
            line = bufferReader.readLine();
            counter = Integer.parseInt(line);
            int j = 0;
            while (j < counter) {
                line = bufferReader.readLine();
                String[] lines = line.split(" ");
                Pair<Integer, Integer> pair = new Pair(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
                this.edgesList.add(j, pair);
                j++;
            }
            //Close the buffer reader
            bufferReader.close();
            inputFile.close();

        } catch(Exception e){
            System.out.println("Error while reading file line by line");
        }
    }

    public List<Pair<Integer, Integer>> getEdgesList() {
        return this.edgesList;
    }

    public List<Vector> getVertexList() {
        return this.vertexList;
    }
    
}
