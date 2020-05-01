import java.awt.*;
import java.util.List;

public class Paint {
    private Scene scene;
    private View view;
    private Clip clip;
    private Line line;

    public Paint(Scene sc, View viw) {
        this.scene = sc;
        this.view = viw;
        this.clip = new Clip(this.view.getViewPortX(), this.view.getViewPortY());
    }

    public void paint(Graphics g, List<Vector> vertexList, boolean cFlag) {
        for (int i = 0; i < this.scene.getEdgesList().size(); i++) {
            Vector v0 = vertexList.get(this.scene.getEdgesList().get(i).getKey());
            Vector v1 = vertexList.get(this.scene.getEdgesList().get(i).getValue());
            this.line = new Line(v0, v1);
            if (cFlag) {
                this.line = clip.clipLine(this.line);
                if (this.line == null) {
                    continue;
                }
            }
            g.drawLine((int)this.line.getP0().getX(), (int)this.line.getP0().getY(),
                    (int)this.line.getP1().getX(), (int)this.line.getP1().getY());
        }
    }
}