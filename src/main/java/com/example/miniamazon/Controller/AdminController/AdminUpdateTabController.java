package com.example.miniamazon.Controller.AdminController;

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

public class AdminUpdateTabController implements Initializable {
    @FXML
    private Button admin_add_btn;

    @FXML
    private Button admin_delete_btn;

    @FXML
    private Button admin_home_btn;

    @FXML
    private Button admin_update_btn;

    @FXML
    private Button fetch_product_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField product_id_fld;

    @FXML
    private Label product_id_lbl;

    @FXML
    private Label product_name_lbl;

    @FXML
    private Label product_price_lbl;

    String productName = null;
    String productPrice = null;
    @FXML
    private Button update_price_btn;
    @FXML
    private TextField updated_price_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        update_price_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminDetails.updateProductPrice(product_id_fld.getText(),updated_price_fld.getText());
            }
        });

        fetch_product_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productName = AdminDetails.getProductNameByID(product_id_fld.getText());
                productPrice = AdminDetails.getProductPriceByID(product_id_fld.getText());
                product_name_lbl.setText(productName);
                product_id_lbl.setText(product_id_fld.getText());
                product_price_lbl.setText(productPrice);

            }
        });
        productName = AdminDetails.getProductNameByID(product_id_fld.getText());
        product_name_lbl.setText(productName);
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
                DBUtils.changeScene(event, "/Fxml/Admin/AfterAdminLoginSuccess.fxml","Dashboard",null,null);
            }
        });

        admin_add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Admin/AfterAdminLoginSuccessAdd.fxml","Add Products",null,null);
            }
        });

        admin_delete_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Admin/AdminDeleteTab.fxml","Delete Product",null,null);
            }
        });

        admin_update_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Admin/AdminUpdateTab.fxml","Update Product",null,null);
            }
        });
    }
}
