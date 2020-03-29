import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

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
    private Vector vStart, vEnd;
    private boolean bFlag = false;
    private boolean pFlag = true;
    private Boolean cFlag = false;
    private double vHeight;
    private double vWidth;

    public MyCanvas(String scn, String viw) {
        this.scene = new Scene(scn);
        this.view = new View(viw);
        this.paint = new Paint(scene);
        this.transformation = new Transformation();
        setSize((int)this.view.getSizeX(), (int)this.view.getSizeY());
        this.vWidth = this.view.getSizeX() - 40;
        this.vHeight = this.view.getSizeY() - 40;
        this.TT = new Matrix(3, 3);
        this.VM = new Matrix(3, 3);
        this.AT = new Matrix(3, 3);
        this.CT = new Matrix(3, 3);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        Matrix t1 = transformation.translate(-this.view.getOrigin().getX(), -this.view.getOrigin().getY());
        Matrix r = transformation.rotate(-this.view.getDirection());
        double wWidth = this.view.getSizeX() - 40;
        double wHeight = this.view.getSizeY() - 40;
        Matrix s = transformation.scale(this.vWidth/wWidth, -this.vHeight/wHeight);
        Matrix t2 = transformation.translate((double) this.vWidth / 2 + 20, (double) this.vHeight / 2 + 20);
        this.VM = t2.mult(s).mult(r).mult(t1);

        if (this.pFlag) {
            //draw the view window boundaries
            g.drawRect(20, 20, (int)wWidth, (int)wHeight);
            this.paint.paint(g, this.updateVertexList(this.scene.getVertexList(), this.VM), this.cFlag);
            this.pFlag = false;
        } else {
            //draw the view window boundaries
            g.drawRect(20, 20, (int)wWidth, (int)wHeight);
            this.paint.paint(g, this.updateVertexList(this.scene.getVertexList(), this.TT), this.cFlag);
        }
    }

    public List<Vector> updateVertexList(List<Vector> vertex, Matrix TT){
        List<Vector> newVertexList = new ArrayList<>();
        int vertexNum = vertex.size();
        int i = 0;
        while (i < vertexNum) {
            newVertexList.add(TT.mult(vertex.get(i)));
            i++;
        }
        return newVertexList;
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
        pEnd = arg0.getPoint();
        //convert the point to vector
        this.vEnd = new Vector(new double[]{this.pEnd.getX(), this.pEnd.getY(), 1}, 3);
        this.bFlag = true;
        AT = CT.mult(AT);
        this.CT = new Matrix(3,3);
        this.repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        pEnd = arg0.getPoint();
        //convert the point to vector
        this.vEnd = new Vector(new double[]{this.pEnd.getX(), this.pEnd.getY(), 1}, 3);
        this.bFlag = true;
        this.transformation.setType(findLocation(pEnd.getX(), pEnd.getY()));

        if (this.transformation.getType().equals("translate")) {
            this.CT = transformation.translate(pEnd.getX() - pStart.getX(),pEnd.getY() - pStart.getY());
        } else {
            Matrix t1 = transformation.translate(this.view.getOrigin().getX(), this.view.getOrigin().getY());
            Matrix t2 = transformation.translate(-this.view.getOrigin().getX(),
                    -this.view.getOrigin().getY());

            if (this.transformation.getType().equals("scale")) {
                //calculate the scale factor
                double da = (this.vEnd.sub(this.view.getOrigin())).getSize();
                double sa = (this.vStart.sub(this.view.getOrigin())).getSize();
                double sFactor = (da / sa);

                Matrix scale = this.transformation.scale(sFactor, sFactor);
                //update the current matrix
                this.CT = t1.mult(scale).mult(t2);
            } else {
                //the transformation type is "rotate"
                Vector dv = this.vEnd.sub(this.view.getOrigin());
                Vector sv = this.vStart.sub(this.view.getOrigin());
                double angle = dv.getTheta(sv);
                Matrix rotate = this.transformation.rotate(angle);

                //update the current matrix
                this.CT = t1.mult(rotate).mult(t2);
            }
        }
        this.TT = this.CT.mult(this.AT).mult(this.VM);
        this.scene.setVertexList(this.TT);
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

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'c' | e.getKeyChar() == 'C') {
            this.cFlag = true;
            this.repaint();
        }
    }

}