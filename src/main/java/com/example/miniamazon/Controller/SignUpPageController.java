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

public class SignUpPageController implements Initializable {
    @FXML
    private TextField first_name_fld;
    @FXML
    private TextField last_name_fld;
    @FXML
    private TextField email_field;
    @FXML
    private  PasswordField password_field;
    @FXML
    private TextField phone_number_fld;
    @FXML
    private Button login_btn;
    @FXML
    private Button sign_up_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/MainWindow.fxml","Welcome!",null,null);
            }
        });

        sign_up_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!first_name_fld.getText().trim().isEmpty() && !email_field.getText().trim().isEmpty() && !password_field.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event,first_name_fld.getText(),last_name_fld.getText(),email_field.getText(),password_field.getText(),phone_number_fld.getText());
                } else {
                    System.out.println("Please Fill all details!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Fill All Details");
                    alert.show();
                }
            }
        });
    }
}
