package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class clientMenu  implements Initializable {
    public Button homebtn;
    public Button servicebtn;
    public Button appontmentbtn;
    public Button paybtn;
    public Button feedbackbtn;
    public Button vehiclesbtn;
    public Button logoutbtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addlisteners();
        logoutbtn.setOnAction(event->showloginwindow());
    }

        private void addlisteners(){
            homebtn.setOnAction(event-> onhome());
            servicebtn.setOnAction(event->onservice());
            appontmentbtn.setOnAction(event->onappointment());
            paybtn.setOnAction(event->onpay());
            feedbackbtn.setOnAction(event->onfeedback());
            vehiclesbtn.setOnAction(event->onvehicle());

        }
        private void onhome(){
            Model.getInstance().getViewfactory().getClientselectedmenuitem().set("home");

    }
         private void onservice(){
            Model.getInstance().getViewfactory().getClientselectedmenuitem().set("services");
    }
    private void  onappointment(){
        Model.getInstance().getViewfactory().getClientselectedmenuitem().set("appointment");
    }
    private void onpay(){
        Model.getInstance().getViewfactory().getClientselectedmenuitem().set("pay");
    }
    private void onfeedback(){
        Model.getInstance().getViewfactory().getClientselectedmenuitem().set("feedback");
    }
    private void onvehicle(){
        Model.getInstance().getViewfactory().getClientselectedmenuitem().set("vehicle");
    }
    private void showloginwindow(){
        Stage stage=(Stage) feedbackbtn.getScene().getWindow();
        Model.getInstance().getViewfactory().CloseStage(stage);

    }
}

