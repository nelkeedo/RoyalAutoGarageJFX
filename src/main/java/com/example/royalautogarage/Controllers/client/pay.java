package com.example.royalautogarage.Controllers.client;

import com.example.royalautogarage.models.BillingItem;
import com.example.royalautogarage.models.BillingService;
import com.example.royalautogarage.models.Model;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class pay implements Initializable  {
    public Label ammount_lbl;
    public Label Totalamount_lbl;
    public Button Pay_btn;
    public TableView <BillingItem>servicesTable;
    public TableColumn<BillingItem,String> serviceNameColumn;
    public TableColumn <BillingItem,Number>serviceAmountColumn;
    public TableColumn <BillingItem,String>vehicleRegColumn;
    public Button viewcartbtn;
    private final double TAX_RATE = 0.16;
    public TextField billingadress;
    public TextField phonenumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      viewcartbtn.setOnAction(e->refresh());
      Pay_btn.setOnAction(e->handlePayment());

        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        serviceAmountColumn.setCellValueFactory(new PropertyValueFactory<>("serviceAmount"));
        vehicleRegColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));

        int customerId = Model.getInstance().getAuthenticatedCustomerId(); // Or however you obtain the user ID
        List<BillingItem> unpaidServices = Model.getInstance().getDatabaseDriver().getUnpaidServicesByCustomerId(customerId);

        servicesTable.setItems(FXCollections.observableArrayList(unpaidServices));
        // calculate intially total cost
        calculateAndDisplayTotals();


    }
    private void refresh() {
        int customerId = Model.getInstance().getAuthenticatedCustomerId(); // Assuming this method exists
        List<BillingItem> unpaidServices = Model.getInstance().getDatabaseDriver().getUnpaidServicesByCustomerId(customerId);
        servicesTable.setItems(FXCollections.observableArrayList(unpaidServices));
        calculateAndDisplayTotals();

    }
    private void calculateAndDisplayTotals() {
        double total = 0;

        for (BillingItem item : servicesTable.getItems()) {
            total += item.getServiceAmount();
        }

        double totalAfterTax = total * (1 + TAX_RATE);

        // Update the labels to display the calculated totals
        ammount_lbl.setText(String.format("%.2f", total));
        Totalamount_lbl.setText(String.format("%.2f", totalAfterTax));
    }
    private void handlePayment() {
        String phoneNumber = phonenumber.getText().trim();
        String address = billingadress.getText().trim();

        if (phoneNumber.isEmpty() || address.isEmpty()) {
            // Display an error message to the user
            showAlert("Error", "Phone number and address cannot be empty.");
            return;
        }

        int customerId = Model.getInstance().getAuthenticatedCustomerId(); // Ensure this method exists and works
        if (Model.getInstance().getDatabaseDriver().processPayment(customerId, phoneNumber, address)) {
            // Payment is successful
            Model.getInstance().getDatabaseDriver().updateBillingItemsStatusToPaid(customerId);
            refresh(); // Optionally refresh the table to no longer show these items
            calculateAndDisplayTotals(); // Recalculate totals
            showAlert("Success", "Payment successful and services have been marked as Paid.");
        } else {
            // Payment processing failed
            showAlert("Payment Failed", "The provided details do not match our records.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
