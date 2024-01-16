package com.janjac.controllers.components;

import com.janjac.utils.Router;
import javafx.event.ActionEvent;

public class TeacherNavigation {
    public void navigateToSubjectList(ActionEvent actionEvent) {
    }

    public void navigateToSubjectScheduleList(ActionEvent actionEvent) {
    }

    public void navigateToBusList(ActionEvent actionEvent) {
        Router.navigate("bus-list-index.fxml");
    }

    public void navigateToBusRouteList(ActionEvent actionEvent) {
    }

    public void navigateToAttendanceList(ActionEvent actionEvent) {
    }

    public void navigateToAttendanceInput(ActionEvent actionEvent) {
    }

    public void handleLogout(ActionEvent actionEvent) {
    }
}
