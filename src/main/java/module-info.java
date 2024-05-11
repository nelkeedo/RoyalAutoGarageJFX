module com.example.royalautogarage {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires java.sql;

    opens com.example.royalautogarage to javafx.fxml;
    exports com.example.royalautogarage;
    exports com.example.royalautogarage.Controllers;
    exports com.example.royalautogarage.Controllers.client;
    exports com.example.royalautogarage.Controllers.Admin;
    exports com.example.royalautogarage.views;
    exports com.example.royalautogarage.models;
}