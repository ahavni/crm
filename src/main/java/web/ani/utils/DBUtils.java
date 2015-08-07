package web.ani.utils;

import org.apache.log4j.Logger;
import web.ani.beans.Customer;
import web.ani.beans.User;
import web.ani.servlets.SearchUserServlet;

import java.awt.image.DataBufferInt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    final static Logger logger = Logger.getLogger(DBUtils.class);

    public static Connection createDBConnection() throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver);
        String url = "jdbc:derby:masterDB;create=true";
        logger.info("DB connection is initialized.");
        return DriverManager.getConnection(url);
    }

    public static void closeDBConnection(Connection conn) {
        try {
            conn.close();
            logger.info("DB connection is terminated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUserTable(Connection conn) throws SQLException {
        Statement stmt = null;
        logger.info("Creating USERS table in given database...");
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
            logger.info("USERS table created");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void createUserRelationsTable(Connection conn) throws SQLException {
        Statement stmt = null;
        logger.info("Creating USERS_RELATIONS table in given database...");
        stmt = conn.createStatement();
        try {
            String sql = "CREATE TABLE USERS_RELATIONS " +
                    "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    " consultant_id INTEGER, " +
                    " customer_id INTEGER, " +
                    " PRIMARY KEY (id), " +
                    " FOREIGN KEY (consultant_id) REFERENCES USERS(id), " +
                    " FOREIGN KEY (customer_id) REFERENCES USERS(id))";

            stmt.executeUpdate(sql);
            logger.info("USERS_RELATIONS table created");
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
            logger.info("User with email: " + user.getEmail() + " is added to the DB");
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
                    logger.info("User with email: " + user.getEmail() + " is got from DB");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usersList;
        }

    public static ArrayList<User> getUsersFromDB(Connection conn, String userType) {
        Statement stmt = null;
        ArrayList<User> usersList = new ArrayList<User>();
        User user;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS where user_type = \'" + userType + "\'";
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
                logger.info("User with email: " + user.getEmail() + " is got from DB");
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
                logger.info("User with email: " + user.getEmail() + " is got from DB");
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
                    " WHERE EMAIL='" + user.getEmail() + "'");// get it from session?
            logger.info("User with email: " + user.getEmail() + " has updated data");

            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static void updateUserExceptPass(Connection conn, User user) {
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
                    + "user_type='" + user.getUserType() + "'" +
                    " WHERE EMAIL='" + user.getEmail() + "'"); // get it from session?
            logger.info("User with email: " + user.getEmail() + " has updated data");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static ArrayList<User> searchUsersFromDB(Connection conn, String whereCause) {
        Statement stmt = null;
        ArrayList<User> usersList = new ArrayList<User>();
        User user;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS where " + whereCause;

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                user = new Customer();

                user.setFistName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getInt("age"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getString("sex"));
                user.setUserType(rs.getString("user_type"));
                user.setPassword(rs.getString("pass"));

                logger.info("User with email: " + user.getEmail() + " is found in DB");

                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

}