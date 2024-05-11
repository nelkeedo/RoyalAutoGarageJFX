package com.example.royalautogarage.models;

public class Feedback {
    // Feedback properties based on your table
    private String serviceName;
    private String appointmentStatus;
    private String qualityOfService;
    private String complain;
    private String wholeServiceExperience;
    private String experienceWithMechanics;
    private String vehicleStatus;

    // Constructor, getters, and setters
    public Feedback(String serviceName, String appointmentStatus, String qualityOfService, String complain, String wholeServiceExperience, String experienceWithMechanics, String vehicleStatus) {
        this.serviceName = serviceName;
        this.appointmentStatus = appointmentStatus;
        this.qualityOfService = qualityOfService;
        this.complain = complain;
        this.wholeServiceExperience = wholeServiceExperience;
        this.experienceWithMechanics = experienceWithMechanics;
        this.vehicleStatus = vehicleStatus;
    }
    public String getServiceName() {
        return serviceName;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getQualityOfService() {
        return qualityOfService;
    }

    public String getComplain() {
        return complain;
    }

    public String getWholeServiceExperience() {
        return wholeServiceExperience;
    }

    public String getExperienceWithMechanics() {
        return experienceWithMechanics;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    // Setters
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setQualityOfService(String qualityOfService) {
        this.qualityOfService = qualityOfService;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public void setWholeServiceExperience(String wholeServiceExperience) {
        this.wholeServiceExperience = wholeServiceExperience;
    }

    public void setExperienceWithMechanics(String experienceWithMechanics) {
        this.experienceWithMechanics = experienceWithMechanics;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

}
