package com.janjac.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.function.Predicate;

public class SearchableDropdown<T1, T2> extends TableCell<T1, T2> {

    private final HBox container = new HBox();
    private final TextField searchInput = new TextField();
    private final ComboBox<T2> comboBox = new ComboBox<>();
    private StringConverter<T2> converter;

    public SearchableDropdown(ArrayList<T2> data, StringConverter<T2> converter) {
        this.converter = converter;
        setupSearchableDropdown(data);
    }

    public SearchableDropdown() {
    }

    public void initializeData(ArrayList<T2> data, StringConverter<T2> converter) {
        this.converter = converter;
        setupSearchableDropdown(data);
    }

    private void setupSearchableDropdown(ArrayList<T2> data) {
        ObservableList<T2> options = FXCollections.observableArrayList(data);
        comboBox.setItems(options);

        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            comboBox.setItems(filterOptions(options, newValue));
        });

        comboBox.setConverter(converter);

        setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                startEdit();
            }
        });
        comboBox.setOnAction(event -> {
            commitEdit(comboBox.getValue());
            cancelEdit();
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        setText(null);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchInput, comboBox);
        searchInput.setMaxWidth(Double.MAX_VALUE);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        container.getChildren().clear();
        container.getChildren().add(vbox);
        comboBox.setPromptText("Select...");

        setGraphic(container);
        searchInput.requestFocus();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(null);
        setText(converter.toString(getItem()));
    }

    @Override
    public void updateItem(T2 item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setText(converter.toString(item));
            setGraphic(null);
        }
    }

    private ObservableList<T2> filterOptions(ObservableList<T2> options, String searchInput) {
        Predicate<T2> containsIgnoreCase = option ->
                option.toString().toLowerCase().contains(searchInput.toLowerCase());

        return options.filtered(containsIgnoreCase);
    }

    public void setOnAction(EventHandler<ActionEvent> onAction) {
        comboBox.setOnAction(onAction);
    }

    public void setDefaultValue(T2 value) {
        comboBox.setValue(value);
    }

}
