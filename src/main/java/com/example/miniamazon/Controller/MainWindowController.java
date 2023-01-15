package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private TextField email_fld;
    @FXML
    private PasswordField password_fld;
    @FXML
    private Button login_btn;
    @FXML
    private Button sign_up_btn;

    static String account_type;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sign_up_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/SignUpPage.fxml","Sign Up!",null,null);
            }
        });

        login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (email_fld.getText().isEmpty() || password_fld.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please provide Email And Password To Continue");
                    alert.show();
                } else {
                    DBUtils.logInUser(event,email_fld.getText(),password_fld.getText());

                }
            }
        });


    }
}
