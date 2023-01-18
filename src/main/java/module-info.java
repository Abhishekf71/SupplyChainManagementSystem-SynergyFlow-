module com.example.miniamazon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.miniamazon to javafx.fxml;
    opens com.example.miniamazon.Controller;
    exports com.example.miniamazon;
    opens com.example.miniamazon.Controller.AdminController;
}