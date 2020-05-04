package World;

import Scene.*;
import Transformations.*;
import View.*;
import Math.*;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

public class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Scene scene;
    private Painter painter;
    private View view;
    //private Matrix TT, VM1, VM2, AT, CT, Pro;
    private Point pressed, current;
    private Vector xAxis, yAxis, zAxis;
    private boolean bFlag, shouldClip;
    private double viewPortWidth, viewPortHeight, windowWidth, windowHeight, centerX, centerY;
    private Vector rotation;

    public MyCanvas(String scn, String viw) {
        initCanvas(scn, viw);
    }

    public void initCanvas(String scn, String viw) {
        scene = new Scene(scn);
        view = new View(viw);
        viewPortWidth = view.getViewPortWidth();
        viewPortHeight = view.getViewPortHeight();
        painter = new Painter(viewPortWidth, viewPortHeight);
        windowWidth = viewPortWidth + 40;
        windowHeight = viewPortHeight + 40;
        centerX = windowWidth / 2;
        centerY = windowHeight / 2;
        shouldClip = false;
        bFlag = false;
        setSize((int) this.windowWidth, (int) this.windowHeight);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public Scene getScene() {
        return scene;
    }

    public View getView() {
        return view;
    }

    public void paint(Graphics g) {
        view.setTT(view.getVM2().mult(view.getPro()).mult(view.getCT()).mult(view.getAT()).mult(view.getVM1()));
        //draw the view window boundaries
        g.drawRect(20, 20, (int) viewPortWidth, (int) viewPortHeight);

        ArrayList<Edge> edges = Scene.apply(view.getTT());

        painter.paint(g, edges, shouldClip);
    }

    public boolean getCFlag() {
        return this.shouldClip;
    }

    public void setCFlag(Boolean bool) {
        this.shouldClip = bool;
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent arg0) {
        pressed = arg0.getPoint();
        //convert the point to vector
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //convert the point to vector
        bFlag = true;
        view.setAT(view.getCT().mult(view.getAT())) ;
        view.setCT(new Matrix(4, 4));
        repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        current = arg0.getPoint();
        //convert the point to vector
        bFlag = true;

        TransformationFactory factory = new TransformationFactory(view, pressed, current);
        TransformationHandler handler = factory.create();
        view.setCT(handler.handle());

        repaint();
    }

    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
}