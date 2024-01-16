package com.janjac.threads;

import com.janjac.models.BusRoute;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusRouteFetchThread extends Thread {

    private final TableView<BusRoute> table;
    private String searchParam;

    public BusRouteFetchThread(TableView<BusRoute> table) {
        this.table = table;
        this.searchParam = "";
    }

    public BusRouteFetchThread(TableView<BusRoute> table, String searchParam) {
        this.table = table;
        this.searchParam = searchParam.toLowerCase();
    }

    @Override
    public void run() {
        Task<ArrayList<BusRoute>> fetchDataTask = new Task<ArrayList<BusRoute>>() {
            @Override
            protected ArrayList<BusRoute> call() {
                return BusRoute.all(BusRoute.class);
            }
        };
        fetchDataTask.setOnSucceeded(event -> {
            ArrayList<BusRoute> result = fetchDataTask.getValue();
            updateTableView(result);
        });

        fetchDataTask.setOnFailed(event -> {
            System.out.println("Task failed");
        });
        Platform.runLater(fetchDataTask);
    }

    private void updateTableView(ArrayList<BusRoute> data) {
        if (!searchParam.isEmpty()) data = new ArrayList<>(filter(data));
        ObservableList<BusRoute> observableData = FXCollections.observableArrayList(data);
        Platform.runLater(() -> {
            table.getItems().clear();
            table.getItems().addAll(observableData);
        });
    }

    private List<BusRoute> filter(ArrayList<BusRoute> data) {
        return data.stream()
                .filter(bus ->
                        (bus.getBusNumber().toLowerCase().contains(this.searchParam) ||
                                bus.getStartPoint().toLowerCase().contains(this.searchParam) ||
                                bus.getEndPoint().toLowerCase().contains(this.searchParam)))
                .collect(Collectors.toList());
    }

}