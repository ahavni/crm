package web.ani.utils;

import web.ani.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {


    public static Connection createDBConnection() throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver);
        String url = "jdbc:derby:masterDB;create=true";
        return DriverManager.getConnection(url);
    }

    public static void closeDBConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUSERStable(Connection conn) throws SQLException {
        Statement stmt = null;
        System.out.println("Creating table in given database...");
        stmt = conn.createStatement();
        try {
            String sql = "CREATE TABLE USERS " +
                    "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    " firstName VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age INTEGER, " +
                    " address VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " sex VARCHAR(255), " +
                    " PRIMARY KEY (id)) ";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void addUser(Connection conn, User user) {
        Statement stmt = null;
        String tableName = "USERS";
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " (firstName, lastName, age, address, email, sex) values ('"
                    + user.getFistName() + "','"
                    + user.getLastName() + "','"
                    + user.getAge() + "','"
                    + user.getAddress() + "','"
                    + user.getEmail() + "','"
                    + user.getSex() +
                    ")");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    /*
        public static List<User> getUsersFromDB(Connection conn) {
            System.out.println("Enter getUsersFromDB");
            Statement stmt = null;
            ArrayList<User> usersList = new ArrayList<User>();
            User user;

            try {
                stmt = conn.createStatement();
                String sql = "SELECT * FROM USERS";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(rs.toString());

                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setAge(rs.getInt("age"));
                    System.out.println(user);
                    usersList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usersList;
        }

     */

}