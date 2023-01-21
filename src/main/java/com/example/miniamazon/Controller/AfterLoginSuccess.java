package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import com.example.miniamazon.Products;
import com.example.miniamazon.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AfterLoginSuccess implements Initializable {

    @FXML
    private TableView<Products> table_product;

    @FXML
    private TableColumn<Products, String> col_category;

    @FXML
    private TableColumn<Products, String> col_description;

    @FXML
    private TableColumn<Products, Integer> col_id;

    @FXML
    private TableColumn<Products, String> col_name;

    @FXML
    private TableColumn<Products, Integer> col_price;

    ObservableList<Products> list;
    int index = -1;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private Label customer_name_lbl;
    @FXML
    private Label customer_email_lbl;
    @FXML
    private Button logout_btn;

    @FXML
    private Button user_home_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_id.setCellValueFactory(new PropertyValueFactory<Products,Integer>("Id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
        col_description.setCellValueFactory(new PropertyValueFactory<Products,String>("Description"));
        col_category.setCellValueFactory(new PropertyValueFactory<Products,String>("Category"));
        col_price.setCellValueFactory(new PropertyValueFactory<Products,Integer>("Price"));

        list = Users.getDataProducts();
        table_product.setItems(list);
        logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/BeforeMainWindow.fxml","Welcome!",null,null);
            }
        });

        user_home_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"/Fxml/AfterLoginSuccess.fxml","Welcome " + DBUtils.getUserName(),null,null);
            }
        });

        customer_name_lbl.setText(DBUtils.getUserName());
        customer_email_lbl.setText(DBUtils.getUserEmail());

    }
}
