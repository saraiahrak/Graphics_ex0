package Sensor;

import World.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.System.exit;

public class KeySensor implements KeyListener {
    private MyCanvas myCanvas;

    public KeySensor(MyCanvas canvas) {
        this.myCanvas = canvas;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
            if (myCanvas.getCFlag()) {
                myCanvas.setCFlag(false);
            } else {
                myCanvas.setCFlag(true);
            }
            myCanvas.repaint();
        }

        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            myCanvas.setCFlag(false);
            myCanvas.getView().initTransformationMatrix();
            myCanvas.repaint();
        }

        if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
            myCanvas.setCFlag(false);
            myCanvas.getView().initTransformationMatrix();
            myCanvas.repaint();
        }

        if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
            myCanvas.getView().setRotation("x");
            exit(0);
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
