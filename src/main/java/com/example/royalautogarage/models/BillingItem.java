package com.example.royalautogarage.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BillingItem {
    private final SimpleStringProperty serviceName;
    private final SimpleIntegerProperty serviceAmount;
    private final SimpleStringProperty vehicleRegistrationNumber;

    public BillingItem(String serviceName, Integer serviceAmount, String vehicleRegistrationNumber) {
        this.serviceName = new SimpleStringProperty(serviceName);
        this.serviceAmount = new SimpleIntegerProperty(serviceAmount);
        this.vehicleRegistrationNumber = new SimpleStringProperty(vehicleRegistrationNumber);
    }

    // Getters and property getters
    public String getServiceName() { return serviceName.get(); }
    public Integer getServiceAmount() { return serviceAmount.get(); }
    public String getVehicleRegistrationNumber() { return vehicleRegistrationNumber.get(); }

    public StringProperty serviceNameProperty() { return serviceName; }
    public IntegerProperty serviceAmountProperty() { return serviceAmount; }
    public StringProperty vehicleRegistrationNumberProperty() { return vehicleRegistrationNumber; }
}
