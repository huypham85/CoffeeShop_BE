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
import utils.NetworkUtils;

/**
 *
 * @author HUY PHAM
 */
public class DrinkDao {

    private static final String INSERT_DRINK = "INSERT INTO drink (name_drink, price, img, id_type, descript) values (?,?,?,?,?)";
    private static final String GET_DRINKS = "SELECT id_drink, name_drink, price, img, id_type, descript FROM drink";
    private static final String UPDATE_DRINK = "UPDATE drink set name_drink = ?, price = ?, img = ?, id_type = ?, descript = ? WHERE id_drink = ?";
    private static final String DELETE_DRINK = "DELETE from drink WHERE id_drink = ?";

    public int insertNewDrink(Drink drink) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
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

    public List<Drink> getDrinks() {
        List<Drink> list = new ArrayList<Drink>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_DRINKS)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Drink drink = new Drink();
                drink.setId_drink(result.getInt(1));
                drink.setName_drink(result.getString(2));
                drink.setPrice(result.getDouble(3));
                drink.setImg(result.getString(4));
                drink.setId_type(result.getInt(5));
                drink.setDescript(result.getString(6));
                list.add(drink);
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return list;
    }

    public int updateDrink(Drink drink) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(UPDATE_DRINK)) {

            preparedStatement.setString(1, drink.getName_drink());
            preparedStatement.setDouble(2, drink.getPrice());
            preparedStatement.setString(3, drink.getImg());
            preparedStatement.setInt(4, drink.getId_type());
            preparedStatement.setString(5, drink.getDescript());
            preparedStatement.setInt(6, drink.getId_drink());
            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public int deleteDrink(int id_drink) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_DRINK)) {
            preparedStatement.setInt(1, id_drink);
            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
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
