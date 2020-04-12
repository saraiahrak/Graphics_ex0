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
            if (this.myCanvas.getCFlag()) {
                this.myCanvas.setCFlag(false);
            } else {
                this.myCanvas.setCFlag(true);
            }
            this.myCanvas.repaint();
        }

        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            this.myCanvas.setMatrix();
            this.myCanvas.repaint();
        }

        if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
            System.out.println("Error");
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
