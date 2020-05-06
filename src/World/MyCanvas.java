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


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/*****************
 * Class MyCanvas
 * ***************/

public class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    private Scene scene;
    private Painter painter;
    private View view;
    private Point pressed;
    private boolean bFlag, shouldClip;
    private double viewPortWidth, viewPortHeight;
    private Vector rotation;


    /**************
     * Constructor
     * ************/
    public MyCanvas(String scn, String viw) {
        initCanvas(scn, viw);
    }


    /**********
     * Getters
     * ********/

    public Scene getScene() {
        return scene;
    }

    public View getView() {
        return view;
    }


    public boolean shouldClip() {
        return shouldClip;
    }

    /**********
     * Setters
     * ********/


    public void setShouldClip(Boolean clip) {
        this.shouldClip = clip;
    }

    private void setFinalTT() {
        view.setTT(view.getVM2().mult(view.getPro()).mult(view.getCT()).mult(view.getAT()).mult(view.getVM1()));
    }


    /**********
     * Methods
     * ********/

    /**
     * initCanvas
     * initialize members
     *
     * @param scn scene file name
     * @param viw view file name
     */


    public void initCanvas(String scn, String viw) {
        scene = new Scene(scn);
        view = new View(viw);
        viewPortWidth = View.viewPortWidth;
        viewPortHeight = View.viewPortHeight;
        painter = new Painter(viewPortWidth, viewPortHeight);
        shouldClip = false;
        bFlag = false;
        setSize((int) viewPortWidth + 40, (int) viewPortHeight + 40);
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    /**
     * drawBoundaries
     * draw canvas boundaries
     *
     * @param g graphics
     */
    private void drawBoundaries(Graphics g) {
        g.drawRect(20, 20, (int) viewPortWidth, (int) viewPortHeight);
    }

    /**
     * paint
     * draw canvas drawables
     *
     * @param g graphics
     */
    public void paint(Graphics g) {
        setFinalTT();
        drawBoundaries(g);
        ArrayList<Edge> edges = Scene.apply(view.getTT());
        painter.paint(g, edges, shouldClip);
    }




    /**********
     * Events
     * ********/

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
        view.setAT(view.getCT().mult(view.getAT()));
        view.setCT(new Matrix(4, 4));
        repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        Point current = arg0.getPoint();
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