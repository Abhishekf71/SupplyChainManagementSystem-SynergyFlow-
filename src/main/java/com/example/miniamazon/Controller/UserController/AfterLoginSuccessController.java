package com.example.miniamazon.Controller.UserController;

import com.example.miniamazon.DBUtils;
import com.example.miniamazon.Orders;
import com.example.miniamazon.Products;
import com.example.miniamazon.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AfterLoginSuccessController implements Initializable {

    @FXML
    public TableView<Products> table_product;

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
    ObservableList<Products> searchable;
    int index = -1;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public Label customer_name_lbl;
    @FXML
    public Label customer_email_lbl;
    @FXML
    private Button logout_btn;

    @FXML
    private Button user_home_btn;

    @FXML
    private TextField search_box_fld;

    @FXML
    private Button search_btn;

    @FXML
    private Button my_order_btn;

    @FXML
    private Button buy_now_btn;
    Products selected;
    @FXML
    void getSelectedProduct(MouseEvent event) {
         selected = table_product.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        my_order_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Users/MyOrdersWindow.fxml","Orders",null,null);
            }
        });

        col_id.setCellValueFactory(new PropertyValueFactory<Products,Integer>("Id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
        col_description.setCellValueFactory(new PropertyValueFactory<Products,String>("Description"));
        col_category.setCellValueFactory(new PropertyValueFactory<Products,String>("Category"));
        col_price.setCellValueFactory(new PropertyValueFactory<Products,Integer>("Price"));

        table_product.setFixedCellSize(70.0);


        list = Users.getDataProducts();


        table_product.setItems(list);

        search_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchable = Users.getDataProductsBySearch(search_box_fld.getText().toLowerCase());
                table_product.setItems(searchable);
            }
        });

        buy_now_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Products selectedProducts = selected;
                if (selectedProducts == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Choose a product");
                    alert.show();
                } else {
                    Orders.placeOrder(customer_email_lbl.getText(),selectedProducts);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Order Placed");
                    alert.show();
                }

            }
        });

        logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Logged Out Successfully!");
                alert.show();
                DBUtils.changeScene(event,"/Fxml/BeforeMainWindow.fxml","Welcome!",null,null);
            }
        });

        user_home_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Users/AfterLoginSuccess.fxml","Welcome " + DBUtils.getUserName(),null,null);
            }
        });

        customer_name_lbl.setText(DBUtils.getUserName());
        customer_email_lbl.setText(DBUtils.getUserEmail());

    }
}
