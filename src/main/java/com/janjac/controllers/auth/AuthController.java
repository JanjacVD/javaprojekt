package com.janjac.controllers.auth;

import com.janjac.Main;
import com.janjac.exceptions.CredentialsFileException;
import com.janjac.exceptions.FailedAuthException;
import com.janjac.exceptions.InvalidFileExtension;
import com.janjac.global.AuthStore;
import com.janjac.models.User;
import com.janjac.utils.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AuthController {

    public Button selectFile;
    public Button loginBtn;
    public Text errorText;
    private Credentials credentials;

    public void handleFileSelect(ActionEvent actionEvent) {
        UiFileReader fileReader = new UiFileReader("Select a login txt file", ".txt");
        try{
            String result = fileReader.readFile();
            this.credentials = CredentialsExtractor.extract(result);
            errorText.setText("");
            this.loginBtn.setDisable(false);
        }catch (InvalidFileExtension | CredentialsFileException e){
            errorText.setText(e.getMessage());
        }
    }


    public void handleLoginAttempt(ActionEvent actionEvent)  {
        try{
            User user = Authenticator.authenticate(this.credentials.getUsername(), this.credentials.getPassword());
            AuthStore.getInstance().setAuthenticatedUser(user);
            renderIndexScreen();
        }
        catch (FailedAuthException e){
            this.errorText.setText(e.getMessage());
            this.loginBtn.setDisable(true);
            this.credentials = null;
        }
    }

    public void renderIndexScreen(){
        boolean isStudent = AuthStore.getInstance().getAuthenticatedUser().isStudent();
        String indexFile = isStudent ? "student-index-view.fxml" : "teacher-index-view.fxml";
        Router.navigate(indexFile);
    }
}
