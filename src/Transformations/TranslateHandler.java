package Transformations;

import View.*;
import Math.*;

import java.awt.Point;

public class TranslateHandler implements TransformationHandler {

    private View view;
    private Point start;
    private Point end;

    public TranslateHandler(View v, Point s, Point e) {
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
        double widthRatio = view.getWindowWidth() / view.getViewPortWidth();
        double heightRatio = view.getWindowHeight() / view.getViewPortHeight();
        Matrix translate = Transformations.translate((end.getX() - start.getX()) * widthRatio,
                (-(end.getY() - start.getY())) * heightRatio, 0).clone();
        return t2.mult(translate).mult(t1).clone();
    }

}
