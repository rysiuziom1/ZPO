package utils;

import java.util.ArrayList;
import java.util.Map;

public class ResultCounter {
    public static double check(Map<String, String> questionsAndAnswers, Map<String, ArrayList<String>> database) {
        double result = 0;

        for (String q : questionsAndAnswers.keySet()) {
            String answer = questionsAndAnswers.get(q);
            for (String a : database.get(q)) {
                int levDistance = Levenshtein.LevQWERTY(answer.toLowerCase(), a);
                result += (levDistance > 1) ? 0.0 : ((levDistance > 0) ? 0.5 : 1.0);
                if (levDistance <= 1) break;
            }
        }

        return result;
    }
}
