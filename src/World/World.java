package World;

import Scene.*;
import Sensor.KeySensor;
import View.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class World {
    private MyCanvas canvas;
    private Frame frame;

    public World() {

        canvas = new MyCanvas("example3d.scn", "example3d.viw");
        frame = new Frame("Graphics");
        frame.add(canvas);
        canvas.addKeyListener(new KeySensor(canvas));
        WindowAdapter myWindowAdapter = new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(myWindowAdapter);
    }

    public void show() {
        frame.pack();
        frame.setVisible(true);
    }

}
