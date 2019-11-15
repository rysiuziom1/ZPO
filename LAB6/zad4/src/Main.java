public class Main {
    public static void main(String[] args) {
        Poly poly = new Poly(new double[]{-1, 0, 2});
        Double root = Bisection.findRoot(poly, -1d, 0d, 0.00001);
        System.out.println("Miejsce zerowe: " + root);
        System.out.println("Wartosc: " + poly.eval(root));
    }
}
