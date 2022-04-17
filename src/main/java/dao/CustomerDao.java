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

/**
 *
 * @author hdmin
 */
public class CustomerDao {
    private static final String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_f866d7997438cb1?useSSL=false";
    private static final String user = "bf9faddc3d7c52";
    private static final String password = "fb891e29";

    private static final String INSERT_CUSTOMER = "INSERT INTO customer (name_customer, phone_number, address) values (?,?,?)";
    private static final String GET_CUSTOMERS = "SELECT name_customer, phone_number, address FROM customer";
    private static final String UPDATE_CUSTOMER = "UPDATE customer set phone_number = ?, address = ? WHERE name_customer = ?";
    private static final String DELETE_CUSTOMER = "DELETE from customer WHERE name_customer = ?";

    public int insertNewCustomer(Customer customer) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_CUSTOMER)) {

            preparedStatement.setString(1, customer.getName_customer());
            preparedStatement.setString(2, customer.getPhone_number());
            preparedStatement.setString(3, customer.getAddress());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public List<Customer> getCustomers(){
        List<Customer> list = new ArrayList<Customer>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_CUSTOMERS)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Customer customer = new Customer();
                customer.setName_customer(result.getString(1));
                customer.setPhone_number(result.getString(2));
                customer.setAddress(result.getString(3));
                list.add(customer);
            }

        } catch (SQLException e) {

            printSQLException(e);
        }
        return list;
    }
    
    public int updateCustomer(Customer customer){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(UPDATE_CUSTOMER)) {

            preparedStatement.setString(3, customer.getName_customer());
            preparedStatement.setString(1, customer.getPhone_number());
            preparedStatement.setString(2, customer.getAddress());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public int deleteCustomer(Customer customer){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_CUSTOMER)) {

            preparedStatement.setString(3, customer.getName_customer());

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