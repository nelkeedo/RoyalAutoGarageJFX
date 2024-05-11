package com.example.royalautogarage.models;

import javafx.beans.property.SimpleStringProperty;

/*public class Vehicle {
    private String registrationNumber;
    private String model;
    private String color;
    private String chassis;

    public Vehicle(String registrationNumber, String model, String color, String chassis) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.color = color;
        this.chassis = chassis;
    }
    @Override
    public String toString() {
        // Customize this to display vehicle details in a way that makes sense for your application
        return registrationNumber + " - " + model + " - " + color + " - " + chassis;
    }
}*/
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle {
    private final SimpleStringProperty registrationNumber;
    private final SimpleStringProperty model;
    private final SimpleStringProperty color;
    private final SimpleStringProperty chassis;

    public Vehicle(String registrationNumber, String model, String color, String chassis) {
        this.registrationNumber = new SimpleStringProperty(registrationNumber);
        this.model = new SimpleStringProperty(model);
        this.color = new SimpleStringProperty(color);
        this.chassis = new SimpleStringProperty(chassis);
    }

    // Registration Number
    public String getRegistrationNumber() {
        return registrationNumber.get();
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber.set(registrationNumber);
    }

    public StringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    // Model
    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public StringProperty modelProperty() {
        return model;
    }

    // Color
    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public StringProperty colorProperty() {
        return color;
    }

    // Chassis
    public String getChassis() {
        return chassis.get();
    }

    public void setChassis(String chassis) {
        this.chassis.set(chassis);
    }

    public StringProperty chassisProperty() {
        return chassis;
    }

    // Optionally override toString for debugging or logging, not required for TableView
    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber=" + registrationNumber.get() +
                ", model=" + model.get() +
                ", color=" + color.get() +
                ", chassis=" + chassis.get() +
                '}';
    }
}

