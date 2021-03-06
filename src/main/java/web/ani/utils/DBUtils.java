package web.ani.utils;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;
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

    public static void createUserTable() {
        logger.info("Entering DBUtils createUserTable() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }

    }

    public static void createProductsTable() {
        logger.info("Entering DBUtils createProductsTable() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "CREATE TABLE PRODUCTS " +
                        "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                        " name VARCHAR(255), " +
                        " PRIMARY KEY (id)) ";
            logger.info("Creating PRODUCTS table in given database...");
            stmt.executeUpdate(sql);
            logger.info("PRODUCTS table created");
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static void createUserRelationsTable() {
        logger.info("Entering DBUtils createUserRelationsTable() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static void createProductsRelationsTable(){
        logger.info("Entering DBUtils createProductsRelationsTable() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static void addUserInDB(User user){
        logger.info("Entering DBUtils addUserInDB() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static void addProductInDB(String product){
        logger.info("Entering DBUtils addProductInDB() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO PRODUCTS " +
                        " (name) " + "values ('"
                        + product + "')");
            logger.info("Product: " + product + " is added to the DB");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static ArrayList<String> getProductsFromDB(){
        logger.info("Entering DBUtils getProductsFromDB() method ");

        ArrayList<String> productsList = new ArrayList<String>();
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM PRODUCTS";
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting users from database...");
            while (rs.next()) {
                productsList.add(rs.getString("name"));
                logger.info("Product: " + rs.getString("name") + " is got from DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return productsList;
    }

    public static ArrayList<String> getCustomersProductsFromDB(int customerId){
        logger.info("Entering DBUtils getCustomersProductsFromDB() method ");

        ArrayList<String> productsList = new ArrayList<String>();
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT PRODUCTS.name " +
                        "FROM PRODUCTS join products_RELATIONS ON PRODUCTS_RELATIONS.product_id = PRODUCTS.ID " +
                        "where PRODUCTS_RELATIONS.customer_id =" + customerId;
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting products of customer with id= " + customerId + " from database...");
            while (rs.next()) {
                productsList.add(rs.getString("name"));
                logger.info("Product: " + rs.getString("name") + " is got from DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return productsList;
    }

    public static ArrayList<Customer> getConsultantsCustomersFromDB(int consultantId){
        logger.info("Entering DBUtils getConsultantsCustomersFromDB() method ");

        ArrayList<Customer> usersList = new ArrayList<Customer>();
        Customer user;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT users.* " +
                        "FROM USERS join USERS_RELATIONS ON USERS_RELATIONS.customer_id = USERS.ID " +
                        "where USERS_RELATIONS.consultant_id = " + consultantId ;
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("Getting customers of consultant with id= " + consultantId + " from database...");
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

                usersList.add(user);
                logger.info("Customer with email: " + user.getEmail() + " is got from DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return usersList;
    }

    public static ArrayList<User> getUsersFromDB() {
        logger.info("Entering DBUtils getUsersFromDB() method ");

        ArrayList<User> usersList = new ArrayList<User>();
        User user;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return usersList;
    }

    public static ArrayList<User> getUsersFromDB(String userType) {
        logger.info("Entering DBUtils getUsersFromDB() method ");

        ArrayList<User> usersList = new ArrayList<User>();
        User user;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS WHERE user_type='" + userType + "'";
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
                logger.info("User with email: " + user.getEmail() + " is got from DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return usersList;
    }

    public static User getUserByEmailFromDB(String emailP){
        logger.info("Entering DBUtils getUserByEmailFromDB() method ");

        User user = null;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS WHERE EMAIL='" + emailP + "'";
            ResultSet rs = stmt.executeQuery(sql);
            boolean isConsultant = false;

            logger.info("Getting user from database...");
            while (rs.next()) {
                if (user == null){
                    user = new User();
                }

                isConsultant = rs.getString("user_type").equals("consultant");
                user = isConsultant ? new Consultant() : new Customer();

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }

        return user;
    }

    public static void updateUserInDB(User user) {
        logger.info("Entering DBUtils updateUserInDB() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static void updateUserExceptPassInDB(User user) {
        logger.info("Entering DBUtils updateUserExceptPassInDB() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static ArrayList<User> searchUsersInDB(String whereClause) {
        logger.info("Entering DBUtils searchUsersInDB() method ");

        ArrayList<User> usersList = new ArrayList<User>();
        User user;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT * FROM USERS where " + whereClause;
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return usersList;
    }

    public static int getUserIDFromDB(String emailP){
        logger.info("Entering DBUtils getUserIDFromDB() method ");

        int id = 0;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT id FROM USERS WHERE EMAIL='" + emailP + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("id");
            }
            logger.info("User with email: " + emailP + " has ID = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return id;
    }

    public static int getProductIDFromDB(String product){
        logger.info("Entering DBUtils getProductIDFromDB() method ");

        int id = 0;
        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Preparing SQL statement...");
            String sql = "SELECT id FROM PRODUCTS WHERE name='" + product + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("id");
            }
            logger.info("Product " + product + " has ID = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        return id;
    }

    public static void assignUsersInDB(int consultantId, int customerId) {
        logger.info("Entering DBUtils assignUsersInDB() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO USERS_RELATIONS " +
                        " (consultant_id, customer_id) "
                        + "VALUES(" + consultantId + ","
                        + customerId + ")");
            stmt.close();
            logger.info("Customer with ID=" + consultantId + " assigned to consultant with ID=" + customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    public static void assignProductToUserInDB(int customerId, int productId) {
        logger.info("Entering DBUtils assignProductToUserInDB() method ");

        Connection conn = null;
        Statement stmt;
        try {
            conn = DBUtils.createDBConnection();
            stmt = conn.createStatement();
            logger.info("Executing SQL statement...");
            stmt.execute("INSERT INTO PRODUCTS_RELATIONS " +
                        " (customer_id, product_id) "
                        + "VALUES(" + customerId + ","
                        + productId + ")");
            stmt.close();
            logger.info("Customer with ID=" + customerId + " assigned to product with ID=" + productId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }
}