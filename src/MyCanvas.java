import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;

class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Scene scene;
    private Paint paint;
    private View view;
    private Transformation transformation;
    private Matrix TT;
    private Matrix VM;
    private Matrix AT;
    private Matrix CT;
    private Point pStart, pEnd;
    private Vector vStart, vEnd, vCenter;
    private boolean bFlag;
    private Boolean cFlag;
    private double vWidth, vHeight, wWidth, wHeight, cx, cy;

    public MyCanvas(String scn, String viw) {
        this.scene = new Scene(scn);
        this.view = new View(viw);
        this.paint = new Paint(scene, view);
        this.transformation = new Transformation();
        this.wWidth = this.view.getSizeX();
        this.wHeight = this.view.getSizeY();
        setSize((int)this.wWidth, (int)this.wHeight);
        this.vWidth = this.view.getSizeX() - 40;
        this.vHeight = this.view.getSizeY() - 40;
        this.cx = this.wWidth / 2;
        this.cy = this.wHeight / 2;
        this.vCenter = new Vector(new double[]{this.cx, this.cy, 1}, 3);
        this.cFlag = false;
        this.bFlag = false;
        //set - TT, VM, CT and AT matrix
        setMatrix();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        this.TT = this.CT.mult(this.AT).mult(this.VM);

        //draw the view window boundaries
        g.drawRect(20, 20, (int)this.vWidth, (int)this.vHeight);
        this.paint.paint(g, this.TT.updateVertexList(this.scene.getVertexList()), this.cFlag);
    }

    public void setMatrix() {
        //total transformations matrix
        this.TT = new Matrix(3, 3);
        //accumulated transformations matrix
        this.AT = new Matrix(3, 3);
        //current transformation matrix
        this.CT = new Matrix(3, 3);

        //view matrix
        Matrix t1 = transformation.translate(-this.view.getOrigin().getX(), -this.view.getOrigin().getY());
        Matrix r = transformation.rotate(-this.view.getDirection());
        Matrix s = transformation.scale(this.vWidth / wWidth, -this.vHeight / wHeight);
        Matrix t2 = transformation.translate(this.vWidth / 2 + 20, this.vHeight / 2 + 20);
        this.VM = t2.mult(s).mult(r).mult(t1);
    }

    public boolean getCFlag() {
        return this.cFlag;
    }

    public void setCFlag(Boolean bool) {
        this.cFlag = bool;
    }

    public String findLocation(double x, double y) {
        double ww = this.view.getSizeX();
        double wh = this.view.getSizeY();

        //pressed on the left or right side
        if (x < ww / 3 || x > (2 * ww) / 3) {
            if (y < wh /3 || y > (2 * wh) / 3) {
                return "rotate";
            } else {
                return "scale";
            }
            //pressed on the middle
        } else {
            if (y < wh /3 || y > (2 * wh) / 3) {
                return "scale";
            } else {
                return "translate";
            }
        }
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent arg0) {
        this.pStart = arg0.getPoint();
        //convert the point to vector
        this.vStart = new Vector(new double[]{this.pStart.getX(), this.pStart.getY(), 1}, 3);
        this.transformation.setType(findLocation(pStart.getX(), pStart.getY()));
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        this.pEnd = arg0.getPoint();
        //convert the point to vector
        this.vEnd = new Vector(new double[]{this.pEnd.getX(), this.pEnd.getY(), 1}, 3);
        this.bFlag = true;
        AT = CT.mult(AT);
        this.CT = new Matrix(3,3);
        this.repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        this.pEnd = arg0.getPoint();
        //convert the point to vector
        this.vEnd = new Vector(new double[]{this.pEnd.getX(), this.pEnd.getY(), 1}, 3);
        this.bFlag = true;
        this.transformation.setType(findLocation(pStart.getX(), pStart.getY()));

        if (this.transformation.getType().equals("translate")) {
            this.CT = transformation.translate(pEnd.getX() - pStart.getX(),pEnd.getY() - pStart.getY());
        } else {
            Matrix t1 = transformation.translate(this.cx, this.cy);
            Matrix t2 = transformation.translate(-this.cx, -this.cy);
            Vector dv = this.vEnd.sub(this.vCenter);
            Vector sv = this.vStart.sub(this.vCenter);

            if (this.transformation.getType().equals("scale")) {
                //calculate the scale factor
                double da = dv.getSize();
                double sa = sv.getSize();
                double sFactor = (da / sa);

                Matrix scale = this.transformation.scale(sFactor, sFactor);
                //update the current matrix
                this.CT = t1.mult(scale).mult(t2);
            } else {
                //the transformation type is "rotate"
                Vector xAxis = new Vector(new double[]{1, 0, 0}, 3);
                double dThetha = dv.getTheta(xAxis);
                double sThetha = sv.getTheta(xAxis);
                Matrix rotate = this.transformation.rotate(dThetha - sThetha);

                //update the current matrix
                this.CT = t1.mult(rotate).mult(t2);
            }
        }
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