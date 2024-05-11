package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Appointment;
import com.example.royalautogarage.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Appoitmnet implements Initializable {
    public TextField dateofappoinment_txt;
    public TextField timeofappoinemtnt_txt;
    public TextField vehregnumber_txt;
    public Button book_btn;
    public TextField pstpndate_txt;
    public TextField pstpntime_txt;
    public TextField pstpnvehicle_txt;
    public TextField newPstpndate_txt;
    public Button postpone_btn;
    public TableView postponedapoinments_table;
    public TableView<Appointment> awaitingAppointmentsTable;
    public TableColumn <Appointment,Number>colAppointmentId;
    public TableColumn <Appointment,String>colAppointmentDate;
    public TableColumn <Appointment,String>colAppointmentTime;
    public TableColumn <Appointment,String>colVehicleRegNum;
    public TextField appoinemt_id;
    public TableView<Appointment> postponedAppointmentsTable;
    public TableColumn <Appointment,Number>colPostponedAppointmentId;
    public TableColumn <Appointment,String>colPostponedAppointmentDate;
    public TableColumn<Appointment,String> colPostponedAppointmentTime;
    public TableColumn<Appointment,String> colPostponedVehicleRegNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ///the awaiting appoinments table view
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAppointmentDate.setCellValueFactory(new PropertyValueFactory<>("dateOfAppointment"));
        colAppointmentTime.setCellValueFactory(new PropertyValueFactory<>("timeOfAppointment"));
        colVehicleRegNum.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        loadAwaitingAppointments();
        // postopned appoitment table view
        colPostponedAppointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPostponedAppointmentDate.setCellValueFactory(new PropertyValueFactory<>("dateOfAppointment"));
        colPostponedAppointmentTime.setCellValueFactory(new PropertyValueFactory<>("timeOfAppointment"));
        colPostponedVehicleRegNum.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        loadAwaitingAppointments();
        loadPostponedAppointments();

        book_btn.setOnAction(event -> bookAppointment());
        // postpone_btn.setOnAction(event -> postponeAppointment());
        postpone_btn.setOnAction(event -> postponeAppointment());
    }
    private void bookAppointment() {
        int customerId = Model.getInstance().getAuthenticatedCustomerId();
        String vehicleRegNum = vehregnumber_txt.getText();
        String date = dateofappoinment_txt.getText(); // Get the date as a string directly from the text field
        String time = timeofappoinemtnt_txt.getText(); // Get the time as a string directly from the text field

        Model.getInstance().getDatabaseDriver().bookAppointment(customerId, vehicleRegNum, date, time);
        loadAwaitingAppointments();
        vehregnumber_txt.clear();
        dateofappoinment_txt.clear();
        timeofappoinemtnt_txt.clear();

        // Refresh the UI or show a confirmation message
    }
    private void loadAwaitingAppointments() {
        // Assuming Model.getInstance().getAuthenticatedCustomerId() returns the ID of the currently logged-in user
        int customerId = Model.getInstance().getAuthenticatedCustomerId();

        // Fetch awaiting appointments for the logged-in user
        List<Appointment> appointments = Model.getInstance().getDatabaseDriver().getAwaitingAppointments(customerId);

        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList(appointments);
        awaitingAppointmentsTable.setItems(appointmentList);
    }
    private void postponeAppointment() {
        try {
            int appointmentId = Integer.parseInt(appoinemt_id.getText());
            String newDate = newPstpndate_txt.getText();
            String newTime = pstpntime_txt.getText();

            // Assuming getDatabaseDriver() returns an instance of DatabaseDriver connected to your DB
            Model.getInstance().getDatabaseDriver().postponeAppointment(appointmentId, newDate, newTime);

            // Optionally, refresh the table of appointments to show the updated status
            loadAwaitingAppointments(); // You might want to create a method to load postponed appointments as well
        } catch (NumberFormatException e) {
            // Handle case where appointment ID is not an integer
            System.err.println("Appointment ID must be a number.");
        }
    }
    //table view of postponed appointmnets
    private void loadPostponedAppointments() {
        int customerId = Model.getInstance().getAuthenticatedCustomerId();
        List<Appointment> postponedAppointments = Model.getInstance().getDatabaseDriver().getPostponedAppointments(customerId);
        ObservableList<Appointment> postponedList = FXCollections.observableArrayList(postponedAppointments);
        postponedAppointmentsTable.setItems(postponedList);
    }


}
