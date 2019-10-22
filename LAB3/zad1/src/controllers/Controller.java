package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Controller {
    @FXML
    private TableView<Cyclist> raceView;

    @FXML
    private TableView<Cyclist> finishersView;

    @FXML
    private TableColumn raceNameColumn;

    @FXML
    private TableColumn raceTimeColumn;

    @FXML
    private TableColumn finisherNameColumn;

    @FXML
    private TableColumn finisherTimeColumn;

    @FXML
    private void initialize() throws IOException {
        final int oneSecSimulationInMillis = 1000 / 25;
        raceView.setEditable(true);
        finishersView.setEditable(true);

        raceNameColumn.setCellValueFactory(new PropertyValueFactory<Cyclist, String>("name"));
        raceTimeColumn.setCellValueFactory(new PropertyValueFactory<Cyclist, Integer>("currentTime"));
        finisherNameColumn.setCellValueFactory(new PropertyValueFactory<Cyclist, String>("name"));
        finisherTimeColumn.setCellValueFactory(new PropertyValueFactory<Cyclist, Integer>("totalTime"));

        Logger logger = Logger.getLogger("RaceSimulatorLogger");
        FileHandler fileHandler = new FileHandler("logs.txt");
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
        fileHandler.setFormatter(new SimpleFormatter());

        URL cyclistsNamesFile = new URL("http://szgrabowski.iis.p.lodz.pl/zpo19/nazwiska.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(cyclistsNamesFile.openStream()));
        List<String> listOfNames = new ArrayList<>();
        String name;
        while ((name = br.readLine()) != null) {
            listOfNames.add(name);
        }
        Set<String> cyclistsNames = new HashSet<>();
        Random rand = new Random();
        while (cyclistsNames.size() < 12) {
            int position = rand.nextInt(listOfNames.size());
            cyclistsNames.add(listOfNames.get(position));
        }
        List<String> listOfCyclists = new ArrayList<>(cyclistsNames);

        ObservableList<Cyclist> cyclistList = FXCollections.observableArrayList();
        raceView.setItems(cyclistList);

        ObservableList<Cyclist> finishersList = FXCollections.observableArrayList();
        finishersView.setItems(finishersList);

        Queue<Cyclist> priorityQueue = new PriorityQueue<>();

        TimerTask startCyclist = new TimerTask() {
            @Override
            public void run() {
                synchronized (this) {
                    if (listOfCyclists.size() > 0) {
                        String cyclistName = listOfCyclists.remove(0);
                        long randomGaussian = Math.round(rand.nextGaussian() * 40 + 290);
                        int totalTime = (int) (randomGaussian > 350 ? 350 : randomGaussian < 240 ? 240 : randomGaussian);
                        Cyclist cyclist = new Cyclist(cyclistName, totalTime);
                        cyclistList.add(cyclist);
                        logger.info("Cyclist " + cyclist.getName() + " starts race.");
                    }
                }
            }
        };

        TimerTask refreshList = new TimerTask() {
            @Override
            public void run() {
                synchronized (this) {
                    int toRemove = -1;
                    for (Cyclist c : cyclistList) {
                        if (c.getTotalTime() != c.getCurrentTime()) {
                            c.setCurrentTime(c.getCurrentTime() + 1);
                        } else {
                            toRemove = cyclistList.indexOf(c);
                        }
                    }
                    if (toRemove > -1) {
                        Cyclist finished = cyclistList.remove(toRemove);
                        priorityQueue.offer(finished);
                        finishersList.clear();
                        Queue<Cyclist> tmpQueue = new PriorityQueue<>(priorityQueue);
                        for (int i = 0; i < 3; i++)
                            finishersList.add(tmpQueue.poll());
                        finishersList.add(finished);
                        logger.info("Cyclist " + finished.getName() + " finished race with time: " + finished.getTotalTime());
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(startCyclist, 0, oneSecSimulationInMillis * 60);
        timer.scheduleAtFixedRate(refreshList, 0, oneSecSimulationInMillis);
    }
}
