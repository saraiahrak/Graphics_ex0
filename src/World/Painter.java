package World;

import Scene.*;
import View.*;
import Math.*;

import java.awt.*;
import java.util.List;

public class Painter {
    private Scene scene;
    private View view;
    private Clip clip;

    public Painter(Scene sc, View viw) {
        this.scene = sc;
        this.view = viw;
        this.clip = new Clip(this.view.getViewPortWidth(), this.view.getViewPortHeight());
    }

    public void paint(Graphics g, List<Edge> edgesList, boolean cFlag) {
        for (Edge e : edgesList) {
            if (cFlag) {
                e = clip.clip(e);
                if (e == null) {
                    continue;
                }
            }
            e.draw(g);
        }
    }

}
