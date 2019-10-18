package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cyclist implements Comparable<Cyclist>{
    private SimpleStringProperty name;
    private SimpleIntegerProperty currentTime = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty totalTime;

    Cyclist(String name, int totalTime) {
        this.name = new SimpleStringProperty(name);
        this.totalTime = new SimpleIntegerProperty(totalTime);
    }

    @Override
    public String toString() {
        return getName() + " " + getCurrentTime();
    }

    @Override
    public int compareTo(Cyclist cyclist) {
        return Integer.compare(this.getTotalTime(), cyclist.getTotalTime());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    int getCurrentTime() {
        return currentTime.get();
    }

    public SimpleIntegerProperty currentTimeProperty() {
        return currentTime;
    }

    void setCurrentTime(int currentTime) {
        this.currentTime.set(currentTime);
    }

    int getTotalTime() {
        return totalTime.get();
    }

    public SimpleIntegerProperty totalTimeProperty() {
        return totalTime;
    }
}
