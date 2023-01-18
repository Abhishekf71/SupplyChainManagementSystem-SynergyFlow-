package com.example.miniamazon;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDetails {

//    ---------------------------------------------------------------------- FETCHING PRODUCT NAME BY ITS ID ---------------------------------------------------------------------------------------
    public static String getProductNameByID(String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String productName = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            preparedStatement = connection.prepareStatement("SELECT product_name FROM product WHERE product_id = ?");
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Product ID not available!");
            } else {
                while (resultSet.next()){
                    productName = resultSet.getString("product_name");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return productName;
    }
//    ------------------------------------------------ DELETING PRODUCT FROM DB ----------------------------------------------------------------------------------------------------------------
    public static void deleteProductByID(String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            preparedStatement = connection.prepareStatement("DELETE FROM product WHERE product_id = ?");
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Product ID : " + id + " Deleted");
            alert.show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    // -------------------------------------------------------------------- FETCHING DETAILS FOR UPDATE WINDOW -----------------------------------------------------------------------------------
    public static String getProductPriceByID(String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String productPrice = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            preparedStatement = connection.prepareStatement("SELECT product_price FROM product WHERE product_id = ?");
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("Product ID not available!");
            } else {
                while (resultSet.next()){
                    productPrice = resultSet.getString("product_price");

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return productPrice;
    }

    //  ----------------------------------------------------------------------------- UPDATING PRODUCT PRICE --------------------------------------------------------------------------------------
    public static void updateProductPrice(String product_id, String product_price){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            preparedStatement = connection.prepareStatement("UPDATE product SET product_price = ? WHERE product_id = ?");
            preparedStatement.setString(1,product_price);
            preparedStatement.setString(2,product_id);
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Product ID : " + product_id + " Updated Price :" + product_price);
            alert.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
