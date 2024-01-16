package com.janjac.controllers.bus;

import com.janjac.fx.ButtonCell;
import com.janjac.models.BusRoute;
import com.janjac.models.BusRoutePeriod;
import com.janjac.threads.BusRouteScheduleFetchThread;
import com.janjac.threads.SetBusRouteToComboBoxThread;
import com.janjac.utils.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BusRouteScheduleIndex implements Initializable {
    public TextField searchInput;
    public TableView<BusRoutePeriod> table;
    public TableColumn<BusRoutePeriod, BusRoute> busNumber;
    public TableColumn<BusRoutePeriod, String> startTime;
    public TableColumn<BusRoutePeriod, String> endTime;
    public TableColumn<BusRoutePeriod, Void> deleteColumn;
    public String groupBy;
    private final ArrayList<Integer> filters = new ArrayList<Integer>();

    public void handleCreateNew(ActionEvent actionEvent) {
    }

    private void refresh() {
        Thread thread = new Thread(new BusRouteScheduleFetchThread(filters, groupBy, table));
        Thread thread2 = new Thread(new SetBusRouteToComboBoxThread(busNumber));
        thread.start();
        thread2.start();
    }

    public void handleReset(ActionEvent actionEvent) {
        filters.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        busNumber.setCellValueFactory(new PropertyValueFactory<>("busRoute"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        deleteColumn.setCellFactory(params -> new ButtonCell<BusRoutePeriod>(table.getItems()));
        startTime.setCellFactory(TextFieldTableCell.forTableColumn());
        endTime.setCellFactory(TextFieldTableCell.forTableColumn());
        handleEditInit();
        refresh();
    }

    public void handleEditInit() {
        startTime.setOnEditCommit(event -> {
            BusRoutePeriod busRoute = event.getTableView().getItems().get(event.getTablePosition().getRow());
            if (confirm()) {
                busRoute.setStartTime(event.getNewValue());
                handleCommit(busRoute);
            }
        });
        endTime.setOnEditCommit(event -> {
            BusRoutePeriod busRoute = event.getTableView().getItems().get(event.getTablePosition().getRow());
            if (confirm()) {
                busRoute.setEndTime(event.getNewValue());
                handleCommit(busRoute);
            }
        });
        busNumber.setOnEditCommit(event -> {
            BusRoutePeriod busRoute = event.getTableView().getItems().get(event.getTablePosition().getRow());
            if (confirm()) {
                busRoute.setBusRoute(event.getNewValue());
                busRoute.setBusRouteId(event.getNewValue().getId());
                handleCommit(busRoute);
            }
        });
    }

    private boolean confirm() {
        return AlertHelper.confirm("Are you sure you want to edit the bus route schedule?");
    }

    private void handleCommit(BusRoutePeriod busRoute) {
        busRoute.update();
    }
}
