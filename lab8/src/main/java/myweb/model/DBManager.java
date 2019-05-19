package myweb.model;

import myweb.domain.City;
import myweb.domain.User;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Statement statement;
    private String driver = "org.gjt.mm.mysql.Driver";
    private String url = "jdbc:mysql://localhost/transportation";
    private String user = "root";
    private String password = "password";
    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet resultSet;
        User user = null;
        System.out.println(username + password);
        try {
            resultSet = statement.executeQuery("select * from user where username = '" + username + " 'and password = '" + password + "'");
            if (resultSet.next()){
                user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<City> getCities() {
        ResultSet resultSet;
        ArrayList<City> result = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from city");
            while(resultSet.next()){
                City city = new City(resultSet.getInt("id"), resultSet.getString("name"));
                result.add(city);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<City> getNeighboursCities(int cityID) {
        ResultSet resultSet;
        ArrayList<City> result = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select id, name from city inner join neighbour on id = city2 and city1 = " +cityID);
            while(resultSet.next()){
                City city = new City(resultSet.getInt("id"), resultSet.getString("name"));
                result.add(city);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
