import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(RNGTest.testRNG(Paths.get("gen1.dat")));
        System.out.println(RNGTest.testRNG(Paths.get("gen2.dat")));
        System.out.println(RNGTest.testRNG(Paths.get("gen3.dat")));
    }
}
