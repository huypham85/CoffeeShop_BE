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
import model.DrinkDetail;
import model.Size;
import model.Topping;

/**
 *
 * @author Dang Dung
 */
public class GetDrinkDao {
    public static final String url = "jdbc:mysql://103.141.141.29:6603/mysql-heroku";
    public static final String user = "root";
    public static final String password = "password";

    private static final String INSERT_CUSTOMER = "INSERT INTO customer (name_customer, phone_number, address) values (?,?,?)";
    private static final String GET_DRINKS_BY_CATEGORY = "SELECT name_drink, price, img, id_type, descript FROM drink WHERE id_type = ?";
    private static final String GET_DRINKS_BY_NAME = "SELECT * FROM drink WHERE name_drink LIKE ?";//
    private static final String GET_TOPPING_BY_DRINK = "SELECT * FROM drinktopping WHERE id_drink = ?";
    private static final String GET_SIZE = "SELECT * FROM size";
    private static final String GET_DRINKS = "SELECT name_drink, price, img, id_type, descript FROM drink";
    private static final String GET_DRINKS_BY_ID = "SELECT * FROM drink WHERE id_drink = ?";
    private static final String GET_TOPPING_BY_ID = "SELECT * FROM topping WHERE id_topping = ?";
    
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
    
    public List<Drink> getDrinksByName(String name){
        List<Drink> list = new ArrayList<Drink>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_DRINKS_BY_NAME)) {

            // sends the statement to the database server
            
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
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
    
    public DrinkDetail getDrinkDetailById(int id) {
        DrinkDetail drinkDetail = new DrinkDetail();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_DRINKS_BY_ID)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                drinkDetail.setId(result.getInt(1));
                drinkDetail.setName_drink(result.getString(2));
                drinkDetail.setPrice(result.getDouble(3));
                drinkDetail.setImg(result.getString(4));
                drinkDetail.setId_type(result.getInt(5));
                drinkDetail.setListSize(getSize());
                drinkDetail.setListTopping(getToppingByIdDrink(id));
                drinkDetail.setDescript(result.getString(6));
//                drinkDetail.add(drinkDetail);
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return drinkDetail;
    }
    
    public List<Size> getSize() {
        List<Size> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_SIZE)) {

            // sends the statement to the database server
            
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Size size = new Size();
                size.setId_size(result.getInt(1));
                size.setPercent_plus(result.getFloat(2));
                size.setName_size(result.getString(3));
                list.add(size);
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return list;
    }
    
    public List<Topping> getToppingByIdDrink(int id) {
        List<Topping> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_TOPPING_BY_DRINK)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Topping topping = getToppingById(result.getInt(1));
                list.add(topping);
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return list;
    }
    public Topping getToppingById(int id) {
        Topping res = new Topping();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_TOPPING_BY_ID)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                res.setId_topping(result.getInt(1));
                res.setName_topping(result.getString(2));
                res.setPrice_plus(result.getDouble(3));
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return res;
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
