package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ResultCounterTest {

    static Map<String, ArrayList<String>> database;

    @BeforeClass
    public static void createDatabase() throws FileNotFoundException {
        database = new HashMap<>();
        Gson gson = new Gson();
        Reader reader = new FileReader("src/PolEngTest.json");
        Type genericType = new TypeToken<HashMap<String, List<String>>>() {
        }.getType();
        database = gson.fromJson(reader, genericType);
    }

    @Test
    public void checkTest() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("krzyczeć", "scream");
        questionsAndAnswers.put("skakać", "jump");
        questionsAndAnswers.put("złośliwy", "mean");
        questionsAndAnswers.put("żal", "regret");
        questionsAndAnswers.put("wątpliwy", "doubtful");
        assertEquals(5.0d, ResultCounter.check(questionsAndAnswers, database), 0);

        questionsAndAnswers.clear();
        questionsAndAnswers.put("krzyczeć", "screm");
        questionsAndAnswers.put("skakać", "jump");
        questionsAndAnswers.put("złośliwy", "men");
        questionsAndAnswers.put("żal", "reget");
        questionsAndAnswers.put("wątpliwy", "doubtfull");
        assertEquals(3.0d, ResultCounter.check(questionsAndAnswers, database), 0);
    }
}