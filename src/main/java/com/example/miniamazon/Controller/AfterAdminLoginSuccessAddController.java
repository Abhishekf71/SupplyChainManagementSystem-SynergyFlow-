package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterAdminLoginSuccessAddController implements Initializable {

    @FXML
    private Button admin_add_btn;

    @FXML
    private Button admin_home_btn;

    @FXML
    private TextArea product_description_area;

    @FXML
    private TextField product_name_fld;

    @FXML
    private ChoiceBox<String> product_category_choice_box;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField product_price_fld;

    @FXML
    private Button proceed_btn;

    @FXML
    private Button admin_delete_btn;

    @FXML
    private Button admin_update_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        product_description_area.setWrapText(true);

        product_category_choice_box.getItems().addAll("Computer & Accessories","Electronics","Clothing");

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

        proceed_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String category = product_category_choice_box.getSelectionModel().getSelectedItem();
                DBUtils.addProducts(event,product_name_fld.getText(),product_description_area.getText(),category,product_price_fld.getText());
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
