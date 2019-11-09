import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Bisection {
    public static Double findRoot(Poly poly, Double startValue, Double endValue, Double step) {
        BigDecimal startValueBD = BigDecimal.valueOf(startValue);
        BigDecimal endValueBD = BigDecimal.valueOf(endValue);
        BigDecimal stepBD = BigDecimal.valueOf(step);
        List<BigDecimal> listOfXs = Lists.newArrayList();
        while (startValueBD.compareTo(endValueBD) <= 0) {
            listOfXs.add(startValueBD);
            startValueBD = startValueBD.add(stepBD);
        }
        BigDecimal epsBD = BigDecimal.valueOf(0.000001d);

        int xIndex = Collections.binarySearch(
                listOfXs,
                BigDecimal.valueOf(0.0d),
                (o1, o2) -> poly.eval(o1.doubleValue()).subtract(o2).abs().compareTo(epsBD) <= 0
                        ? 0
                        : poly.eval(o1.doubleValue()).compareTo(o2)
        );
        return listOfXs.get(xIndex).doubleValue();
    }
}
