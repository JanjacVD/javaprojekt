package com.janjac.controllers.bus;

import com.janjac.Main;
import com.janjac.models.BusRoute;
import com.janjac.utils.AlertHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BusListCreate {
    public TextField busNumber;
    public TextField endPoint;
    public TextField startPoint;
    public Text busNumberError;
    public Text startPointError;
    public Text endPointError;

    public void handleSubmit(ActionEvent actionEvent) {
        if (!validateInputs() || !AlertHelper.confirm("Are you sure you want to create a new Bus route?")) return;
        BusRoute busRoute = new BusRoute("busNumber", busNumber.getText(), "endPoint", endPoint.getText(), "startPoint", startPoint.getText());
        busRoute.save();
        Stage stage = (Stage) busNumber.getScene().getWindow();
        stage.close();
    }

    private boolean validateInputs() {
        String num = busNumber.getText();
        String start = startPoint.getText();
        String end = endPoint.getText();
        if (num.isEmpty()) busNumberError.setText("Bus number must not be empty!");
        else if (!num.matches("\\d+")) busNumberError.setText("Bus number should include only digits!");
        else busNumberError.setText("");
        if (start.isEmpty()) startPointError.setText("Start point must not be empty!");
        else startPointError.setText("");
        if (end.isEmpty()) endPointError.setText("End point must not be empty!");
        endPointError.setText("");
        return (startPointError.getText().isEmpty() && endPointError.getText().isEmpty() && busNumberError.getText().isEmpty());
    }
}
