package com.example.miniamazon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

public class DBUtils {

    public static String customer_name;
    public static String customer_email;

// ----------------------------------------------------------------   CHANGING SCENES -------------------------------------------------------------------------------------------------------------
    public static void changeScene(ActionEvent event, String fxml, String title, String email, String password){
        Parent root = null;
        if (email != null && password != null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxml));
                root = loader.load();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxml));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    // ------------------------------------------------------ SIGN UP USER USING BELOW CODE -------------------------------------------------------------------------------------------------------
    public static void signUpUser(ActionEvent event, String first_name, String last_name, String email, String password, String phone_number){
        Connection connection = null;
        PreparedStatement psInsert = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            psInsert = connection.prepareStatement("INSERT INTO users (first_name, last_name,phone_number, email, password) VALUES (?,?,?,?,?)");
            psInsert.setString(1,first_name);
            psInsert.setString(2,last_name);
            psInsert.setString(3,phone_number);
            psInsert.setString(4,email);
            psInsert.setString(5,password);
            psInsert.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("SUCCESS!");
            alert.setContentText("Your Account Has Been Created!");
            alert.show();
            changeScene(event,"/Fxml/MainWindow.fxml","Welcome",null,null);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (psInsert != null){
                try {
                    psInsert.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    // --------------------------------- LOG IN USER ----------------------------------------------------------------------------------------------------------------------------------------------
    public static void logInUser(ActionEvent event, String email, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User Not Found!");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedName = resultSet.getString("first_name");

                    if (retrievedPassword.equals(password)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Logged In Successful. Redirecting.....");
                        alert.show();
                        customer_name = retrievedName;
                        customer_email = email;
                        changeScene(event,"/Fxml/AfterLoginSuccess.fxml","Dashboard",email,null);
                    } else {
                        System.out.println("password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Provided Credentials Are Incorrect!");
                        alert.show();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    // ---------------------------------------------------------------------------GETTING USER"S NAME AND EMAIL -----------------------------------------------------------------------------------
    public static String getUserName(){
        return customer_name;
    }

    public static String getUserEmail(){
        return customer_email;
    }

}
