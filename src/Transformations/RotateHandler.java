package Transformations;

import Math.Matrix;
import View.View;

import java.awt.*;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/********************
 * Class RotateHandler
 * ******************/
public class RotateHandler implements TransformationHandler {

    private View view;
    private Point start;
    private Point end;


    /*************
     * Constructor
     *************/

    public RotateHandler(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
    }


    /**
     * handle
     * Handle rotation transformation
     *
     * @return current transformation
     */
    @Override
    public Matrix handle() {

        //translate to origin
        double d = (view.getPosition().sub(view.getLookAt())).getSize();
        Matrix t1 = Transformations.translate(0, 0,
                d);
        Matrix t2 = Transformations.translate(0, 0,
                -d);

        //calculate end, start point vectors (relative to center)

        Point center = new Point(View.viewPortWidth / 2, View.viewPortHeight / 2);
        Point ec = new Point(end.x - center.x, end.y - center.y);
        Point sc = new Point(start.x - center.x, start.y - center.y);


        //calculate rotation angle
        double dAngle = Math.atan2(ec.y, ec.x);
        double sAngle = Math.atan2(sc.y, sc.x);
        double theta = dAngle - sAngle;
        Matrix rotate = Transformations.rotate(theta);

        return t2.mult(rotate).mult(t1).clone();
    }


}
