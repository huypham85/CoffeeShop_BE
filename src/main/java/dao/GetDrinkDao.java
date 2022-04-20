/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Drink;

/**
 *
 * @author Dang Dung
 */
public class GetDrinkDao {
    private static final String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_f866d7997438cb1?useSSL=false";
    private static final String user = "bf9faddc3d7c52";
    private static final String password = "fb891e29";

    private static final String INSERT_CUSTOMER = "INSERT INTO customer (name_customer, phone_number, address) values (?,?,?)";
    private static final String GET_DRINKS_BY_CATEGORY = "SELECT name_drink, price, img, id_type, descript FROM drink WHERE id_type = ?";
    private static final String GET_DRINKS_BY_NAME = "SELECT * FROM drink WHERE name_drink LIKE ?";
    private static final String GET_TOPPING_BY_DRINK = "SELECT * FROM drinktopping WHERE id_drink = ?";
    private static final String GET_SIZE = "SELECT * FROM size";
    
    public List<Drink> getDrinksByCategory(int id_type){
        List<Drink> list = new ArrayList<Drink>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_DRINKS_BY_CATEGORY)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id_type);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Drink drink = new Drink();
                drink.setName_drink(result.getString(1));
                drink.setPrice(result.getDouble(2));
                drink.setImg(result.getString(3));
                drink.setId_type(result.getInt(4));
                drink.setDescript(result.getString(5));
                list.add(drink);
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return list;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
