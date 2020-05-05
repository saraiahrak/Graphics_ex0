package Transformations;

import View.*;
import Math.*;

import java.awt.Point;

public class ScaleHandler implements TransformationHandler {

    private View view;
    private Point start;
    private Point end;
    private Point center;

    public ScaleHandler(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
        center = new Point(v.getViewPortWidth() / 2, v.getViewPortHeight() / 2);
    }

    @Override
    public Matrix handle() {
        double d = (view.getPosition().sub(view.getLookAt())).getSize();
        Matrix t1 = Transformations.translate(0, 0,
                d);
        Matrix t2 = Transformations.translate(0, 0,
                -d);
        double sf = end.distance(center) / start.distance(center);
        return t2.mult(Transformations.scale(sf)).mult(t1).clone();
    }
}
