import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cyclist {
    private SimpleIntegerProperty totalTime;
    private SimpleIntegerProperty currentTime = new SimpleIntegerProperty(0);
    private SimpleStringProperty name;

    public Cyclist(String name, int totalTime) {
        this.name = new SimpleStringProperty(name);
        this.totalTime = new SimpleIntegerProperty(totalTime);
    }

    public int getTotalTime() {
        return totalTime.get();
    }

    public int getCurrentTime() {
        return currentTime.get();
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime.set(currentTime);
    }

    public String getName() {
        return name.get();
    }
}
