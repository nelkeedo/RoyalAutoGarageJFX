package com.example.royalautogarage.Controllers;

import com.example.royalautogarage.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField usernametxt;
    public Button loginbutton;
    public TextField paswwordtxt;
    public Label error_lbl;
    public Label register_lbl;
    public Label Adminlbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginbutton.setOnAction(event->OnLogin());
        register_lbl.setOnMouseClicked(Event->showregister());
    }
    private void OnLogin(){
        Stage stage=(Stage) paswwordtxt.getScene().getWindow();
        Model.getInstance().evaluatecreditials(usernametxt.getText(),paswwordtxt.getText());
        if(Model.getInstance().getClientloginSuccesflag()){
            Model.getInstance().getViewfactory().ShowClientWindow();
            Model.getInstance().getViewfactory().CloseStage(stage);
        }else{
            usernametxt.setText("");
            paswwordtxt.setText("");
            error_lbl.setText("incorrect details");
        }



    }

    private void showregister(){
        Stage stage=(Stage) paswwordtxt.getScene().getWindow();
        Model.getInstance().getViewfactory().CloseStage(stage);
        Model.getInstance().getViewfactory().Showregisterwindow();
    }

}
