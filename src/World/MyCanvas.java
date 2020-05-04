package World;

import Scene.*;
import Transformations.*;
import View.*;
import Math.*;
import World.*;

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
    private Painter paint;
    private View view;
    private Matrix TT, VM1, VM2, AT, CT, Pro;
    private Point pStart, pEnd;
    private Vector xVec, yVec, zVec;
    private boolean bFlag, cFlag;
    private double vWidth, vHeight, wWidth, wHeight, centerX, centerY;

    public MyCanvas(String scn, String viw) {
        this.scene = new Scene(scn);
        this.view = new View(viw);
        this.paint = new Painter(scene, view);
        this.vWidth = this.view.getViewPortWidth();
        this.vHeight = this.view.getViewPortHeight();
        this.wWidth = this.vWidth + 40;
        this.wHeight = this.vHeight + 40;
        setSize((int) this.wWidth, (int) this.wHeight);
        this.centerX = this.wWidth / 2;
        this.centerY = this.wHeight / 2;
        this.cFlag = false;
        this.bFlag = false;
        //set - TT, VM, CT and AT matrix
        setMatrix();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        TT = VM2.mult(Pro).mult(CT).mult(AT).mult(VM1);
        //draw the view window boundaries
        g.drawRect(20, 20, (int) vWidth, (int) vHeight);
        ArrayList<Vertex> vertices = TT.updateVertexList(scene.getVertexList());

        this.paint.paint(g, scene.manipulateEdge(vertices), this.cFlag);
    }

    public void setMatrix() {
        //total transformations matrix
        this.TT = new Matrix(4, 4);
        //accumulated transformations matrix
        this.AT = new Matrix(4, 4);
        //current transformation matrix
        this.CT = new Matrix(4, 4);
        //projection matrix
        this.Pro = new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        });


        // form viewing coordinate system
        // Z-Axis
        Vector tmpZ = this.view.getPosition().sub(this.view.getLookAt());
        this.zVec = tmpZ.scalar(1 / tmpZ.getSize());
        // X-Axis
        Vector tmpX = this.view.getUp().cross(zVec);
        this.xVec = tmpX.scalar(1 / tmpX.getSize());
        // Y-Axis
        this.yVec = this.zVec.cross(this.xVec);

        //view matrix
        Matrix t1 = Transformation3D.translate(-view.getPosition().getX(),
                -view.getPosition().getY(), -view.getPosition().getZ());
        Matrix r = new Matrix(new double[][]{
                {xVec.getX(), xVec.getY(), xVec.getZ(), 0},
                {yVec.getX(), yVec.getY(), yVec.getZ(), 0},
                {zVec.getX(), zVec.getY(), zVec.getZ(), 0},
                {0, 0, 0, 1}
        });
        this.VM1 = r.mult(t1);

        double sF = this.vWidth / this.view.getWindowWidth();
        double sY = this.vHeight / this.view.getWindowHeight();

        Matrix s = Transformation3D.scale(sF, -sY, 1);
        Matrix tt = Transformation3D.translate(-(view.getLeft() + (view.getWindowWidth() / 2)),
                -(view.getBottom() + (view.getWindowHeight() / 2)), 0);
        Matrix t2 = Transformation3D.translate(this.centerX, this.centerY, 0);

        this.VM2 = t2.mult(s).mult(tt);
    }

    public boolean getCFlag() {
        return this.cFlag;
    }

    public void setCFlag(Boolean bool) {
        this.cFlag = bool;
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent arg0) {
        this.pStart = arg0.getPoint();
        //convert the point to vector
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        this.pEnd = arg0.getPoint();
        //convert the point to vector
        this.bFlag = true;
        AT = CT.mult(AT);
        this.CT = new Matrix(4, 4);
        this.repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        this.pEnd = arg0.getPoint();
        //convert the point to vector
        this.bFlag = true;

        TransformationFactory factory = new TransformationFactory(view, pStart, pEnd);
        TransformationHandler handler = factory.create();
        CT = handler.handle();

        this.repaint();
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