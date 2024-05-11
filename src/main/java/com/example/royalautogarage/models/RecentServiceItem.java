package com.example.royalautogarage.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecentServiceItem {
    private final SimpleStringProperty serviceName;
    private final SimpleStringProperty vehicleRegistrationNumber;

    public RecentServiceItem(String serviceName, String vehicleRegistrationNumber) {
        this.serviceName = new SimpleStringProperty(serviceName);
        this.vehicleRegistrationNumber = new SimpleStringProperty(vehicleRegistrationNumber);
    }

    public String getServiceName() { return serviceName.get(); }
    public String getVehicleRegistrationNumber() { return vehicleRegistrationNumber.get(); }

    public StringProperty serviceNameProperty() { return serviceName; }
    public StringProperty vehicleRegistrationNumberProperty() { return vehicleRegistrationNumber; }
}
