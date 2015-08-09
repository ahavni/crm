package web.ani.utils;

import org.apache.log4j.Logger;
import web.ani.beans.Customer;
import web.ani.beans.User;
import java.sql.*;
import java.util.ArrayList;

public class DBUtils {
    final static Logger logger = Logger.getLogger(DBUtils.class);

    public static Connection createDBConnection() throws SQLException, ClassNotFoundException {
        logger.info("Entering DBUtils createDBConnection() method ");
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver);
        String url = "jdbc:derby:masterDB;create=true";
        logger.info("DB connection is initialized.");
        return DriverManager.getConnection(url);
    }

    public static void closeDBConnection(Connection conn) {
        logger.info("Entering DBUtils closeDBConnection() method ");
        try {
            conn.close();
            logger.info("DB connection is terminated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUserTable(Connection conn) throws SQLException {
        logger.info("Entering DBUtils createUserTable() method ");


        try {
            Statement stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
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
            logger.info("Creating USERS table in given database...");
            stmt.executeUpdate(sql);
            logger.info("USERS table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createProductsTable(Connection conn) throws SQLException {
        logger.info("Entering DBUtils createProductsTable() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Preparing SQL statement...");
            String sql = "CREATE TABLE PRODUCTS " +
                        "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                        " name VARCHAR(255), " +
                        " PRIMARY KEY (id)) ";
            logger.info("Creating PRODUCTS table in given database...");
            stmt.executeUpdate(sql);
            logger.info("PRODUCTS table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUserRelationsTable(Connection conn) throws SQLException {
        logger.info("Entering DBUtils createUserRelationsTable() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Preparing SQL statement...");
            String sql = "CREATE TABLE USERS_RELATIONS " +
                        "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                        " consultant_id INTEGER, " +
                        " customer_id INTEGER, " +
                        " PRIMARY KEY (id), " +
                        " FOREIGN KEY (consultant_id) REFERENCES USERS(id), " +
                        " FOREIGN KEY (customer_id) REFERENCES USERS(id))";
            logger.info("Creating USERS_RELATIONS table in given database...");
            stmt.executeUpdate(sql);
            logger.info("USERS_RELATIONS table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createProductsRelationsTable(Connection conn) throws SQLException {
        logger.info("Entering DBUtils createProductsRelationsTable() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Preparing SQL statement...");
            String sql = "CREATE TABLE PRODUCTS_RELATIONS " +
                    "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    " customer_id INTEGER, " +
                    " product_id INTEGER, " +
                    " PRIMARY KEY (id), " +
                    " FOREIGN KEY (customer_id) REFERENCES USERS(id), " +
                    " FOREIGN KEY (product_id) REFERENCES PRODUCTS(id))";
            logger.info("Creating PRODUCTS_RELATIONS table in given database...");
            stmt.executeUpdate(sql);
            logger.info("PRODUCTS_RELATIONS table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(Connection conn, User user) throws SQLException{
        logger.info("Entering DBUtils addUser() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO USERS " +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Connection conn, String product) throws SQLException{
        logger.info("Entering DBUtils addProduct() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO PRODUCTS " +
                        " (name) " + "values ('"
                        + product + "')");
            logger.info("Product: " + product + " is added to the DB");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsersFromDB(Connection conn) throws SQLException{
        logger.info("Entering DBUtils getUsersFromDB() method ");

        Statement stmt = conn.createStatement();;
        ArrayList<User> usersList = new ArrayList<User>();
        User user;
        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS";
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting users from database...");
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

    public static ArrayList<User> getUsersFromDB(Connection conn, String userType) throws SQLException{
        logger.info("Entering DBUtils getUsersFromDB() method ");

        Statement stmt = conn.createStatement();
        ArrayList<User> usersList = new ArrayList<User>();
        User user;
        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS where user_type = \'" + userType + "\'";
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting " + userType + " from database...");
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
                logger.info(userType + " with email: " + user.getEmail() + " is got from DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public static ArrayList<String> getProductsFromDB(Connection conn) throws SQLException{
        logger.info("Entering DBUtils getProductsFromDB() method ");

        Statement stmt = conn.createStatement();
        ArrayList<String> productList = new ArrayList<String>();

        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM PRODUCTS";
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting products from database...");
            while (rs.next()) {
                productList.add(rs.getString("name"));
                logger.info("Product: " + rs.getString("name") + " is got from DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static User getUserByEmail(Connection conn, String emailP) throws SQLException{
        logger.info("Entering DBUtils getUserByEmail() method ");

        Statement stmt = conn.createStatement();;
        User user = new User();
        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS WHERE EMAIL='" + emailP + "'";
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting user from database...");
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

    public static void updateUser(Connection conn, User user) throws SQLException{
        logger.info("Entering DBUtils updateUser() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Executing SQL statement...");
            stmt.execute("UPDATE USERS " +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserExceptPass(Connection conn, User user) throws SQLException{
        logger.info("Entering DBUtils updateUserExceptPass() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Executing SQL statement...");
            stmt.execute("UPDATE USERS " +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> searchUsersFromDB(Connection conn, String whereCause) throws SQLException{
        logger.info("Entering DBUtils searchUsersFromDB() method ");

        Statement stmt = conn.createStatement();
        ArrayList<User> usersList = new ArrayList<User>();
        User user;
        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS where " + whereCause;
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Checking results...");
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

                logger.info("User with email: " + user.getEmail() + " is added to found users list results");
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public static int getUserID(Connection conn, String emailP) throws SQLException{
        logger.info("Entering DBUtils getUserID() method ");

        Statement stmt = conn.createStatement();
        int id = 0;
        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT id FROM USERS WHERE EMAIL='" + emailP + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("id");
            }
            logger.info("User with email: " + emailP + " has ID = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getProductID(Connection conn, String product) throws SQLException{
        logger.info("Entering DBUtils getProductID() method ");

        Statement stmt = conn.createStatement();
        int id = 0;
        try {
            logger.info("Preparing SQL statement...");
            String sql = "SELECT id FROM PRODUCTS WHERE name='" + product + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("id");
            }
            logger.info("Product " + product + " has ID = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void assignUsers(Connection conn, int consultantId, int customerId)throws SQLException{
        logger.info("Entering DBUtils assignUsers() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO USERS_RELATIONS " +
                        " (consultant_id, customer_id) "
                        + "VALUES(" + consultantId + ","
                        + customerId + ")");
            stmt.close();
            logger.info("Customer with ID=" + consultantId + " assigned to consultant with ID=" + customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void assignProductToUser(Connection conn, int customerId, int productId)throws SQLException{
        logger.info("Entering DBUtils assignProductToUser() method ");

        Statement stmt = conn.createStatement();
        try {
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO PRODUCTS_RELATIONS " +
                        " (customer_id, product_id) "
                        + "VALUES(" + customerId + ","
                        + productId + ")");
            stmt.close();
            logger.info("Customer with ID=" + customerId + " assigned to product with ID=" + productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}