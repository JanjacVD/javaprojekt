package com.janjac;

import com.janjac.database.DbConnection;
import com.janjac.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    public void start(Stage stage){
        try {
            setPrimaryStage(stage);
            VBox root =  FXMLLoader.load(Main.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(root);

            getPrimaryStage().setScene(scene);
            getPrimaryStage().show();
            getPrimaryStage().setResizable(true);

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