package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Feedback;
import com.example.royalautogarage.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class feebackcontoller implements Initializable {
    public TextField service_fld;
    public TextField appoitmentdate_btn;
    public TextField qualityservice_fld;
    public TextField complain_fld;
    public TextField vehicle_fld;
    public TextField experience_fld;
    public Button subbmit_btn;
    public TextField servviceexperience_fld;
    public TextField appoinmentstatusfield;
    public Label status_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subbmit_btn.setOnAction(event -> submitFeedback());

    }
    private void submitFeedback() {
        // Retrieve data from text fields and trim whitespace
        String serviceName = service_fld.getText().trim();
        String appointmentStatus = appoinmentstatusfield.getText().trim();
        String qualityOfService = qualityservice_fld.getText().trim();
        String complain = complain_fld.getText().trim();
        String wholeServiceExperience = servviceexperience_fld.getText().trim();
        String experienceWithMechanics = experience_fld.getText().trim();
        String vehicleStatus = vehicle_fld.getText().trim();

        // Check if any field is empty
        if (serviceName.isEmpty() || appointmentStatus.isEmpty() || qualityOfService.isEmpty() ||
                wholeServiceExperience.isEmpty() || experienceWithMechanics.isEmpty() || vehicleStatus.isEmpty() ||
                (appointmentStatus.equalsIgnoreCase("yes") && complain.isEmpty())) { // Assuming "yes" means they had an appointment and a complaint is expected
            // Display error message indicating missing information
            status_lbl.setText("Please fill in all required fields."); // Assuming there's a Label with fx:id=status_lbl for status messages
            return;  // Stop the method execution if any field is empty
        }

        // Proceed with feedback submission if all fields are filled
        try {
            Feedback feedback = new Feedback(serviceName, appointmentStatus, qualityOfService, complain, wholeServiceExperience, experienceWithMechanics, vehicleStatus);
            int customerId = Model.getInstance().getAuthenticatedCustomerId();
            Model.getInstance().getDatabaseDriver().submitFeedback(feedback, customerId);

            // Clear the form fields and show success message
            clearFormFields();
            status_lbl.setText("Feedback submitted successfully!");
        } catch (Exception e) {
            status_lbl.setText("Failed to submit feedback: " + e.getMessage());
        }
    }

    private void clearFormFields() {
        // Clear all text fields
        service_fld.clear();
        appoinmentstatusfield.clear();
        qualityservice_fld.clear();
        complain_fld.clear();
        servviceexperience_fld.clear();
        experience_fld.clear();
        vehicle_fld.clear();
    }


}

