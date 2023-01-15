package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginSuccess implements Initializable {
    @FXML
    private Label customer_name_lbl;
    @FXML
    private Label customer_email_lbl;
    @FXML
    private Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/BeforeMainWindow.fxml","Welcome!",null,null);
            }
        });

        customer_name_lbl.setText(DBUtils.getUserName());
        customer_email_lbl.setText(DBUtils.getUserEmail());

    }
}
