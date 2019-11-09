import java.math.BigDecimal;

public class Poly {
    private int degree;
    private double[] coefficients;

    public Poly(double[] coefficients) {
        this.coefficients = new double[coefficients.length];
        System.arraycopy(coefficients, 0, this.coefficients, 0, coefficients.length);
        this.degree = this.coefficients.length - 1;
    }

    public BigDecimal eval(Double x) {
        BigDecimal p = BigDecimal.valueOf(0);
        for (int i = degree; i >= 0; i--)
            p = p.multiply(BigDecimal.valueOf(x)).add(BigDecimal.valueOf(coefficients[i]));
        return p;
    }
}
