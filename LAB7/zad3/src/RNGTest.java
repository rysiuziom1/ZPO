import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.LinkedList;

public class RNGTest {
    public static String testRNG(Path filePath) throws IOException {
        File file = filePath.toFile();

        InputStream inputStream = new FileInputStream(file);

        LinkedList<Integer> list = new LinkedList<>();
        int counter = 0, currentValue;
        byte[] buffer = new byte[4];
        while (inputStream.read(buffer) != -1) {
            currentValue = ByteBuffer.wrap(buffer).getInt();
            int index;
            if ((index = list.indexOf(currentValue)) != -1) {
                int lower = index + (counter > 1000 ? counter - 1000 : 0);
                String toReturn = "BAD SEQ, " + lower + ", " + counter + ", period length = " + (counter - lower);
                return toReturn;
            }
            if (counter > 1000)
                list.remove(0);
            list.add(currentValue);
            counter++;
        }
        return "GOOD SEQ";
    }
}
