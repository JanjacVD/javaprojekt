package com.janjac.threads;

import com.janjac.models.BusRoute;
import com.janjac.models.BusRoutePeriod;
import com.janjac.utils.BusRouteStringConverted;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.converter.DefaultStringConverter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BusRouteScheduleFetchThread implements Runnable {

    private ArrayList<Integer> dayFilters;
    private String groupBy;
    private final TableView<BusRoutePeriod> table;

    public BusRouteScheduleFetchThread(ArrayList<Integer> dayFilters, String groupBy, TableView<BusRoutePeriod> table) {
        this.dayFilters = dayFilters;
        this.groupBy = groupBy;
        this.table = table;
    }

    private void handleUpdate() {
        Task<ArrayList<BusRoutePeriod>> fetchDataTask = new Task<ArrayList<BusRoutePeriod>>() {
            @Override
            protected ArrayList<BusRoutePeriod> call() {
                return BusRoutePeriod.all(BusRoutePeriod.class);
            }
        };
        fetchDataTask.setOnSucceeded(event -> {
            ArrayList<BusRoutePeriod> result = fetchDataTask.getValue();
            updateTableView(result);
        });

        fetchDataTask.setOnFailed(event -> {
            System.out.println("Task failed");
        });
        Platform.runLater(fetchDataTask);
    }

    private void updateTableView(ArrayList<BusRoutePeriod> data) {
//        if (!searchParam.isEmpty()) data = new ArrayList<>(filter(data));
        ObservableList<BusRoutePeriod> observableData = FXCollections.observableArrayList(data);
        Platform.runLater(() -> {
            table.getItems().clear();
            table.getItems().addAll(observableData);
        });
    }

    @Override
    public void run() {
        handleUpdate();
    }
}
