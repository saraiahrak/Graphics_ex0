import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;

class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MyCanvas() {
        setSize(600, 480);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public MyCanvas(String scn, String viw) {
        setSize(600, 480);
        addMouseListener(this);
        addMouseMotionListener(this);
        get2dWorld(scn);
        getViewParameters(viw);
    }
    Point pStart, pEnd;
    boolean bFlag = false;

    public void paint(Graphics g) {
        Polygon	p = new Polygon();
        int x[] = {100, 200, 200, 100};
        int y[] = {100, 100, 200, 200};
        int x1[] = {300, 400, 400, 300};
        int y1[] = {300, 300, 400, 400};


        for (int i=0; i<x.length; i++) {
            p.addPoint(x1[i],y1[i]);
        }

        g.setColor(Color.blue);

        g.drawPolyline(x, y, x.length);
        g.drawPolygon(x1, y, x1.length);
        g.drawPolygon(p);
        g.drawLine(20,30,100,100);
        g.drawRect(100, 300, 100, 100);

        if ( bFlag )
            g.drawLine(pStart.x,pStart.y,pEnd.x,pEnd.y);
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        pStart = arg0.getPoint();

    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        pEnd = arg0.getPoint();
        bFlag = true;
        this.repaint();
    }

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        pEnd = arg0.getPoint();
        bFlag = true;
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

    public void get2dWorld(String fileName) {
        Point[] points = null;
        Point[] edges = null;
        try{
            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);
            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);
            //read the first line
            String line = bufferReader.readLine();
            int counter = Integer.parseInt(line);
            int i = 0;
            while (i < counter) {
                line = bufferReader.readLine();
                String[] params = line.split(" ");
                points[i] = new Point(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
                i++;
            }
            line = bufferReader.readLine();
            counter = Integer.parseInt(line);
            i = 0;
            while (i < counter) {
                line = bufferReader.readLine();
                String[] lines = line.split(" ");
                edges[i+i] = points[Integer.parseInt(lines[0])];
                edges[i+i+1] = points[Integer.parseInt(lines[1])];
                i++;
            }
            //Close the buffer reader
            bufferReader.close();
            inputFile.close();
        } catch(Exception e){
            System.out.println("Error while reading file line by line");
        }
    }

    public void getViewParameters(String fileName) {

    }


}