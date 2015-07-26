package web.ani.utils;

import web.ani.beans.Customer;
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

    public static void createUserTable(Connection conn) throws SQLException {
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
                            " user_type VARCHAR(10), " +
                            " pass VARCHAR(100), " +
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
            stmt.execute("INSERT INTO " + tableName +
                            " (firstName, lastName, age, address, email, sex, user_type, pass) " + "values ('"
                            + user.getFistName() + "','"
                            + user.getLastName() + "',"
                            + user.getAge() + ",'"
                            + user.getAddress() + "','"
                            + user.getEmail() + "','"
                            + user.getSex() + "','"
                            + user.getUserType() + "','"
                            + user.getPassword() + "')");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

        public static ArrayList<User> getUsersFromDB(Connection conn) {
            Statement stmt = null;
            ArrayList<User> usersList = new ArrayList<User>();
            User user;

            try {
                stmt = conn.createStatement();
                String sql = "SELECT * FROM USERS";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    user = new User();

                    user.setFistName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setAge(rs.getInt("age"));
                    user.setAddress(rs.getString("address"));
                    user.setEmail(rs.getString("email"));
                    user.setSex(rs.getString("sex"));
                    user.setUserType(rs.getString("user_type"));
                    user.setPassword(rs.getString("pass"));

                    usersList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usersList;
        }

    public static User getUserByEmail(Connection conn, String emailP){
        Statement stmt = null;
        User user = new User();

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS WHERE EMAIL='" + emailP + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                user.setFistName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getInt("age"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getString("sex"));
                user.setUserType(rs.getString("user_type"));
                user.setPassword(rs.getString("pass"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUser(Connection conn, User user) {
        Statement stmt = null;
        String tableName = "USERS";
        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE " + tableName +
                    " SET "
                    + "firstName='" + user.getFistName() + "',"
                    + "lastName='" + user.getLastName() + "',"
                    + "age=" + user.getAge() + ","
                    + "address='" + user.getAddress() + "',"
                    + "email='" + user.getEmail() + "',"
                    + "sex='" + user.getSex() + "',"
                    + "user_type='" + user.getUserType() + "',"
                    + "pass='" + user.getPassword() + "'" +
                    " WHERE EMAIL='" + user.getEmail() + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static ArrayList<Customer> getCustomersFromDB(Connection conn) {
        Statement stmt = null;
        ArrayList<Customer> customersList = new ArrayList<Customer>();
        Customer customer;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS where user_type = \'customer\'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customer = new Customer();

                customer.setFistName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setAge(rs.getInt("age"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setSex(rs.getString("sex"));
                customer.setUserType(rs.getString("user_type"));
                customer.setPassword(rs.getString("pass"));

                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }
}