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
        double widthRatio = view.getWindowWidth() / view.getViewPortWidth();
        double heightRatio = view.getWindowHeight() / view.getViewPortHeight();
        return Transformation3D.translate((end.getX() - start.getX()) * widthRatio,
                (-(end.getY() - start.getY())) * heightRatio, 0).clone();
    }

}
