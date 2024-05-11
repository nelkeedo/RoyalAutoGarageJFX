package com.example.royalautogarage.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver {
    private Connection conn;
    public DatabaseDriver(){
        try{
            this.conn= DriverManager.getConnection("jdbc:sqlite:royal.db");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /* client section*/
public ResultSet getClientData(String username,String password){
    Statement statement;
    ResultSet resultSet=null;
    try{
        statement=this.conn.createStatement();
        resultSet=statement.executeQuery("SELECT * FROM Customer WHERE FirstName='"+username+"'AND CustomerPassword='"+password+"';");
    }catch (SQLException e){
        e.printStackTrace();
    }
    return resultSet;

}
// addding new users
    public boolean addNewUser(String firstName, String lastName, String address, String password,String PhoneNumber) {
        String sql = "INSERT INTO Customer (FirstName, LastName, Address, CustomerPassword,PhoneNumber) VALUES (?, ?, ?, ?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, password);
            pstmt.setString(5,PhoneNumber);
            pstmt.executeUpdate();
            return true; // Return true if insertion is successful
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Return false if an error occurs
        }
    }

// vehicle controller databse connection to add vehicles
    public void addVehicle(String registrationNumber, String model, String color, String chassis) {
        // Fetch the authenticated customer's ID
        int customerId = Model.getInstance().getAuthenticatedCustomerId();

        String sql = "INSERT INTO Vehicle(CustomerID, VehicleRegistrationNumber, VehicleDetails, Color, Chassis) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, registrationNumber);
            pstmt.setString(3, model);
            pstmt.setString(4, color);
            pstmt.setString(5, chassis);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // to display a person vehicle in the home contoller list
    public List<Vehicle> getVehiclesByCustomerId(int customerId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT VehicleRegistrationNumber, VehicleDetails, Color, Chassis FROM Vehicle WHERE CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String registrationNumber = rs.getString("VehicleRegistrationNumber");
                String model = rs.getString("VehicleDetails");
                String color = rs.getString("Color");
                String chassis = rs.getString("Chassis");
                vehicles.add(new Vehicle(registrationNumber, model, color, chassis));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }
    //appoinmetts
    public void bookAppointment(int customerId, String vehicleRegNum, String date, String time) {
        String sql = "INSERT INTO Appointment (CustomerID, VehicleRegistrationNumber, DateOfAppointment, TimeOfAppointment, Status) VALUES (?, ?, ?, ?, 'Awaiting')";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, vehicleRegNum);
            pstmt.setString(3, date); // Directly use the string
            pstmt.setString(4, time); // Directly use the string
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postponeAppointment(int appointmentId, String newDate, String newTime) {
        String sql = "UPDATE Appointment SET DateOfAppointment = ?, TimeOfAppointment = ?, Status = 'Postponed' WHERE AppointmentID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newDate);
            pstmt.setString(2, newTime);
            pstmt.setInt(3, appointmentId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("No appointment found with ID: " + appointmentId);
            } else {
                System.out.println("Appointment postponed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Appointment> getAwaitingAppointments(int customerId) {
       List<Appointment> appointments = new ArrayList<>();
       String sql = "SELECT AppointmentID, DateOfAppointment, TimeOfAppointment, Status, VehicleRegistrationNumber FROM Appointment WHERE Status = 'Awaiting' AND CustomerID = ?";
// Make sure to fetch and pass the vehicle registration number to the Appointment constructor

       try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setInt(1, customerId);  // Set the CustomerID parameter

           ResultSet rs = pstmt.executeQuery();
           while (rs.next()) {
               appointments.add(new Appointment(rs.getInt("AppointmentID"), rs.getString("DateOfAppointment"),
                       rs.getString("TimeOfAppointment"), rs.getString("Status"),
                       rs.getString("VehicleRegistrationNumber")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return appointments;
   }
    public List<Appointment> getPostponedAppointments(int customerId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT AppointmentID, DateOfAppointment, TimeOfAppointment, Status, VehicleRegistrationNumber FROM Appointment WHERE Status = 'Postponed' AND CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("AppointmentID"),
                        rs.getString("DateOfAppointment"),
                        rs.getString("TimeOfAppointment"),
                        rs.getString("Status"),
                        rs.getString("VehicleRegistrationNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    public List<Appointment> getAllUserAppointments(int customerId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT AppointmentID, DateOfAppointment, TimeOfAppointment, Status, VehicleRegistrationNumber FROM Appointment WHERE CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("AppointmentID"),
                        rs.getString("DateOfAppointment"),
                        rs.getString("TimeOfAppointment"),
                        rs.getString("Status"),
                        rs.getString("VehicleRegistrationNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    public void submitFeedback(Feedback feedback, int customerId) {
        String sql = "INSERT INTO FeedbackTable (ServiceName, AppoinmentStatus, QualityOfService, Complain, WholeServiceExperience, ExperienceWithMechanics, VehicleStatus, CustomerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, feedback.getServiceName());
            pstmt.setString(2, feedback.getAppointmentStatus());
            pstmt.setString(3, feedback.getQualityOfService());
            pstmt.setString(4, feedback.getComplain());
            pstmt.setString(5, feedback.getWholeServiceExperience());
            pstmt.setString(6, feedback.getExperienceWithMechanics());
            pstmt.setString(7, feedback.getVehicleStatus());
            pstmt.setInt(8, customerId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // car validaition in services cart itmes
    public boolean isVehicleRegisteredToUser(String vehicleRegNo, int userId) {
        String query = "SELECT COUNT(*) FROM Vehicle WHERE VehicleRegistrationNumber = ? AND CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, vehicleRegNo);
            pstmt.setInt(2, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    //taking cart items in the billing table
    public void addCartItemsToBilling(int customerId, List<ServiceItem> cartItems, String vehicleReg) {
        String sql = "INSERT INTO Billing (CustomerID, ServiceName, ServiceAmount, Status,VehicleRegistrationNumber) VALUES (?, ?, ?, 'Not Paid',?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (ServiceItem item : cartItems) {
                pstmt.setInt(1, customerId);
                pstmt.setString(2, item.getServiceName());
                pstmt.setInt(3, item.getServiceAmount());
                pstmt.setString(4,vehicleReg);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //to display  all services in the pay view
    public List<BillingItem> getUnpaidServicesByCustomerId(int customerId) {
        List<BillingItem> unpaidServices = new ArrayList<>();
        String sql = "SELECT ServiceName, ServiceAmount, VehicleRegistrationNumber FROM Billing WHERE CustomerID = ? AND Status = 'Not Paid'";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String serviceName = rs.getString("ServiceName");
                int serviceAmount = rs.getInt("ServiceAmount");
                String vehicleRegNo = rs.getString("VehicleRegistrationNumber");

                unpaidServices.add(new BillingItem(serviceName, serviceAmount, vehicleRegNo));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return unpaidServices;
    }
    public boolean processPayment(int customerId, String phoneNumber, String address) {
        String sql = "SELECT COUNT(*) FROM Customer WHERE CustomerID = ? AND PhoneNumber = ? AND Address = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, address);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // The phone number and address match the customer's record
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false; // Return false if no match is found or an error occurs
    }
// update the stastus of not paid to paid
    public void updateBillingItemsStatusToPaid(int customerId) {
        String sql = "UPDATE Billing SET Status = 'Paid' WHERE CustomerID = ? AND Status = 'Not Paid'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//display the recent sercice at dashboard
public List<RecentServiceItem> getRecentServicesForUser(int customerId) {
    List<RecentServiceItem> services = new ArrayList<>();
    String sql = "SELECT ServiceName, VehicleRegistrationNumber FROM Billing WHERE CustomerID = ? AND Status = 'Paid'"; // Adjust based on your schema

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, customerId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String serviceName = rs.getString("ServiceName");
            String vehicleRegNo = rs.getString("VehicleRegistrationNumber");
            services.add(new RecentServiceItem(serviceName, vehicleRegNo));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return services;
}

}

