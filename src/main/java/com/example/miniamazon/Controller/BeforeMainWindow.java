package com.example.miniamazon.Controller;

import com.example.miniamazon.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BeforeMainWindow implements Initializable {
    @FXML
    private ChoiceBox<String> acc_choice_box;

    @FXML
    private Button proceed_button;

    static String account_type = "USER";

//    @FXML
//    void getAccountType(MouseEvent event) {
//        account_type = acc_choice_box.getSelectionModel().getSelectedItem();
//        System.out.println(account_type);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_choice_box.getItems().addAll("USER","ADMIN");

        proceed_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                account_type = acc_choice_box.getSelectionModel().getSelectedItem();

                if (account_type.equals("USER")){
                    DBUtils.changeScene(event,"/Fxml/MainWindow.fxml","Log In",null,null);
                } else {
                    DBUtils.changeScene(event,"/Fxml/AdminWindow.fxml","Log In",null,null);
                }
            }
        });

    }
}
