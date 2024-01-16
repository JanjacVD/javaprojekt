package com.janjac.fx;

import com.janjac.abstractions.Model;
import com.janjac.utils.AlertHelper;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell<T extends Model> extends TableCell<T, Void> {

    private final Button deleteButton = new Button("Delete");

    public ButtonCell(ObservableList<T> list) {
        deleteButton.setOnAction(event -> {
            T selectedModel = getTableView().getItems().get(getIndex());
            if(AlertHelper.confirm("Proceed with delete?")){
                selectedModel.delete();
                list.remove(selectedModel);
            }
        });
    }

    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            deleteButton.setStyle("-fx-background-color: #c70000; -fx-text-fill: #fff;");
            setGraphic(deleteButton);
        } else {
            setGraphic(null);
        }
    }
}
