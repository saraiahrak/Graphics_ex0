public class Main {
    public static void main(String[] args) {
        double[] v = {3,4,0};
        double[] u = {4,4,2};

        Vector v1 = new Vector(v);
        Vector v2 = new Vector(u);

        double v3 = v1.dot(v2);
        double v4 = v1.dot(v2, v1.getTheta(v2));

        System.out.println(v3);
        System.out.println(v4);
    }
}
