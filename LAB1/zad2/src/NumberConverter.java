import java.util.Scanner;

public class NumberConverter {
    public static void main(String[] args) {
        int number = 0b1101_1000;

        Scanner scn = new Scanner(System.in);
        System.out.println("Podaj podstawę systemu (szesnaście | dziesięć | trzy):");
        String base = scn.nextLine();
        String valueAfterConversion;

        switch(base) {
            case "szesnaście": case "szesnascie":
                valueAfterConversion = Integer.toHexString(number);
                break;
            case "dziesięć": case "dziesięc": case "dziesieć": case "dziesiec":
                valueAfterConversion = Integer.toString(number);
                break;
            case "trzy":
                valueAfterConversion = decimalToTernary(number);
                break;
            default:
                valueAfterConversion = "Błędne wejście!";
                break;
        }

        System.out.println(valueAfterConversion);
    }

    private static String decimalToTernary(int number) {
        StringBuilder sb = new StringBuilder();
        while(number > 0) {
            sb.append(number % 3);
            number /= 3;
        }
        sb.reverse();
        return sb.toString();
    }
}
