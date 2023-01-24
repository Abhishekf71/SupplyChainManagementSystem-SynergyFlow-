package com.example.miniamazon.Controller.UserController;

import com.example.miniamazon.DBUtils;
import com.example.miniamazon.UserOrder;
import com.example.miniamazon.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MyOrdersWindowController implements Initializable {
    @FXML
    private Label customer_email_lbl;

    @FXML
    private Label customer_name_lbl;

    @FXML
    private Button logout_btn;

    @FXML
    private Button my_order_btn;

    @FXML
    private Button user_home_btn;

    @FXML
    private TableView<UserOrder> orders_table;

    @FXML
    private TableColumn<UserOrder,String> col_category;

    @FXML
    private TableColumn<UserOrder,String> col_name;

    @FXML
    private TableColumn<UserOrder, Integer> col_order_id;

    @FXML
    private TableColumn<UserOrder,Integer> col_price;

    @FXML
    private TableColumn<UserOrder, String> col_description;

    ObservableList<UserOrder> list;

    int index = -1;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_order_id.setCellValueFactory(new PropertyValueFactory<UserOrder,Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<UserOrder,String>("Name"));
        col_description.setCellValueFactory(new PropertyValueFactory<UserOrder,String>("Description"));
        col_category.setCellValueFactory(new PropertyValueFactory<UserOrder,String>("Category"));
        col_price.setCellValueFactory(new PropertyValueFactory<UserOrder,Integer>("Price"));

        list = Users.getOrderDetails(DBUtils.getUserEmail());
        orders_table.setFixedCellSize(70.0);
        orders_table.setItems(list);
        // ----------------------------------------- -------- ----------- BUTTONS ON ACTION ------------ --------------- ---------------------------------
        user_home_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Users/AfterLoginSuccess.fxml","Welcome ",null,null);
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

        my_order_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Fxml/Users/MyOrdersWindow.fxml","Orders",null,null);
            }
        });

        customer_name_lbl.setText(DBUtils.getUserName());
        customer_email_lbl.setText(DBUtils.getUserEmail());
//        customer_email_lbl.setText(afterLoginSuccess.customer_email_lbl.getText());




    }
}
