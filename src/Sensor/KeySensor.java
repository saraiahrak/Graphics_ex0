package Sensor;

import World.MyCanvas;
import World.World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.System.exit;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*****************
 * Class KeySensor
 * ***************/
public class KeySensor implements KeyListener {

    private MyCanvas canvas;

    /*****************
     * Constructor
     * ***************/
    public KeySensor(MyCanvas myCanvas) {
        canvas = myCanvas;
    }

    /*********
     * Events
     * ********/


    /**
     * keyPressed
     * Key pressed event
     *
     * @param e KeyEvent
     */

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
            if (canvas.shouldClip()) {
                canvas.setShouldClip(false);
            } else {
                canvas.setShouldClip(true);
            }
            canvas.repaint();
        }

        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            canvas.setShouldClip(false);
            canvas.getView().initTransformationMatrix();
            canvas.repaint();
        }

        if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
            canvas.setShouldClip(false);
            new World();
            canvas.repaint();
        }

        if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
            canvas.getView().setRotation("x");
        }
        if (e.getKeyChar() == 'y' || e.getKeyChar() == 'Y') {
            canvas.getView().setRotation("y");
        }
        if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
            canvas.getView().setRotation("z");
        }

        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            exit(0);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
