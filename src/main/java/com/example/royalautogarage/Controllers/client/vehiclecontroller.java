package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class vehiclecontroller implements Initializable {
    public TextField vehicleregistration_fld;
    public TextField vehichlemodel_fld;
    public TextField colour_fld;
    public TextField chasis_fld;
    public Button addvehicle_btn;
    public Label status_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addvehicle_btn.setOnAction(event->addVehicle());

    }
    private void addVehicle() {
        // Retrieve data from text fields
        String registrationNumber = vehicleregistration_fld.getText().trim();
        String model = vehichlemodel_fld.getText().trim();
        String color = colour_fld.getText().trim();
        String chassis = chasis_fld.getText().trim();

        // Validate inputs
        if (registrationNumber.isEmpty() || model.isEmpty() || color.isEmpty() || chassis.isEmpty()) {
            // Display error message indicating missing information
            status_lbl.setText("Please fill in all fields to add a vehicle.");
            System.out.println("Failed to add vehicle: Missing details.");
            return; // Stop the method execution if validation fails
        }

        // All fields are filled, proceed to add the vehicle
        try {
            Model.getInstance().getDatabaseDriver().addVehicle(registrationNumber, model, color, chassis);

            // Optional: Clear the text fields after successful insertion and display a success message
            vehicleregistration_fld.clear();
            vehichlemodel_fld.clear();
            colour_fld.clear();
            chasis_fld.clear();
            status_lbl.setText("Vehicle added successfully!");
            System.out.println("Vehicle added successfully!");
        } catch (Exception e) {
            // Handle any exceptions, e.g., database errors
            status_lbl.setText("Failed to add vehicle: " + e.getMessage());
            System.err.println("Failed to add vehicle: " + e.getMessage());
        }
    }

}
