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
    private Transformation3D transformation3D;
    private Matrix TT, VM1, VM2, AT, CT, Pro;
    private Point pStart, pEnd;
    private Vector vStart, vEnd, vCenter, xVec, yVec, zVec;
    private boolean bFlag, cFlag;
    private double vWidth, vHeight, wWidth, wHeight, centerX, centerY;

    public MyCanvas(String scn, String viw) {
        this.scene = new Scene(scn);
        this.view = new View(viw);
        this.paint = new Paint(scene, view);
        this.transformation3D = new Transformation3D();
        this.vWidth = this.view.getViewPortX();
        this.vHeight = this.view.getViewPortY();
        this.wWidth = this.vWidth + 40;
        this.wHeight = this.vHeight + 40;
        setSize((int)this.wWidth, (int)this.wHeight);
        this.centerX = this.wWidth / 2;
        this.centerY = this.wHeight / 2;
        this.vCenter = new Vector(new double[]{this.centerX, this.centerY, 1, 1}, 4);
        this.cFlag = false;
        this.bFlag = false;
        //set - TT, VM, CT and AT matrix
        setMatrix();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        this.TT = this.VM2.mult(this.Pro).mult(this.CT).mult(this.AT).mult(this.VM1);
        //draw the view window boundaries
        g.drawRect(20, 20, (int)this.vWidth, (int)this.vHeight);
        this.paint.paint(g, this.TT.updateVertexList(this.scene.getVertexList()), this.cFlag);
    }

    public void setMatrix() {
        //total transformations matrix
        this.TT = new Matrix(4, 4);
        //accumulated transformations matrix
        this.AT = new Matrix(4, 4);
        //current transformation matrix
        this.CT = new Matrix(4, 4);
        //projection matrix
        this.Pro = new Matrix(4, 4);

        // form viewing coordinate system
        // Z-Axis
        Vector tmpZ = this.view.getPosition().sub(this.view.getLookAt());
        this.zVec = tmpZ.scalar(1 / tmpZ.getSize());
        // X-Axis
        Vector tmpX = this.view.getUp().cross(zVec);
        this.xVec = tmpX.scalar(1 / tmpX.getSize());
        // Y-Axis
        this.yVec = this.xVec.cross(this.zVec);

        //view matrix
        Matrix t1 = transformation3D.translate(-this.view.getPosition().getX(),
                -this.view.getPosition().getY(), -this.view.getPosition().getZ());
        Matrix r = new Matrix(this.xVec, this.yVec, this.zVec);
        this.VM1 = r.mult(t1);

        double sF = this.vWidth / this.view.getWindowWidth();
        double sY = this.vHeight / this.view.getWindowHeight();

        Matrix s = transformation3D.scale(sF, -sY);
        Matrix t2 = transformation3D.translate(this.centerX, this.centerY, 0);

        this.VM2 = t2.mult(s);
    }

    public boolean getCFlag() {
        return this.cFlag;
    }

    public void setCFlag(Boolean bool) {
        this.cFlag = bool;
    }

    public String findLocation(double x, double y) {

        //pressed on the left or right side
        if (x < this.wWidth / 3 || x > (2 * this.wWidth) / 3) {
            if (y < this.wHeight /3 || y > (2 * this.wHeight) / 3) {
                return "rotate";
            } else {
                return "scale";
            }
            //pressed on the middle
        } else {
            if (y < this.wHeight /3 || y > (2 * this.wHeight) / 3) {
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
        this.vStart = new Vector(new double[]{this.pStart.getX(), this.pStart.getY(), 1, 1}, 4);
        this.transformation3D.setType(findLocation(pStart.getX(), pStart.getY()));
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        this.pEnd = arg0.getPoint();
        //convert the point to vector
        this.vEnd = new Vector(new double[]{this.pEnd.getX(), this.pEnd.getY(), 1, 1}, 4);
        this.bFlag = true;
        AT = CT.mult(AT);
        this.CT = new Matrix(4,4);
        this.repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        this.pEnd = arg0.getPoint();
        //convert the point to vector
        this.vEnd = new Vector(new double[]{this.pEnd.getX(), this.pEnd.getY(), 1, 1}, 4);
        this.bFlag = true;
        this.transformation3D.setType(findLocation(pStart.getX(), pStart.getY()));

        if (this.transformation3D.getType().equals("translate")) {
            double widthRatio = this.view.getWindowWidth() / this.vWidth;
            double heightRatio = this.view.getWindowHeight() / this.vHeight;
            this.CT = transformation3D.translate((pEnd.getX() - pStart.getX())*widthRatio,
                    ( - (pEnd.getY() - pStart.getY()))*heightRatio, 0);
        } else {
            Matrix t1 = transformation3D.translate(0, 0,
                    this.view.getPosition().getZ() - this.view.getLookAt().getZ());
            Matrix t2 = transformation3D.translate(0, 0,
                    this.view.getLookAt().getZ() - this.view.getPosition().getZ());
            Vector dv = this.vEnd.sub(this.vCenter);
            Vector sv = this.vStart.sub(this.vCenter);

            if (this.transformation3D.getType().equals("scale")) {
                //calculate the scale factor
                double da = dv.getSize();
                double sa = sv.getSize();
                double sFactor = (da / sa);

                Matrix scale = this.transformation3D.scale(sFactor);
                //update the current matrix
                this.CT = t2.mult(scale).mult(t1);
            } else {
                Matrix rotate;
                //the transformation type is "rotate"
                Vector xAxis = new Vector(new double[]{1, 0, 0, 0}, 4);
                double dTheta = dv.getTheta(xAxis);
                double sTheta = sv.getTheta(xAxis);
                rotate = this.transformation3D.rotate(dTheta - sTheta);
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