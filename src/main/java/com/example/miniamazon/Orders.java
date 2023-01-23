package com.example.miniamazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Orders {
    public static void placeOrder(String customer_email, Products product){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniamazon","root","123456");
            preparedStatement = connection.prepareStatement("INSERT INTO orders (user_id, product_id) VALUES ((SELECT user_id FROM users WHERE email = ? ),?)");
            preparedStatement.setString(1,customer_email);
            preparedStatement.setInt(2,product.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
