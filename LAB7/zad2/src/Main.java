import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        assert Double.parseDouble(args[2]) > 0 : "stdev mniejsze od 0";
        RNG.generateNumbersToBinaryFile(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Paths.get("test.bin"));
        RNG.binaryToTextFile(Paths.get("test.bin"), Paths.get("test2.txt"));
    }
}
