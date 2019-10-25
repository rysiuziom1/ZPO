package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import utils.Levenshtein;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Controller {
    @FXML
    private StackPane mainStackPane;

    @FXML
    private Label question1;
    @FXML
    private Label question2;
    @FXML
    private Label question3;
    @FXML
    private Label question4;
    @FXML
    private Label question5;

    @FXML
    private TextField answer1;
    @FXML
    private TextField answer2;
    @FXML
    private TextField answer3;
    @FXML
    private TextField answer4;
    @FXML
    private TextField answer5;

    private Label[] questionArray;
    private TextField[] answersArray;

    private double result = 0.0;
    private static final int questionNumber = 5;
    private static final int totalQuestionNumber = 10;
    private HashMap<String, ArrayList<String>> database;
    private long time;

    @FXML
    public void initialize() {
        time = System.nanoTime();
        Gson gson = new Gson();
        try {
            questionArray = new Label[]{question1, question2, question3, question4, question5};
            answersArray = new TextField[]{answer1, answer2, answer3, answer4, answer5};
            Random rand = new Random();
            Reader reader = new FileReader("src/PolEngTest.json");
            Type genericType = new TypeToken<HashMap<String, List<String>>>() {
            }.getType();
            database = gson.fromJson(reader, genericType);
            HashSet<String> questions = new HashSet<>();
            while (questions.size() < 5) {
                questions.add((String) database.keySet().toArray()[rand.nextInt(totalQuestionNumber)]);
            }
            Iterator<String> iterator = questions.iterator();
            for (Label l : questionArray) {
                if (iterator.hasNext())
                    l.setText(iterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendQuiz() throws IOException {
        time = System.nanoTime() - time;

        Map<String, String> questionsAndAnswers = new HashMap<>();
        for (int i = 0; i < questionArray.length; i++) {
            questionsAndAnswers.put(questionArray[i].getText(), answersArray[i].getText());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(questionsAndAnswers);
        FileWriter fileWriter = new FileWriter("src/jacek_wychowalek.json");
        fileWriter.write(json);
        fileWriter.close();

        for (String q : questionsAndAnswers.keySet()) {
            String answer = questionsAndAnswers.get(q);
            for (String a : database.get(q)) {
                int levDistance = Levenshtein.LevQWERTY(answer, a);
                result += (levDistance > 1) ? 0.0 : ((levDistance > 0) ? 0.5 : 1.0);
                if (levDistance <= 1) break;
            }
        }

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/resultsView.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResultsController resultsController = loader.getController();
        resultsController.setController(this);
        resultsController.setTime(time);
        resultsController.setResult(result);
        resultsController.build();
        setScreen(pane);
    }

    private void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
}
