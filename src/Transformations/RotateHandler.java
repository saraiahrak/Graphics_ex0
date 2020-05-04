package Transformations;

import Math.*;
import Math.Matrix;
import World.*;
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



        Matrix t1 = Transformation3D.translate(0, 0,
                view.getPosition().getZ() - view.getLookAt().getZ());
        Matrix t2 = Transformation3D.translate(0, 0,
                view.getLookAt().getZ() - view.getPosition().getZ());

        Vector xAxis = new Vector(new double[]{1, 0, 0, 0});
        Vector yAxis = new Vector(new double[]{0, 1, 0, 0});
        Vector zAxis = new Vector(new double[]{0, 0, 1, 0});

        Vector vEnd = new Vector(new double[]{end.getX(), end.getY(), 1, 1});
        Vector vStart = new Vector(new double[]{start.getX(), start.getY(), 1, 1});
        Vector vCenter = new Vector(new double[]{view.getWindowWidth() / 2, view.getWindowHeight() / 2, 1, 1});

        Vector dv = vEnd.sub(vCenter);
        Vector sv = vStart.sub(vCenter);
        Matrix rotate;
        //the transformation type is "rotate"
        double dTheta = dv.getTheta(xAxis);
        double sTheta = sv.getTheta(xAxis);
        rotate = Transformation3D.rotate(dTheta - sTheta);
        //update the current matrix
        return t1.mult(rotate).mult(t2).clone();
    }


}
