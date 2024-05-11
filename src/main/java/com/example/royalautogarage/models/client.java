package com.example.royalautogarage.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class client {
    private final StringProperty firstName;
    private final StringProperty Lastname;
    private final StringProperty adress;


    public client(String Fname, String Lname, String Adress ){
        this.firstName=new SimpleStringProperty(this,"firstname",Fname);
        this.Lastname=new SimpleStringProperty(this,"Lastname",Lname);
        this.adress=new SimpleStringProperty(this,"adress",Adress);

    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastnameProperty() {
        return Lastname;
    }

    public StringProperty adressProperty() {
        return adress;
    }


}
