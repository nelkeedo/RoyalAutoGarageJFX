package com.example.royalautogarage.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
    private final IntegerProperty id;
    private final StringProperty dateOfAppointment;
    private final StringProperty timeOfAppointment;
    private final StringProperty status;
    private final  StringProperty vehicleRegistrationNumber;

    public Appointment(Integer id, String dateOfAppointment, String timeOfAppointment, String status,String vehicleRegNum) {
        this.id = new SimpleIntegerProperty(id);
        this.dateOfAppointment = new SimpleStringProperty(dateOfAppointment);
        this.timeOfAppointment = new SimpleStringProperty(timeOfAppointment);
        this.status = new SimpleStringProperty(status);
        this.vehicleRegistrationNumber = new SimpleStringProperty(vehicleRegNum);
    }

    // Getters
    public String getVehicleRegistrationNumber() { return vehicleRegistrationNumber.get(); }

    public int getId() { return id.get(); }
    public String getDateOfAppointment() { return dateOfAppointment.get(); }
    public String getTimeOfAppointment() { return timeOfAppointment.get(); }
    public String getStatus() { return status.get(); }

    // Property getters
    public IntegerProperty idProperty() { return id; }
    public StringProperty dateOfAppointmentProperty() { return dateOfAppointment; }
    public StringProperty timeOfAppointmentProperty() { return timeOfAppointment; }
    public StringProperty statusProperty() { return status; }
    public StringProperty vehicleRegistrationNumberProperty() { return vehicleRegistrationNumber; }
}

