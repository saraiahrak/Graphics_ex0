package Transformations;

import Math.*;
import Math.Matrix;
import View.*;

import java.awt.Point;

public class RotateHandler implements TransformationHandler {

    private View view;
    private Point start;
    private Point end;

    public RotateHandler(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
    }

    @Override
    public Matrix handle() {

        double d = (view.getPosition().sub(view.getLookAt())).getSize();
        Matrix t1 = Transformations.translate(0, 0,
                d);
        Matrix t2 = Transformations.translate(0, 0,
                -d);
        Point center = new Point(View.viewPortWidth / 2, View.viewPortHeight / 2);
        Point dMinusC = new Point(end.x - center.x, end.y - center.y);
        Point sMinusC = new Point(start.x - center.x, start.y - center.y);

        double dAngle = Math.atan2(dMinusC.y, dMinusC.x);
        double sAngle = Math.atan2(sMinusC.y, sMinusC.x);
        double theta = dAngle - sAngle;
        Matrix rotate = Transformations.rotate(theta);

        return t2.mult(rotate).mult(t1).clone();
    }


}
