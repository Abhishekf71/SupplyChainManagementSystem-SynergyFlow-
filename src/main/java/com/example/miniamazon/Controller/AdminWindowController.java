package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminWindowController implements Initializable {

    @FXML
    private TextField email_fld;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_fld;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (email_fld.getText().trim().isEmpty() && password_fld.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provide Valid Email & Password");
                    alert.show();
                } else {
                    DBUtils.logInAdmin(event,email_fld.getText(),password_fld.getText());
                }
            }
        });
    }
}
