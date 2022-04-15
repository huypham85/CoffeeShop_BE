/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Drink;

/**
 *
 * @author HUY PHAM
 */
public class CoffeeDao {

    private static final String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_f866d7997438cb1?useSSL=false";
    private static final String user = "bf9faddc3d7c52";
    private static final String password = "fb891e29";

    private static final String INSERT_DRINK = "INSERT INTO drink (name_drink, price, img, id_type, descript) values (?,?,?,?,?)";
    private static final String GET_DRINKS = "SELECT name_drink, price, img, id_type, descript FROM drink";

    public int insertNewDrink(Drink drink) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_DRINK)) {

            preparedStatement.setString(1, drink.getName_drink());
            preparedStatement.setDouble(2, drink.getPrice());
            preparedStatement.setString(3, drink.getImg());
            preparedStatement.setInt(4, drink.getId_type());
            preparedStatement.setString(5, drink.getDescript());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return result;
    }
    
    public List<Drink> getDrinks(){
        List<Drink> list = new ArrayList<Drink>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_DRINKS)) {

            // sends the statement to the database server
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
