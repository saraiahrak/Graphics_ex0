package Transformations;

import Math.Matrix;
import View.View;

import java.awt.*;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/***********************
 * Class TranslateHandler
 * *********************/
public class TranslateHandler implements TransformationHandler {

    private View view;
    private Point start;
    private Point end;


    /***************
     * Constructor
     * ************/
    public TranslateHandler(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
    }


    /**
     * handle
     * Handle translate transformation
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

        //calculate ratio
        double widthRatio = view.getWindowWidth() / view.getViewPortWidth();
        double heightRatio = view.getWindowHeight() / view.getViewPortHeight();

        Matrix translate = Transformations.translate((end.getX() - start.getX()) * widthRatio,
                (-(end.getY() - start.getY())) * heightRatio, 0).clone();

        return t2.mult(translate).mult(t1).clone();
    }

}
