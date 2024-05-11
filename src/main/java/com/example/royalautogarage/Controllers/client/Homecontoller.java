package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Homecontoller implements Initializable {
    public Label usernamelbl;
    public Label datelbl;

    public ListView recentservices_listview;
    public ListView recentappointments_listview;
    public ListView billing_listview;
    public TableView vehicleTableView;
    public TableColumn registrationColumn;
    public TableColumn modelColumn;
    public TableColumn colorColumn;
    public TableColumn chassisColumn;
    public TableView <Appointment>RecentappointmentsTable;
    public TableColumn <Appointment,String>Recentstatuscolums;
    public TableColumn <Appointment,String>Recentappointmentdatecolum;
    public TableColumn <Appointment,String>Recentappointmenttimecolumn;
    public TableColumn <Appointment,String>Recentappointmentvehiclecolumn;
    public TableView<RecentServiceItem> recentServicesTable;
    public TableColumn <RecentServiceItem,String>serviceNameColumn;
    public TableColumn <RecentServiceItem,String>vehicleRegColumn;
    public Button refreshbtn;
    private ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
    private ObservableList<RecentServiceItem> recentServicesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //recent table of appoinments
        initializeRecentappoinmentTable();
        loadAllUserAppointments();


        ///vehicle table
        initializeVehicleTable();
        loadVehiclesForCurrentUser();

        LocalDate currentDate = LocalDate.now();
        datelbl.setText(String.valueOf(currentDate));
        usernamelbl.setText(Model.getInstance().getClient().firstNameProperty().get());

        // recent services
        initializerecentservicesTable();
        loadRecentServices();

    }
        /// displaying vehicles in the listview of vehicles
    private void initializeVehicleTable() {
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        chassisColumn.setCellValueFactory(new PropertyValueFactory<>("chassis"));
    }

    private void loadVehiclesForCurrentUser() {
        int customerId = Model.getInstance().getAuthenticatedCustomerId();
        List<Vehicle> vehicles = Model.getInstance().getDatabaseDriver().getVehiclesByCustomerId(customerId);
        vehicleTableView.setItems(FXCollections.observableArrayList(vehicles));
    }
//display recent appoinments
private void initializeRecentappoinmentTable() {
    RecentappointmentsTable.setItems(appointmentsList);
    Recentstatuscolums.setCellValueFactory(new PropertyValueFactory<>("status"));
    Recentappointmentdatecolum.setCellValueFactory(new PropertyValueFactory<>("dateOfAppointment"));
    Recentappointmenttimecolumn.setCellValueFactory(new PropertyValueFactory<>("timeOfAppointment"));
    Recentappointmentvehiclecolumn.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
}
private void initializerecentservicesTable(){
    recentServicesTable.setItems(recentServicesList); // Bind the observable list to the TableView once
    serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
    vehicleRegColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));

}

    private void loadAllUserAppointments() {
        int customerId = Model.getInstance().getAuthenticatedCustomerId();
        List<Appointment> allAppointments = Model.getInstance().getDatabaseDriver().getAllUserAppointments(customerId);
         RecentappointmentsTable.setItems(FXCollections.observableArrayList(allAppointments));
    }

    private void loadRecentServices() {
        int customerId = Model.getInstance().getAuthenticatedCustomerId();
        List<RecentServiceItem> recentServices = Model.getInstance().getDatabaseDriver().getRecentServicesForUser(customerId);
        recentServicesTable.setItems(FXCollections.observableArrayList(recentServices));
    }


}
