package com.janjac.threads;

import com.janjac.fx.SearchableDropdown;
import com.janjac.models.BusRoute;
import com.janjac.models.BusRoutePeriod;
import com.janjac.utils.BusRouteStringConverted;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;

import java.util.ArrayList;

public class SetBusRouteToComboBoxThread implements Runnable{
    private final TableColumn<BusRoutePeriod, BusRoute> busNumber;

    public SetBusRouteToComboBoxThread(TableColumn<BusRoutePeriod, BusRoute> busNumber){
        this.busNumber = busNumber;
    }
    @Override
    public void run() {
    handleBusRouteFetch();
    }
    private void handleBusRouteFetch() {
        Task<ArrayList<BusRoute>> fetchDataTask = new Task<ArrayList<BusRoute>>() {
            @Override
            protected ArrayList<BusRoute> call() {
                return BusRoute.all(BusRoute.class);
            }
        };
        fetchDataTask.setOnSucceeded(event -> {
            ArrayList<BusRoute> result = fetchDataTask.getValue();
            updateOptions(result);
        });

        fetchDataTask.setOnFailed(event -> {
            System.out.println("Task failed");
        });
        Platform.runLater(fetchDataTask);
    }
    private void updateOptions(ArrayList<BusRoute> data){
        Platform.runLater(() -> {
            busNumber.setEditable(true);
            busNumber.setCellFactory(params -> new SearchableDropdown<BusRoutePeriod, BusRoute>(data, new BusRouteStringConverted()));
        });
    }
}
