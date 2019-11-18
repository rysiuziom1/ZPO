import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Random;

public class RNG {
    public static void generateNumbersToBinaryFile(int quantity, double average, double stdev, Path filePath) throws IOException {
        File file = filePath.toFile();
        OutputStream writer = new FileOutputStream(file);
        Random rand = new Random();
        byte[] b = new byte[8];
        for (int i = 0; i < quantity; i++) {
            ByteBuffer.wrap(b).putDouble(rand.nextGaussian() * stdev + average);
            writer.write(b);
        }
        writer.close();
    }

    public static void binaryToTextFile(Path binaryFilePath, Path textFilePath) throws IOException {
        File binaryFile = binaryFilePath.toFile();
        File textFile = textFilePath.toFile();

        InputStream inputStream = new FileInputStream(binaryFile);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textFile));

        byte[] b = new byte[8];

        while (inputStream.read(b) != -1) {
            bufferedWriter.write(Double.toString(ByteBuffer.wrap(b).getDouble()).replace(".", ","));
            bufferedWriter.newLine();
        }
        inputStream.close();
        bufferedWriter.close();
    }
}
