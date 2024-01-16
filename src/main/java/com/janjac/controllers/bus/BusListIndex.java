package com.janjac.controllers.bus;

import com.janjac.fx.ButtonCell;
import com.janjac.interfaces.UpdateCallback;
import com.janjac.models.BusRoute;
import com.janjac.threads.BusRouteFetchThread;
import com.janjac.utils.AlertHelper;
import com.janjac.utils.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BusListIndex implements Initializable, UpdateCallback {
    @FXML
    public TableView<BusRoute> table;
    public TableColumn<BusRoute, String> startPoint;
    public TableColumn<BusRoute, String> busNumber;
    public TableColumn<BusRoute, String> endPoint;
    public TextField searchInput;
    public TableColumn deleteColumn;

    public void handleCreateNew(ActionEvent actionEvent) {
        Router.openNewWindow("Add new bus route", "bus-list-create.fxml");
    }

    @Override
    public void updateData() {
        refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        busNumber.setCellValueFactory(new PropertyValueFactory<>("busNumber"));
        startPoint.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        endPoint.setCellValueFactory(new PropertyValueFactory<>("endPoint"));
        deleteColumn.setCellFactory(params -> new ButtonCell<BusRoute>(table.getItems()));
        startPoint.setCellFactory(TextFieldTableCell.forTableColumn());
        busNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        endPoint.setCellFactory(TextFieldTableCell.forTableColumn());
        handleEditInit();
        final BusRouteFetchThread[] fetchThread = {new BusRouteFetchThread(table)};
        fetchThread[0] = new BusRouteFetchThread(table);
        fetchThread[0].start();
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (fetchThread[0].isAlive()) fetchThread[0].interrupt();
            fetchThread[0] = new BusRouteFetchThread(table, newValue);
            fetchThread[0].start();
        });
    }

    public void handleEditInit() {
        startPoint.setOnEditCommit(event -> {
            BusRoute busRoute = event.getTableView().getItems().get(event.getTablePosition().getRow());
            if (confirm()) {
                busRoute.setStartPoint(event.getNewValue());
                handleCommit(busRoute);
            }
        });
        endPoint.setOnEditCommit(event -> {
            BusRoute busRoute = event.getTableView().getItems().get(event.getTablePosition().getRow());
            if (confirm()) {
                busRoute.setEndPoint(event.getNewValue());
                handleCommit(busRoute);
            }
        });
        busNumber.setOnEditCommit(event -> {
            BusRoute busRoute = event.getTableView().getItems().get(event.getTablePosition().getRow());
            if (confirm()) {
                busRoute.setBusNumber(event.getNewValue());
                handleCommit(busRoute);
            }
        });
    }

    private boolean confirm() {
        return AlertHelper.confirm("Are you sure you want to edit the bus route?");
    }

    private void handleCommit(BusRoute busRoute) {
        busRoute.update();
    }

    private void refresh() {
        final BusRouteFetchThread[] fetchThread = {new BusRouteFetchThread(table)};
        fetchThread[0] = new BusRouteFetchThread(table);
        fetchThread[0].start();
    }

    public void handleReset(ActionEvent actionEvent) {
    }
}
