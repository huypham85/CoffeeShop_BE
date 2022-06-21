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
import model.Size;

/**
 *
 * @author hdmin
 */
public class SizeDao {
    public static final String url = "jdbc:mysql://103.141.141.29:6603/mysql-heroku";
    public static final String user = "root";
    public static final String password = "password";

    private static final String INSERT_SIZE = "INSERT INTO size (percent_plus,name_size) values (?,?)";
    private static final String GET_SIZES = "SELECT id_size, percent_plus,name_size FROM size";
    private static final String UPDATE_SIZE = "UPDATE size set percent_plus = ?,name_size=? WHERE id_size = ?";
    private static final String DELETE_SIZE = "DELETE from size WHERE id_size = ?";


    public int insertNewSize(Size size) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_SIZE)) {

            preparedStatement.setFloat(1, size.getPercent_plus());
            preparedStatement.setString(2, size.getName_size());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public List<Size> getSizes(){
        List<Size> list = new ArrayList<Size>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_SIZES)) {

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

            printSQLException(e);
        }
        return list;
    }
    
    public int updateSize(Size size){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(UPDATE_SIZE)) {

            preparedStatement.setString(2, size.getName_size());
            preparedStatement.setFloat(1, size.getPercent_plus());
            preparedStatement.setInt(3, size.getId_size());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public int deleteSize(int id){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_SIZE)) {

            preparedStatement.setInt(1, id);

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

