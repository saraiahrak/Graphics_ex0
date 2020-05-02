import java.awt.Point;

public class TransformationFactory {

    private View view;
    private Point start;
    private Point end;

    public TransformationFactory(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
    }


    public TransformationHandler create() {
        double x = start.getX();
        double y = start.getY();
        double width = view.getViewPortX();
        double height = view.getViewPortY();

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
