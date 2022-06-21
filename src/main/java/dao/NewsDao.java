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
import model.News;

/**
 *
 * @author hdmin
 */
public class NewsDao {
    public static final String url = "jdbc:mysql://103.141.141.29:6603/mysql-heroku";
    public static final String user = "root";
    public static final String password = "password";
    private static final String INSERT_NEWS = "INSERT INTO news (name_news,img,descript) values (?,?,?)";
    private static final String GET_NEWS = "SELECT id_news,name_news,img,descript FROM news";
    private static final String GET_NEWS_ID = "SELECT id_news,name_news,img,descript FROM news WHERE id_news = ?";
    private static final String UPDATE_NEWS = "UPDATE news set name_news=?,img=?,descript=? WHERE id_news = ?";
    private static final String DELETE_NEWS = "DELETE from news WHERE id_news = ?";

    public int insertNews(News news) {
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_NEWS)) {

            preparedStatement.setString(1, news.getName_news());
            preparedStatement.setString(2, news.getImg());
            preparedStatement.setString(3, news.getDescript());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public List<News> getNewsById(int id){
        List<News> list = new ArrayList<News>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_NEWS_ID)) {
            
            preparedStatement.setInt(1, id);

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                News news = new News();
                news.setId_news(result.getInt(1));
                news.setName_news(result.getString(2));
                news.setImg(result.getString(3));
                news.setDescript(result.getString(4));
                list.add(news);
            }


        } catch (SQLException e) {

            printSQLException(e);
        }
        return list;
    }
    
    
    public List<News> getNews(){
        List<News> list = new ArrayList<News>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(GET_NEWS)) {

            // sends the statement to the database server
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                News news = new News();
                news.setId_news(result.getInt(1));
                news.setName_news(result.getString(2));
                news.setImg(result.getString(3));
                news.setDescript(result.getString(4));
                list.add(news);
            }


        } catch (SQLException e) {

            printSQLException(e);
        }
        return list;
    }
    
    public int updateNews(News news){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(UPDATE_NEWS)) {

            preparedStatement.setString(1, news.getName_news());
            preparedStatement.setString(2, news.getImg());
            preparedStatement.setString(3, news.getDescript());
            preparedStatement.setInt(4, news.getId_news());

            // sends the statement to the database server
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public int deleteNews(int id){
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try ( Connection connection = DriverManager
                .getConnection(url, user, password); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_NEWS)) {

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
