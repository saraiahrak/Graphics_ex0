import java.awt.Point;

public class ScaleHandler implements TransformationHandler {

    private View view;
    private Point start;
    private Point end;

    public ScaleHandler(View v, Point s, Point e) {
        view = v;
        start = s;
        end = e;
    }

    @Override
    public Matrix handle() {
        Matrix t1 = Transformation3D.translate(0, 0,
                view.getPosition().getZ() - view.getLookAt().getZ());
        Matrix t2 = Transformation3D.translate(0, 0,
                view.getLookAt().getZ() - view.getPosition().getZ());


        Vector vEnd = new Vector(new double[]{end.getX(), end.getY(), 1, 1}, 4);
        Vector vStart = new Vector(new double[]{start.getX(), start.getY(), 1, 1}, 4);
        Vector vCenter = new Vector(new double[]{view.getWindowWidth() / 2, view.getWindowHeight() / 2, 1, 1}, 4);

        Vector dv = vEnd.sub(vCenter);
        Vector sv = vStart.sub(vCenter);


        //calculate the scale factor
        double da = dv.getSize();
        double sa = sv.getSize();
        double sFactor = (da / sa);

        Matrix scale = Transformation3D.scale(sFactor);
        //update the current matrix
        return t2.mult(scale).mult(t1).clone();
    }
}
