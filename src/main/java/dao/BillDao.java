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
import model.Bill;
import model.Drink;
import model.BillResponse;
import utils.NetworkUtils;

/**
 *
 * @author HUY PHAM
 */
public class BillDao {

    private static final String INSERT_BILL = "INSERT INTO bill (id_customer, note, address, order_day) values (?,?,?,?)";
    private static final String GET_BILLS = "SELECT * FROM bill";
    private static final String GET_BILL_BY_ID = "SELECT * FROM bill WHERE id_bill = ?";
    private static final String DELETE_BILL = "DELETE from bill WHERE id_bill = ?";
    
    private CustomerDao customerDao;

    public BillDao() {
        customerDao = new CustomerDao();
    }
    
    

    public int insertNewBill(Bill bill) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_BILL)) {

            preparedStatement.setInt(1, bill.getId_customer());
            preparedStatement.setString(2, bill.getNote());
            preparedStatement.setString(3, bill.getAddress());
            preparedStatement.setDate(4, bill.getOrder_day());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process INSERT_BILL exception
            printSQLException(e);
        }
        return result;
    }

    public List<BillResponse> getBills() {
        List<BillResponse> list = new ArrayList<BillResponse>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_BILLS)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                BillResponse bill = new BillResponse();
                bill.setId_bill(result.getInt(1));
                bill.setCustomer(customerDao.getCustomerById(result.getInt(1)));
                bill.setNote(result.getString(3));
                bill.setAddress(result.getString(4));
                bill.setOrder_day(result.getDate(5));
                list.add(bill);
            }

        } catch (SQLException e) {
            // process INSERT_BILL exception
            printSQLException(e);
        }
        return list;
    }
    
    public Bill getBillById(int id) {
        Bill res = new Bill();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_BILL_BY_ID)) {
            
            preparedStatement.setInt(1, id);

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                res.setId_bill(result.getInt(1));
                res.setId_customer(result.getInt(2));
                res.setNote(result.getString(3));
                res.setAddress(result.getString(4));
                res.setOrder_day(result.getDate(5));
            }

        } catch (SQLException e) {
            // process INSERT_BILL exception
            printSQLException(e);
        }
        return res;
    }

    public int deleteBill(int id_bill) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_BILL)) {
            preparedStatement.setInt(1, id_bill);
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
