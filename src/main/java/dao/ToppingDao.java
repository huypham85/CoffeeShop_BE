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
import model.Topping;
import utils.NetworkUtils;

/**
 *
 * @author HUY PHAM
 */
public class ToppingDao {

    private static final String INSERT_TOPPING = "INSERT INTO topping (name_topping, price_plus) values (?,?)";
    private static final String UPDATE_TOPPING = "UPDATE topping set name_topping = ?, price_plus = ? WHERE id_topping = ?";
    private static final String DELETE_TOPPING = "DELETE from topping WHERE id_topping = ?";
    private static final String GET_TOPPING = "SELECT * FROM topping";

    public int insertNewTopping(Topping topping) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_TOPPING)) {

            preparedStatement.setString(1, topping.getName_topping());
            preparedStatement.setDouble(2, topping.getPrice_plus());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return result;
    }

    public int updateTopping(Topping topping) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(UPDATE_TOPPING)) {

            preparedStatement.setString(1, topping.getName_topping());
            preparedStatement.setDouble(2, topping.getPrice_plus());
            preparedStatement.setInt(3, topping.getId_topping());
            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public int deleteTopping(int id_topping) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_TOPPING)) {
            preparedStatement.setInt(1, id_topping);
            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public List<Topping> getTopping() {
        List<Topping> list = new ArrayList<Topping>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_TOPPING)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Topping topping = new Topping();
                topping.setId_topping(result.getInt(1));
                topping.setName_topping(result.getString(2));
                topping.setPrice_plus(result.getDouble(3));
                list.add(topping);
            }


        } catch (SQLException e) {

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
