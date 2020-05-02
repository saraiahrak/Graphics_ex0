public class Clip {
    private Line left;
    private Line right;
    private Line bottom;
    private Line top;

    public Clip(double width, double height) {
        this.left = new Line(20, 20, 1, 20, height + 20, 1);
        this.right = new Line(width + 20, 20, 1, width + 20, height + 20, 1);
        this.top = new Line(20, 20 ,1, width + 20, 20, 1);
        this.bottom = new Line(20, height + 20, 1, width + 20, height + 20, 1);
    }

    public Line clipLine(Line line) {
        boolean in = true;
        boolean out = true;

        double x1 = line.getP0().getX();
        double y1 = line.getP0().getY();
        double x2 = line.getP1().getX();
        double y2 = line.getP1().getY();

        if (x1 < this.left.getP0().getX() || x2 < this.left.getP0().getX()) {
            in = false;
            Point intersection = line.intersectionWith(this.left);
            if (intersection != null) {
                out = false;
                if (x1 < this.left.getP0().getX()) {
                    x1 = intersection.getX();
                    y1 = intersection.getY();
                } else {
                    x2 = intersection.getX();
                    y2 = intersection.getY();
                }
            }
        }
        if (x1 > this.right.getP0().getX() || x2 > this.right.getP0().getX()) {
            in = false;
            Point intersection = line.intersectionWith(this.right);
            if (intersection != null) {
                out = false;
                if (x1 > this.right.getP0().getX()) {
                    x1 = intersection.getX();
                    y1 = intersection.getY();
                } else {
                    x2 = intersection.getX();
                    y2 = intersection.getY();
                }
            }
        }
        if (y1 < this.top.getP0().getY() || y2 < this.top.getP0().getY()) {
            in = false;
            Point intersection = line.intersectionWith(this.top);
            if (intersection != null) {
                out = false;
                if (y1 < this.top.getP0().getY()) {
                    x1 = intersection.getX();
                    y1 = intersection.getY();
                } else {
                    x2 = intersection.getX();
                    y2 = intersection.getY();
                }
            }
        }

        if (y1 > this.bottom.getP0().getY() || y2 > this.bottom.getP0().getY()) {
            in = false;
            Point intersection = line.intersectionWith(this.bottom);
            if (intersection != null) {
                out = false;
                if (y1 > this.bottom.getP0().getY()) {
                    x1 = intersection.getX();
                    y1 = intersection.getY();
                } else {
                    x2 = intersection.getX();
                    y2 = intersection.getY();
                }
            }
        }

        if (in) {
            return line;
        }

        if (out) {
            return null;
        }

        return new Line(x1, y1, 1, x2, y2, 1);
    }
}