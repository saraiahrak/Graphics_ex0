package World;

import Math.*;

/************************
 * Dekel Yosef 315634071 *
 * Sarai Ahrak 204894000 *
 * *********************/


/****************
 * Class Clip
 * **************/

public class Clip {
    enum Borders {
        LEFT, RIGHT, TOP, BOTTOM
    }

    private Edge left;
    private Edge right;
    private Edge bottom;
    private Edge top;


    /**************
     * Constructor
     * ************/

    public Clip(double width, double height) {
        left = new Edge(new Vertex(20, 20, 1), new Vertex(20, height + 20, 1));
        right = new Edge(new Vertex(width + 20, 20, 1), new Vertex(width + 20, height + 20, 1));
        top = new Edge(new Vertex(20, 20, 1), new Vertex(width + 20, 20, 1));
        bottom = new Edge(new Vertex(20, height + 20, 1), new Vertex(width + 20, height + 20, 1));
    }


    /***********
     * Methods
     * *********/


    /**
     * clip
     * clip edge according to borders
     *
     * @param edge to clip
     * @return clipped edge
     */
    public Edge clip(Edge edge) {
        double x1 = edge.getP0().getX();
        double y1 = edge.getP0().getY();
        double x2 = edge.getP1().getX();
        double y2 = edge.getP1().getY();
        double leftX = left.getP0().getX();
        double rightX = right.getP0().getX();
        double upY = top.getP0().getY();
        double downY = bottom.getP0().getY();

        if (allOut(edge)) {
            return null;
        }
        if (allIn(edge)) {
            return edge;
        }

        Borders[] intersecting = getBorders(edge);

        for (Borders border : intersecting) {
            if (border == null) {
                continue;
            }
            switch (border) {
                case LEFT:
                    edge = clipLeft(edge);
                    break;
                case BOTTOM:
                    edge = clipBottom(edge);
                    break;
                case RIGHT:
                    edge = clipRight(edge);
                    break;
                case TOP:
                    edge = clipTop(edge);
                    break;
                default:
            }
            if (edge == null) {
                break;
            }
        }

        return edge;

    }

    /**
     * allOut
     * check if edge is all out
     *
     * @param edge to clip
     * @return true if out of bounds, false otherwise
     */
    private boolean allOut(Edge edge) {
        double x1 = edge.getP0().getX();
        double y1 = edge.getP0().getY();
        double x2 = edge.getP1().getX();
        double y2 = edge.getP1().getY();
        double leftX = left.getP0().getX();
        double rightX = right.getP0().getX();
        double upY = top.getP0().getY();
        double downY = bottom.getP0().getY();

        return ((x1 < leftX && x2 < leftX) || (x2 > rightX && x1 > rightX) || (y1 < upY && y2 < upY) || (y1 > downY && y2 > downY));
    }

    /**
     * allIn
     * check if edge is all in
     *
     * @param edge to clip
     * @return true if in of bounds, false otherwise
     */
    private boolean allIn(Edge edge) {
        double x1 = edge.getP0().getX();
        double y1 = edge.getP0().getY();
        double x2 = edge.getP1().getX();
        double y2 = edge.getP1().getY();
        double leftX = left.getP0().getX();
        double rightX = right.getP0().getX();
        double upY = top.getP0().getY();
        double downY = bottom.getP0().getY();

        return (x1 >= leftX && x2 >= leftX && x1 <= rightX && x2 <= rightX && y1 >= upY && y2 >= upY && y1 <= downY && y2 <= downY);

    }

    /**
     * clipLeft
     * clip edge according to left border
     *
     * @param edge to clip
     * @return clipped edge
     */
    private Edge clipLeft(Edge edge) {
        if (edge.isParallel(left)) {
            return null;
        }
        Vertex intersection = edge.intersectionWith(left);
        if (intersection == null) {
            return null;
        }
        return new Edge(intersection, edge.getP1());

    }


    /**
     * clipTop
     * clip edge according to top border
     *
     * @param edge to clip
     * @return clipped edge
     */
    private Edge clipTop(Edge edge) {
        if (edge.isParallel(top)) {
            return null;
        }
        double slope = edge.getV().getY();
        Vertex intersection = edge.intersectionWith(top);
        if (intersection == null) {
            return null;
        }
        if (slope < 0) {
            return new Edge(edge.getP0(), intersection);
        }
        return new Edge(intersection, edge.getP1());
    }


    /**
     * clipBottom
     * clip edge according to bottom border
     *
     * @param edge to clip
     * @return clipped edge
     */
    private Edge clipBottom(Edge edge) {
        if (edge.isParallel(bottom)) {
            return null;
        }
        Vertex intersection = edge.intersectionWith(bottom);
        if (intersection == null) {
            return null;
        }
        double slope = edge.getV().getY();

        if (slope < 0) {
            return new Edge(intersection, edge.getP1());
        }
        return new Edge(edge.getP0(), intersection);

    }


    /**
     * clipRight
     * clip edge according to right border
     *
     * @param edge to clip
     * @return clipped edge
     */
    private Edge clipRight(Edge edge) {
        if (edge.isParallel(right)) {
            return null;
        }
        Vertex intersection = edge.intersectionWith(right);
        if (intersection == null) {
            return null;
        }

        return new Edge(edge.getP0(), intersection);
    }


    /**
     * getBorders
     * get borders the edge intersects with
     *
     * @param edge to clip
     * @return array of borders
     */
    private Borders[] getBorders(Edge edge) {
        Borders[] borders = new Borders[4];
        int size = 0;

        double x1 = edge.getP0().getX();
        double y1 = edge.getP0().getY();
        double x2 = edge.getP1().getX();
        double y2 = edge.getP1().getY();
        double leftX = left.getP0().getX();
        double rightX = right.getP0().getX();
        double upY = top.getP0().getY();
        double downY = bottom.getP0().getY();

        if (x1 < leftX || x2 < leftX) {
            borders[size] = (Borders.LEFT);
            size++;
        }

        if (y2 > downY || y1 > downY) {
            borders[size] = (Borders.BOTTOM);
            size++;
        }

        if (x2 > rightX || x1 > rightX) {
            borders[size] = (Borders.RIGHT);
            size++;
        }

        if (y1 < upY || y2 < upY) {
            borders[size] = (Borders.TOP);
        }

        return borders;
    }

}