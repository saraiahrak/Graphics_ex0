package World;

import Math.Edge;

import java.awt.*;
import java.util.List;



/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*****************
 * Class Painter
 * ***************/
public class Painter {
    private Clip clip;

    public Painter(double width, double height) {
        this.clip = new Clip(width, height);
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
