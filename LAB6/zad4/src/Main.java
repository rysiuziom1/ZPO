import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Poly poly = new Poly(new double[]{-4, 0, 1});
        Double root = Bisection.findRoot(poly, 0d, 4d, 0.001);
        System.out.println(root);
        System.out.println(poly.eval(root));
    }
}
