package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.Model;
import com.example.royalautogarage.models.ServiceItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class servicescontoller implements Initializable {
    public Button wheelalignment_btn;
    public Button oilchange_btn;
    public Button tirerotaion_btn;
    public Button brakeinsepction_btn;
    public Button batteryreplacment_btn;
    public Button airfilte_btn;
    public Button winshield_btn;
    public Text transmision_btn;
    public Button engine_btn;
    public Button suspesion_btn;
    public Button air_btn;
    public Label electrical_btn;
    public Label clutch_btn;
    public Label timming_btn;
    public Button fuel_btn;
    public Button addtoblling_btn;
    public TableView <ServiceItem>Carttable;
    public TableColumn <ServiceItem,String> servicenamecolumn;
    public TableColumn <ServiceItem,Integer>serviceamountcolumn;
    public TextField Cartobeserviced;
    public Button Electricalbtn;
    public Button Clutchreplacemnetbtn;
    public Button timmingbeltbtn;
    public Button Transimmisonbutton;
    public Label Datelbl;
    private Map<String, ServiceItem> addedServices = new HashMap<>();
    private ObservableList<ServiceItem> cartItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //date update
        LocalDate currentDate = LocalDate.now();
        Datelbl.setText(String.valueOf(currentDate));

        servicenamecolumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        serviceamountcolumn.setCellValueFactory(new PropertyValueFactory<>("serviceAmount"));
        Carttable.setItems(cartItems);
        // Setup actions for each service button
        setupServiceButtonAction(wheelalignment_btn, "Wheel Alignment", 5000);
        setupServiceButtonAction(oilchange_btn, "Oil Change", 3000);
        setupServiceButtonAction(tirerotaion_btn, "Tire Rotation", 4500);
        setupServiceButtonAction(brakeinsepction_btn, "Brake Inspection", 15000);
        setupServiceButtonAction(batteryreplacment_btn, "Battery Replacement", 8000);
        setupServiceButtonAction(airfilte_btn, "Air Filter Replacement", 3000);
        setupServiceButtonAction(winshield_btn, "Windshield Replacement", 30000);
        setupServiceButtonAction(Transimmisonbutton, "Transmission Repair", 150000);
        setupServiceButtonAction(engine_btn, "Engine Rebuild or Replacement", 300000);
        setupServiceButtonAction(suspesion_btn, "Suspension Overhaul", 75000);
        setupServiceButtonAction(air_btn, "Air Conditioning Repair", 20000);
        setupServiceButtonAction(Electricalbtn, "Electrical System Repair", 30000);
        setupServiceButtonAction(Clutchreplacemnetbtn, "Clutch Replacement", 75000);
        setupServiceButtonAction(timmingbeltbtn, "Timing Belt Replacement", 45000);
        setupServiceButtonAction(fuel_btn, "Fuel System Repair", 30000);

        ///
        addtoblling_btn.setOnAction(e -> {
            String vehicleReg = Cartobeserviced.getText().trim();
            int customerId = Model.getInstance().getAuthenticatedCustomerId(); // Assuming a method to get the current user ID

            if (!vehicleReg.isEmpty() && Model.getInstance().getDatabaseDriver().isVehicleRegisteredToUser(vehicleReg, customerId)) {
                System.out.println("Vehicle is registered to the user. Proceeding...");
                Model.getInstance().getDatabaseDriver().addCartItemsToBilling(customerId, cartItems ,vehicleReg);
                 cartItems.clear();
                 addedServices.clear();
                 Cartobeserviced.clear();
            } else {
                // Vehicle is not registered to the user or field is empty
                System.out.println("Vehicle is not registered to the user or the field is empty.");
                // Optionally, show an alert to the user

            }
        });



    }

    private void setupServiceButtonAction(Button serviceButton, String serviceName, int serviceAmount) {
        serviceButton.setOnAction(e -> toggleServiceInCart(serviceName, serviceAmount));
    }
    private void toggleServiceInCart(String serviceName, Integer serviceAmount) {
        ServiceItem existingItem = addedServices.get(serviceName);
        if (existingItem != null) {
            // Service is already in the cart, so remove it
            cartItems.remove(existingItem);
            addedServices.remove(serviceName);
        } else {
            // Service is not in the cart, so add it
            ServiceItem newItem = new ServiceItem(serviceName, serviceAmount);
            cartItems.add(newItem);
            addedServices.put(serviceName, newItem);
        }
    }

}
