package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class registercontroller implements Initializable {
    public TextField firstnametxt;
    public TextField lastnametxt;
    public TextField adreesstxt;
    public TextField paswordtxt;
    public TextField confirmpaswordtxt;
    public Button Register_btn;
     public TextField phonenumber;
    public Label error;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // login_lbl.setOnMouseClicked(event->onshowlogin());
        Register_btn.setOnAction(event -> registerUser());

    }
    private void registerUser() {
        String firstName = firstnametxt.getText();
        String lastName = lastnametxt.getText();
        String address = adreesstxt.getText();
        String password = paswordtxt.getText();
        String confirmPassword = confirmpaswordtxt.getText();
        String PhoneNumber =phonenumber.getText();

        if (!password.equals(confirmPassword)) {
            // Handle error: passwords do not match
            error.setText("password dont match");
            paswordtxt.setText("");
            return;
        }

        // Attempt to add a new user to the database
        boolean success = Model.getInstance().getDatabaseDriver().addNewUser(firstName, lastName, address, password,PhoneNumber);

        if (success) {
            // Registration successful, you can close the registration window and open the login window
            onshowlogin();
        } else {
            // Handle registration failure
            System.out.println("Registration failed.");
        }
    }

    private void onshowlogin(){
        Stage stage=(Stage) firstnametxt.getScene().getWindow();
        Model.getInstance().getViewfactory().CloseStage(stage);
        Model.getInstance().getViewfactory().ShowLoginWindow();
    }
}
