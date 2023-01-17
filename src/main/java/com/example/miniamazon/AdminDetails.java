package com.example.miniamazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDetails {
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
        }

        return productName;
    }
}
