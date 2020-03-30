import java.awt.*;
import java.util.List;

public class Paint {
    private Scene scene;

    public Paint(Scene sc) {
        this.scene = sc;
    }

    public void paint(Graphics g, List<Vector> vertexList, boolean cFlag) {
        for (int i = 0; i < this.scene.getEdgesList().size(); i++) {
            Vector v = vertexList.get(this.scene.getEdgesList().get(i).getKey());
            Vector u = vertexList.get(this.scene.getEdgesList().get(i).getValue());
            if (cFlag) {
                //clliping
            }
            g.drawLine((int)v.getX(), (int)v.getY(), (int)u.getX(), (int)u.getY());
        }
    }
}