package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterAdminLoginSuccess implements Initializable {

    @FXML
    private Button admin_add_btn;

    @FXML
    private Button admin_home_btn;

    @FXML
    private Label total_product_lbl;
    @FXML
    private Button logout_btn;

    @FXML
    private Button admin_delete_btn;


    @FXML
    private Button admin_update_btn;

    @FXML
    private Label total_user_lbl;
    String totalProducts = "NA";
    String totalUsers = "NA";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        totalProducts = DBUtils.getAdminHomeDetailsProduct();
        total_product_lbl.setText(totalProducts);

        totalUsers = DBUtils.getAdminHomeUsers();
        total_user_lbl.setText(totalUsers);

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
                DBUtils.changeScene(event,"/Fxml/AfterAdminLoginSuccess.fxml","Admin Dashboard",null,null);
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
        admin_update_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/AdminUpdateTab.fxml","Update Product",null,null);
            }
        });
    }
}
