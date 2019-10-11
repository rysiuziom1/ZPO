import javax.swing.*;
import java.util.HashMap;

public class Expressions {
    public static void main(String[] args) {
        String expression = JOptionPane.showInputDialog("Please input algebraic expression");
        if(!expression.matches("-?[0-9]{1,2} [+\\-*] -?[0-9]{1,2} [+\\-*] -?[0-9]{1,2}"))
            throw new IllegalArgumentException("Wrong algebraic expression");
        String wordExpression = algebraicToWordExpression(expression);
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, wordExpression);
        System.exit(0);
    }

    public static String algebraicToWordExpression(String algebraicExpression) {
        //create numbers's map
        HashMap<String, String> numberMap = new HashMap<>();
        String[] belowTwenty = {"zero", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem",
                "dziewięć", "dziesięć", "jedenaście", "dwanaście", "trzynaście", "cztenaście", "piętnaście",
                "szesnaście", "siedemnaście", "osiemnaście", "dziwiętnaście"};
        String[] teens = {"", "", "dwadzieścia", "trzydzieście", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
                "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};

        for (int i = 0; i < 100; i++) {
            if (i < 20)
                numberMap.put(String.valueOf(i), belowTwenty[i]);
            else {
                numberMap.put(String.valueOf(i), teens[i / 10] + (i % 10 == 0 ? "" : " " + belowTwenty[i % 10]));
            }
        }
        numberMap.put("+", "plus");
        numberMap.put("*", "razy");
        numberMap.put("-", "minus");
        String[] exprComponents = algebraicExpression.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s : exprComponents) {
            if(s.startsWith("-") && s.length() > 1) {
                sb.append("minus").append(" ");
                s = s.substring(1);
            }
            if(s.startsWith("0") && s.length() > 1)
                s = String.valueOf(s.charAt(1));
            sb.append(numberMap.get(s)).append(" ");
        }
        return sb.toString();
    }
}
