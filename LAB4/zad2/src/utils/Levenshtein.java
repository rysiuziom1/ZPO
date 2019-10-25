package utils;

public class Levenshtein {
    public static int LevQWERTY(String firstWord, String secondWord) {
        int[][] levTable = new int[firstWord.length() + 1][secondWord.length() + 1];

        for (int i = 0; i <= firstWord.length(); i++)
            levTable[i][0] = i;
        for (int i = 0; i <= secondWord.length(); i++)
            levTable[0][i] = i;

        int cost;
        for (int i = 1; i <= firstWord.length(); i++) {
            for (int j = 1; j <= secondWord.length(); j++) {
                if (firstWord.charAt(i - 1) == secondWord.charAt(j - 1)) cost = 0;
                else cost = 1;
                levTable[i][j] = Math.min(Math.min(levTable[i - 1][j] + 1, levTable[i][j - 1] + 1), levTable[i - 1][j - 1] + cost);
            }
        }
        return levTable[firstWord.length()][secondWord.length()];
    }
}
