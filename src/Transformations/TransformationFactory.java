package Transformations;

import View.*;
import java.awt.Point;


/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/******************************
 * Class TransformationFactory
 * ***************************/
public class TransformationFactory {

    private View view;
    private Point start;
    private Point end;


    /**************
     * Constructor
     * ************/
    public TransformationFactory(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
    }

    /**
     * create
     * create transformation handler according to mouse position on screen
     *
     * @return current transformation
     */
    public TransformationHandler create() {
        double x = start.getX();
        double y = start.getY();

        double width = View.viewPortWidth;
        double height = View.viewPortHeight;

        if (x < width / 3 || x > (2 * width) / 3) {
            if (y < height / 3 || y > (2 * height) / 3) {
                return new RotateHandler(view, start, end);
            }
            return new ScaleHandler(view, start, end);
        }
        //pressed on the middle

        if (y < height / 3 || y > (2 * height) / 3) {
            return new ScaleHandler(view, start, end);

        }
        return new TranslateHandler(view, start, end);

    }
}
