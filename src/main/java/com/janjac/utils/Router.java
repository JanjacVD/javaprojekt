package com.janjac.utils;

import com.janjac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Router {
    public static void navigate(String path) {
        try{
            AnchorPane root = FXMLLoader.load(Main.class.getResource(path));
            Scene scene = new Scene(root);
            Main.getPrimaryStage().setScene(scene);
            Main.getPrimaryStage().show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void openNewWindow(String title, String path) {
        // Create a new stage for the new window
        Stage newStage = new Stage();
        newStage.setTitle(title);
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
}
