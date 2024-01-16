package com.janjac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    public void start(Stage stage){
        try {
            setPrimaryStage(stage);
            AnchorPane root =  FXMLLoader.load(Main.class.getResource("login-view.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Janjac projekt");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
            getPrimaryStage().setScene(scene);
            getPrimaryStage().setResizable(false);
            getPrimaryStage().show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized static Stage getStage(){
        return getPrimaryStage();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }
}