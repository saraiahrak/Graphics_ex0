import java.awt.*;
import java.util.List;

public class Paint {
    private Scene scene;
    private View view;
    private Clip clip;

    public Paint(Scene sc, View viw) {
        this.scene = sc;
        this.view = viw;
        this.clip = new Clip(this.view.getViewPortWidth(), this.view.getViewPortHeight());
    }

    public void paint(Graphics g, List<Edge> edgesList, boolean cFlag) {
        for (Edge e : edgesList) {
//            if (cFlag) {
//                Vertex p0 = e.getP0();
//                Vertex p1 = e.getP1();
//                Line line = new Line(p0.getX(), p0.getY(), p0.getZ(), p1.getX(), p1.getY(), p1.getZ());
//                line = clip.clipLine(line);
//                if (line == null) {
//                    continue;
//                }
//                g.drawLine((int) line.getP0().getX(), (int) line.getP0().getY(),
//                        (int) line.getP1().getX(), (int) line.getP1().getY());
            e.draw(g);
        }
    }

}
