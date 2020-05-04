package World;
import Math.*;
public class Clip {
    enum Borders {
        LEFT, RIGHT, TOP, BOTTOM
    }

    private Edge left;
    private Edge right;
    private Edge bottom;
    private Edge top;


    public Clip(double width, double height) {
        left = new Edge(new Vertex(20, 20, 1), new Vertex(20, height + 20, 1));
        right = new Edge(new Vertex(width + 20, 20, 1), new Vertex(width + 20, height + 20, 1));
        top = new Edge(new Vertex(20, 20, 1), new Vertex(width + 20, 20, 1));
        bottom = new Edge(new Vertex(20, height + 20, 1), new Vertex(width + 20, height + 20, 1));
    }

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


    public Edge clipLeft(Edge edge) {
        if (edge.isParallel(left)) {
            return null;
        }
        Vertex intersection = edge.intersectionWith(left);
        if (intersection == null) {
            return null;
        }
        return new Edge(intersection, edge.getP1());

    }

    public Edge clipTop(Edge edge) {
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

    public Edge clipBottom(Edge edge) {
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

    public Edge clipRight(Edge edge) {
        if (edge.isParallel(right)) {
            return null;
        }
        Vertex intersection = edge.intersectionWith(right);
        if (intersection == null) {
            return null;
        }

        return new Edge(edge.getP0(), intersection);
    }

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

//
//    public Math.Edge clip(Math.Edge edge) {
//        boolean in = true;
//        boolean out = true;
//
//        double x1 = edge.getP0().getX();
//        double y1 = edge.getP0().getY();
//        double x2 = edge.getP1().getX();
//        double y2 = edge.getP1().getY();
//
//        if (x1 < left.getP0().getX() || x2 < left.getP0().getX()) {
//            in = false;
//            Math.Vertex intersection = edge.intersectionWith(left);
//            if (intersection != null) {
//                out = false;
//                if (x1 < this.left.getP0().getX()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//        if (x1 > this.right.getP0().getX() || x2 > this.right.getP0().getX()) {
//            in = false;
//            Math.Vertex intersection = edge.intersectionWith(this.right);
//            if (intersection != null) {
//                out = false;
//                if (x1 > this.right.getP0().getX()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//        if (y1 < this.top.getP0().getY() || y2 < this.top.getP0().getY()) {
//            in = false;
//            Math.Vertex intersection = edge.intersectionWith(this.top);
//            if (intersection != null) {
//                out = false;
//                if (y1 < this.top.getP0().getY()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//
//        if (y1 > this.bottom.getP0().getY() || y2 > this.bottom.getP0().getY()) {
//            in = false;
//            Math.Vertex intersection = edge.intersectionWith(this.bottom);
//            if (intersection != null) {
//                out = false;
//                if (y1 > this.bottom.getP0().getY()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//
//        if (in) {
//            return edge;
//        }
//
//        if (out) {
//            return null;
//        }
//
//        return new Math.Edge(new Math.Vertex(x1, y1, 1), new Math.Vertex(x2, y2, 1));
//
//
//    }

//
//    public Math.Edge clipLine(Math.Edge edge) {
//        boolean in = true;
//        boolean out = true;
//
//        double x1 = edge.getP0().getX();
//        double y1 = edge.getP0().getY();
//        double x2 = edge.getP1().getX();
//        double y2 = edge.getP1().getY();
//
//        if (x1 < left.getP0().getX() || x2 < left.getP0().getX()) {
//            in = false;
//            Math.Point intersection = edge.intersectionWith(left);
//            if (intersection != null) {
//                out = false;
//                if (x1 < this.left.getP0().getX()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//        if (x1 > this.right.getP0().getX() || x2 > this.right.getP0().getX()) {
//            in = false;
//            Math.Point intersection = line.intersectionWith(this.right);
//            if (intersection != null) {
//                out = false;
//                if (x1 > this.right.getP0().getX()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//        if (y1 < this.top.getP0().getY() || y2 < this.top.getP0().getY()) {
//            in = false;
//            Math.Point intersection = line.intersectionWith(this.top);
//            if (intersection != null) {
//                out = false;
//                if (y1 < this.top.getP0().getY()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//
//        if (y1 > this.bottom.getP0().getY() || y2 > this.bottom.getP0().getY()) {
//            in = false;
//            Math.Point intersection = line.intersectionWith(this.bottom);
//            if (intersection != null) {
//                out = false;
//                if (y1 > this.bottom.getP0().getY()) {
//                    x1 = intersection.getX();
//                    y1 = intersection.getY();
//                } else {
//                    x2 = intersection.getX();
//                    y2 = intersection.getY();
//                }
//            }
//        }
//
//        if (in) {
//            return line;
//        }
//
//        if (out) {
//            return null;
//        }
//
//        return new Math.Line(x1, y1, 1, x2, y2, 1);
//    }
}