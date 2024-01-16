package com.janjac.controllers.components;

import com.janjac.Main;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LogoImage implements Initializable {
    public ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image.setImage(new Image(Main.class.getResourceAsStream("icon.png")));
    }
}
