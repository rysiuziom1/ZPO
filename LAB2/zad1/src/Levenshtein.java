import java.util.HashMap;

public class Levenshtein {
    public static void main(String[] args) {
        String s1 = "alma";
        String s2 = "alarm";
        System.out.println(LevQWERTY(s1, s2));
    }

    static double LevQWERTY(String firstWord, String secondWord) {
        HashMap<String, String> neighbors = new HashMap<>();
        String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

        for (String s : keyboard) {
            int j = 0;
            for (char c : s.toCharArray()) {
                StringBuilder sb = new StringBuilder();
                if (j - 1 >= 0)
                    sb.append(Character.toString(s.toCharArray()[j - 1]));
                if (j + 1 < s.length())
                    sb.append(Character.toString(s.toCharArray()[j + 1]));
                neighbors.put(Character.toString(c), sb.toString());
                j++;
            }
        }

        //System.out.println(neighbors);

        double[][] levTable = new double[firstWord.length() + 1][secondWord.length() + 1];

        for (int i = 0; i <= firstWord.length(); i++)
            levTable[i][0] = i;
        for (int i = 0; i <= secondWord.length(); i++)
            levTable[0][i] = i;

        double cost = 0.0;
        for (int i = 1; i <= firstWord.length(); i++) {
            for (int j = 1; j <= secondWord.length(); j++) {
                if (firstWord.charAt(i - 1) == secondWord.charAt(j - 1)) cost = 0.0;
                else if (neighbors.get(Character.toString(firstWord.charAt(i - 1))).contains(Character.toString(secondWord.charAt(j - 1))))
                    cost = 0.5;
                else cost = 1.0;

                levTable[i][j] = Math.min(Math.min(levTable[i - 1][j] + 1, levTable[i][j - 1] + 1), levTable[i - 1][j - 1] + cost);
            }
        }
        return levTable[firstWord.length()][secondWord.length()];
    }
}
