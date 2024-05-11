package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Clientcontroller implements Initializable {
    public BorderPane client_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewfactory().getClientselectedmenuitem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "services"->client_parent.setCenter(Model.getInstance().getViewfactory().getServices());
                case "appointment"->client_parent.setCenter(Model.getInstance().getViewfactory().getAppointment());
                case "pay"->client_parent.setCenter(Model.getInstance().getViewfactory().getPay());
                case "feedback"->client_parent.setCenter(Model.getInstance().getViewfactory().getFeedback());
                case "vehicle"->client_parent.setCenter(Model.getInstance().getViewfactory().getVehicle());
                default -> client_parent.setCenter(Model.getInstance().getViewfactory().getHome());
            }

        });
    }

}
