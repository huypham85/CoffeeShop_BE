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
import model.BillDetail;
import model.BillReturn;
import model.BookTopping;
import model.Customer;
import model.Drink;
import model.Size;
import utils.NetworkUtils;
import static utils.NetworkUtils.password;
import static utils.NetworkUtils.url;
import static utils.NetworkUtils.user;

/**
 *
 * @author Dang Dung
 */
public class BillDetailDao {
    private static final String INSERT_BILL = "INSERT INTO bill (id_customer, note, address, order_day) values (?,?,?,?)";
    private static final String INSERT_BOOK_TOPPING = "INSERT INTO booktopping (id_topping, id_bill_detail) values (?,?)";
    private static final String INSERT_BILL_DETAIL = "INSERT INTO billdetail (id_bill, id_drink, price, id_size, note, amount) values (?,?,?,?,?,?)";
    private static final String GET_BILLS = "SELECT * FROM bill";
    private static final String DELETE_BILL = "DELETE from bill WHERE id_bill = ?";
    private static final String GET_CUSTOMERS_BY_PHONE_NUMBER = "SELECT DISTINCT id_customer, name_customer, phone_number, address FROM customer WHERE phone_number = ?";
    private static final String GET_LAST_BILL_ID = "SELECT MAX(id_bill) from bill";
    private static final String GET_LAST_BILL_DETAIL_ID = "SELECT MAX(id_bill_detail) from billdetail";
    private static final String GET_DRINK_BY_ID = "SELECT * FROM drink WHERE id_drink = ?";
    private static final String GET_SIZE_BY_ID = "SELECT * FROM size WHERE id_size = ?";
    private static final String GET_BILL_DETAIL = "SELECT * from billdetail WHERE id_bill_detail = ?";
    private static final String GET_BILL_DETAIL_BY_BILL = "SELECT * from billdetail WHERE id_bill = ?";
    
    private GetDrinkDao getDrinkDao;
    private BillDao billDao;
    private CustomerDao customerDao;

    public BillDetailDao() {
        getDrinkDao = new GetDrinkDao();
        billDao = new BillDao();
        customerDao = new CustomerDao();
    }
    
    public BillReturn getBillDisplay(int id) {
        BillReturn billReturn = new BillReturn();
        Bill bill = billDao.getBillById(id);
        System.err.println(bill.getId_bill());
        billReturn.setBill(bill);
        billReturn.setCustomer(customerDao.getCustomerById(bill.getId_customer()));
        List<BillDetail> list = getListBillDetail(id);
        double totalMoney = 0;
        for (BillDetail i : list) {
            totalMoney += i.getPrice()*i.getAmount();
        }
        billReturn.setListBillDetail(list);
        billReturn.setTotalMoney(totalMoney);
        return billReturn;
    }
    
    public List<BillDetail> getListBillDetail(int id) {
        List<BillDetail> res = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_BILL_DETAIL_BY_BILL)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                BillDetail billDetail = new BillDetail();
                billDetail.setId(result.getInt(1));
                billDetail.setId_bill(result.getInt(2));
                billDetail.setDrink(getDrinkById(result.getInt(3)));
                billDetail.setPrice(result.getDouble(4));
                billDetail.setSize(getSizeById(result.getInt(5)));
                billDetail.setNote(result.getString(6));
                billDetail.setAmount(result.getInt(7));
                res.add(billDetail);
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return res;
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
    
    public int insertNewBillDetail(BillDetail billDetail) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_BILL_DETAIL)) {

            //"INSERT INTO billdetail (id_bill, id_drink, price, id_size, note, amount) values (?,?,?,?,?,?)"
            preparedStatement.setInt(1, billDetail.getId_bill());
            preparedStatement.setInt(2, billDetail.getDrink().getId_drink());
            preparedStatement.setDouble(3, billDetail.getPrice());
            preparedStatement.setInt(4, billDetail.getSize().getId_size());
            preparedStatement.setString(5, billDetail.getNote());
            preparedStatement.setInt(6, billDetail.getAmount());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process INSERT_BILL exception
            printSQLException(e);
        }
        return result;
    }
    public int insertBookTopping(BookTopping bookTopping) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_BOOK_TOPPING)) {

            //"INSERT INTO billdetail (id_bill, id_drink, price, id_size, note, amount) values (?,?,?,?,?,?)"
            preparedStatement.setInt(1, bookTopping.getId_topping());
            preparedStatement.setInt(2, bookTopping.getId_bill_detail());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process INSERT_BILL exception
            printSQLException(e);
        }
        return result;
    }
    
    public Customer getCustomerByPhoneNumber(Customer pnb){
        Customer res = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_CUSTOMERS_BY_PHONE_NUMBER)) {

            // sends the statement to the database server
            preparedStatement.setString(1, pnb.getPhone_number());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                res = new Customer();
                res.setId_customer(result.getInt(1));
                res.setName_customer(result.getString(2));
                res.setPhone_number(result.getString(3));
                res.setAddress(result.getString(4));
            }

        } catch (SQLException e) {

            printSQLException(e);
        }
        return res;
    }
    
    
    public int getLatedBill(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_LAST_BILL_ID)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                return result.getInt(1);
            }
        } catch (SQLException e) {

            printSQLException(e);
        }
        return -1;
    }
    
    public int getLatedBillDetail(){
        Customer res = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_LAST_BILL_DETAIL_ID)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                return result.getInt(1);
            }

        } catch (SQLException e) {

            printSQLException(e);
        }
        return -1;
    }
    
    public Drink getDrinkById(int id) {
        Drink res = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_DRINK_BY_ID)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                res = new Drink();
                res.setId_drink(result.getInt(1));
                res.setName_drink(result.getString(2));
                res.setPrice(result.getDouble(3));
                res.setImg(result.getString(4));
                res.setId_type(result.getInt(5));
                res.setDescript(result.getString(6));
            }

        } catch (SQLException e) {
            // process INSERT_DRINK exception
            printSQLException(e);
        }
        return res;
    }
    
    public Size getSizeById(int id) {
        Size res = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(NetworkUtils.url, NetworkUtils.user, NetworkUtils.password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_SIZE_BY_ID)) {

            // sends the statement to the database server
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                res = new Size();
                res.setId_size(result.getInt(1));
                res.setPercent_plus(result.getFloat(2));
                res.setName_size(result.getString(3));
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
