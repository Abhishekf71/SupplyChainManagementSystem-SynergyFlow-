package com.example.miniamazon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users {


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
}
