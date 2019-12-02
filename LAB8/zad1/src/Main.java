import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MaxSearchAlgorithms asd = new MaxSearchAlgorithms();
        int[] data = {4, 10, 3, 7, 4, 1, 6, 2};
        Arrays.stream(asd.getClass().getDeclaredMethods()).filter(e -> e.getName().contains("Scan")).forEach(e -> {
            try {
                System.out.println(e.invoke(asd, data));
            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        });
    }
}
