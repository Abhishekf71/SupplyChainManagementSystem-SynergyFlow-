package com.example.miniamazon.Controller;

import com.example.miniamazon.AdminDetails;
import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDeleteTabController implements Initializable {

    @FXML
    private Button admin_add_btn;

    @FXML
    private Button admin_home_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button admin_delete_btn;

    @FXML
    private TextField product_id_fld;

    String productName = null;

    @FXML
    private Label check_product_label;

    @FXML
    private Button fetch_product_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Logged Out!");
                alert.show();
                DBUtils.changeScene(event,"/Fxml/BeforeMainWindow.fxml","Welcome!",null,null);
            }
        });

        admin_home_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/AfterAdminLoginSuccess.fxml","Dashboard",null,null);
            }
        });

        admin_add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/AfterAdminLoginSuccessAdd.fxml","Add Products",null,null);
            }
        });

        admin_delete_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/AdminDeleteTab.fxml","Delete Product",null,null);
            }
        });

        fetch_product_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productName = AdminDetails.getProductNameByID(product_id_fld.getText());
                check_product_label.setText(productName);
            }
        });

    }
}
