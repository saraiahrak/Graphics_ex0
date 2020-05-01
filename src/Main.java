import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Frame myFrame = new Frame("Exercise1");
        MyCanvas myCanvas = new MyCanvas("example3d.scn", "example3d.viw");
        myFrame.add(myCanvas);
        myCanvas.addKeyListener(new KeySensor(myCanvas));

        WindowAdapter myWindowAdapter = new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        myFrame.addWindowListener(myWindowAdapter);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}