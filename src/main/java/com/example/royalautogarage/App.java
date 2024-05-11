package com.example.royalautogarage;

import com.example.royalautogarage.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewfactory().ShowLoginWindow();


    }
}
