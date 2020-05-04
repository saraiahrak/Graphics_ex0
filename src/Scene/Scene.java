package Scene;
import Math.*;
import Utils.*;
import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scene {
    public static List<Vertex> vertexList;
    public static List<Edge> EL;
    public static List<Pair<Integer, Integer>> edgesMap;

    public Scene(String filename) {
        vertexList = new ArrayList<>();
        EL = new ArrayList<>();
        edgesMap = new ArrayList<>();
        initScene(filename);
    }

    private void initScene(String filename) {
        ArrayList<String> lines = Reader.readLines(filename);
        int vertexEnd = Integer.parseInt(lines.get(0));
        int edgeStart = vertexEnd + 2;

        List<String> v = lines.subList(1, vertexEnd + 1);
        List<String> e = lines.subList(edgeStart, lines.size());

        ArrayList<Vertex> vertexes = createVertexes(v);
        setVertexList(vertexes);

        ArrayList<Edge> edges = createEdges(e);
        setEdgeList(edges);
    }

    private ArrayList<Vertex> createVertexes(List<String> arr) {
        ArrayList<Vertex> vertices = new ArrayList<>();

        for (String s : arr) {
            String[] point = s.split(" ");
            double x = Double.parseDouble(point[0]);
            double y = Double.parseDouble(point[1]);
            double z = Double.parseDouble(point[2]);

            vertices.add(new Vertex(x, y, z));
        }

        return vertices;
    }

    private ArrayList<Edge> createEdges(List<String> lines) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (String s : lines) {
            String[] edge = s.split(" ");
            int first = Integer.parseInt(edge[0]);
            int second = Integer.parseInt(edge[1]);

            edgesMap.add(new Pair<Integer, Integer>(first,second));
            edges.add(new Edge(vertexList.get(first), vertexList.get(second)));
        }

        return edges;
    }

    public void setEdgeList(ArrayList<Edge> edgeList) {
        EL = edgeList;
    }

    public void setVertexList(ArrayList<Vertex> VL) {
        vertexList = VL;
    }
    public ArrayList<Edge> manipulateEdge(ArrayList<Vertex> VL) {
        ArrayList<Edge> tmp = new ArrayList<>();
        for (Pair<Integer, Integer> pair : edgesMap) {
            Vertex v0 = VL.get(pair.getKey());
            Vertex v1 = VL.get(pair.getValue());
            tmp.add(new Edge(v0, v1));
        }

        return tmp;
    }

    public List<Edge> getEdgesList() {
        return EL;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEL() {
        return EL;
    }

    public List<Pair<Integer, Integer>> getEdgesMap() {
        return edgesMap;
    }

    public static ArrayList<Edge> apply(Matrix transformation) {
        ArrayList<Vertex> transformed = applyToVertex(transformation);

        ArrayList<Edge> edges = new ArrayList<>();
        for (Pair<Integer, Integer> pair : edgesMap) {
            Vertex v0 = transformed.get(pair.getKey());
            Vertex v1 = transformed.get(pair.getValue());
            edges.add(new Edge(v0, v1));
        }

        return edges;

    }

    public static ArrayList<Vertex> applyToVertex(Matrix transformation) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        int i = 0;
        while (i < vertexList.size()) {
            vertices.add(transformation.mult(vertexList.get(i)));
            i++;
        }
        return vertices;
    }
}
