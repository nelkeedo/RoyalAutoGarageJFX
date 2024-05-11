package com.example.royalautogarage.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceItem {
    private final SimpleStringProperty serviceName;
    private final IntegerProperty serviceAmount;

    public ServiceItem(String serviceName, Integer serviceAmount) {
        this.serviceName = new SimpleStringProperty(serviceName);
        this.serviceAmount = new SimpleIntegerProperty(serviceAmount);
    }

    public String getServiceName() { return serviceName.get(); }
    public Integer getServiceAmount() { return serviceAmount.get(); }

    public StringProperty serviceNameProperty() { return serviceName; }
    public IntegerProperty serviceAmountProperty() { return serviceAmount; }
}
