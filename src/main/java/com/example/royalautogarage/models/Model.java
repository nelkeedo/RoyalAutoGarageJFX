package com.example.royalautogarage.models;

import com.example.royalautogarage.views.Viewfactory;

import java.sql.ResultSet;

public class Model {
    private int authenticatedCustomerId;
    private client client;
    private static Model model;
    private final Viewfactory viewfactory;
    private final DatabaseDriver databaseDriver;
    // client data section
    private boolean ClientloginSuccesflag;
    public void setAuthenticatedCustomerId(int customerId) {
        this.authenticatedCustomerId = customerId;
    }
    public int getAuthenticatedCustomerId() {
        return this.authenticatedCustomerId;
    }
    private Model(){
        this.viewfactory=new Viewfactory();
        this.databaseDriver=new DatabaseDriver();
        this.ClientloginSuccesflag=false;
        this.client=new client("","","");


    }
    public static  synchronized  Model getInstance(){
        if (model==null){
            model=new Model();
        }
        return model;
    }
    public Viewfactory getViewfactory() {
        return viewfactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }
    public boolean getClientloginSuccesflag(){return this.ClientloginSuccesflag;}
    public void setClientloginSuccesflag(boolean flag ){ this.ClientloginSuccesflag=flag;}

    public client getClient() {
        return client;
    }

    public void evaluatecreditials(String username, String password){
        ResultSet resultSet=databaseDriver.getClientData(username,password);
        try{
            if(resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastnameProperty().set(resultSet.getString("LastName"));
                this.client.adressProperty().set(resultSet.getString("Address"));
                this.ClientloginSuccesflag=true;
                setAuthenticatedCustomerId(resultSet.getInt("CustomerID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
