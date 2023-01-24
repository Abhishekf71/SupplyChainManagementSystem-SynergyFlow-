package com.example.miniamazon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users {

    // ------------------------------------------------------ Displays all available products to existing as well as new users --------------------------------------------------------------------
    public static ObservableList<Products> getDataProducts(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<Products> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                list.add(new Products(Integer.parseInt(resultSet.getString("product_id")),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_price")));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    // ------------------------------------------------------ Displays products searched by USER --------------------------------------------------------------------
    public static ObservableList<Products> getDataProductsBySearch(String word){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
        }catch (Exception e){
            e.printStackTrace();
        }
        ObservableList<Products> list = FXCollections.observableArrayList();
        try {
            String statement = String.format("SELECT * FROM product WHERE product_name LIKE '%%%s%%' ",word);
            PreparedStatement preparedStatement = connection.prepareStatement(statement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                list.add(new Products(Integer.parseInt(resultSet.getString("product_id")),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_price")));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    // ------------------------------------------------------ Displays the products which USER has placed orders  --------------------------------------------------------------------
    public static ObservableList<UserOrder> getOrderDetails(String email){
        Connection connection = null;
        ObservableList<UserOrder> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.order_id, p.product_name , p.product_category, p.product_price FROM orders as o \n" +
                    "INNER JOIN product as p ON o.product_id = p.product_id WHERE user_id = (SELECT user_id FROM users WHERE email = ?);");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new UserOrder(Integer.parseInt(
                        resultSet.getString("order_id")),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("product_price")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
