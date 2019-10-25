package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DecimalFormat;

public class ResultsController {
    private Controller controller;
    private double result;
    private long time;

    @FXML
    private Label timeLabel;
    @FXML
    private Label resultLabel;

    void build() {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        Double timeInSeconds = ((time / 1000.0) / 1000.0) / 1000.0;
        timeLabel.setText("Twój czas: " + decimalFormat.format(timeInSeconds) + " s");
        decimalFormat.applyPattern("#.#");
        resultLabel.setText(decimalFormat.format(result));
    }

    void setController(Controller controller) {
        this.controller = controller;
    }

    void setTime(long time) {
        this.time = time;
    }

    void setResult(double result) {
        this.result = result;
    }
}
