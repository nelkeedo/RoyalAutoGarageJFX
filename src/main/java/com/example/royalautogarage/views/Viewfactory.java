package com.example.royalautogarage.views;


import com.example.royalautogarage.Controllers.client.Clientcontroller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Viewfactory {
    //client
    private final StringProperty clientselectedmenuitem;
    private AnchorPane Home;
    private AnchorPane services;
    private AnchorPane pay;
    private AnchorPane Appointment;
    private AnchorPane feedback;
    private AnchorPane vehicle;
    /// admin



    public Viewfactory(){

        this.clientselectedmenuitem = new SimpleStringProperty("");
    }

    public StringProperty getClientselectedmenuitem() {
        return clientselectedmenuitem;
    }

    public AnchorPane getHome(){
        if (Home==null){
            try{
                Home=new FXMLLoader(getClass().getResource("/Fxml/Client/Home.fxml")).load();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return Home;
    }

    public AnchorPane getServices() {
        if (services==null){
            try{
                services=new FXMLLoader(getClass().getResource("/Fxml/Client/clientservicesfxml.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return services;
    }
    public AnchorPane getAppointment() {
        if (Appointment==null){
            try{
                Appointment=new FXMLLoader(getClass().getResource("/Fxml/Client/Appointment.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return Appointment;
    }
    public AnchorPane getPay() {
        if (pay==null){
            try{
                pay=new FXMLLoader(getClass().getResource("/Fxml/Client/pay.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return pay;
    }


    public AnchorPane getFeedback() {
        if (feedback==null){
            try{
                feedback=new FXMLLoader(getClass().getResource("/Fxml/Client/feedback.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return feedback;
    }

    public AnchorPane getVehicle() {
        if (vehicle==null){
            try {
                vehicle=new FXMLLoader(getClass().getResource("/Fxml/Client/vehicle.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return vehicle;
    }

    public  void ShowLoginWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/LoginFxml.fxml"));
     CreateStage(loader);

 }
 public void ShowClientWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        Clientcontroller clientcontroller=new Clientcontroller();
        loader.setController(clientcontroller);
     CreateStage(loader);

 }

 public void Showregisterwindow(){
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Fxml/Client/Register.fxml"));
        CreateStage(loader);
 }
 public void showadmin(){
     FXMLLoader loader =new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminLogin.fxml"));
     CreateStage(loader);

 }

    private void CreateStage(FXMLLoader loader) {
        Scene scene=null;
        try{
            scene=new Scene(loader.load());

        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("royal");
        stage.show();
    }
    public void CloseStage(Stage stage){
        stage.close();

    }


}