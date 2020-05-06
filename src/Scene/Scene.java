package Scene;

import Math.*;
import Utils.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*************
 * Class Scene
 * ***********/

public class Scene {
    public static List<Vertex> vertexList;
    public static List<Edge> EL;
    public static List<Pair<Integer, Integer>> edgesMap;


    /*************
     * Constructor
     * ***********/
    public Scene(String filename) {
        vertexList = new ArrayList<>();
        EL = new ArrayList<>();
        edgesMap = new ArrayList<>();
        initScene(filename);
    }


    /*************
     * Getters
     * ***********/
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


    /*************
     * Setters
     * ***********/

    public void setEdgeList(ArrayList<Edge> edgeList) {
        EL = edgeList;
    }

    public void setVertexList(ArrayList<Vertex> VL) {
        vertexList = VL;
    }


    /*************
     * Methods
     * ***********/

    /**
     * initScene
     * Parse scene file, initialize edges and vertices
     *
     * @param filename scene file name
     */
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

    /**
     * createVertexes
     * Create vertices from string vertex coordinates array
     *
     * @param coordinates string coordinates
     * @return vertices array
     */
    private ArrayList<Vertex> createVertexes(List<String> coordinates) {
        ArrayList<Vertex> vertices = new ArrayList<>();

        for (String s : coordinates) {
            String[] vertex = s.split(" ");
            double x = Double.parseDouble(vertex[0]);
            double y = Double.parseDouble(vertex[1]);
            double z = Double.parseDouble(vertex[2]);

            vertices.add(new Vertex(x, y, z));
        }

        return vertices;
    }

    /**
     * createEdges
     * Create edges from string edges array, initialize edges map
     *
     * @param lines string edges
     * @return edges array
     */
    private ArrayList<Edge> createEdges(List<String> lines) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (String s : lines) {
            String[] edge = s.split(" ");
            int first = Integer.parseInt(edge[0]);
            int second = Integer.parseInt(edge[1]);

            edgesMap.add(new Pair<Integer, Integer>(first, second));
            edges.add(new Edge(vertexList.get(first), vertexList.get(second)));
        }

        return edges;
    }

    /**
     * apply
     * Get transformed edges list
     *
     * @param transformation matrix
     * @return transformed edges list
     */
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

    /**
     * applyToVertex
     * Apply transformation to this vertex list
     *
     * @param transformation matrix
     * @return transformed vertex list
     */
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
