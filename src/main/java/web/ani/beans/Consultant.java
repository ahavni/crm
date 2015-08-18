package web.ani.beans;

import web.ani.utils.DBUtils;

import java.util.ArrayList;

public class Consultant extends User {
    protected ArrayList<Customer> customerslist;

    public Consultant(){
        super();
    }

    public Consultant(String fistNameP, String lastNameP, int ageP,
                      String addressP, String emailP, String sexP,
                      String passwordP) {
        super(fistNameP, lastNameP, ageP, addressP, emailP, sexP, "consultant", passwordP);
    }

    public ArrayList<Customer> getCustomersList(Consultant user) {
        return customerslist;
    }

    public void setCustomersList(Consultant user) {
        customerslist =  DBUtils.getConsultantsCustomersFromDB(DBUtils.getUserIDFromDB(user.getEmail()));
    }

    public ArrayList<User> listAllUsers(){
        return DBUtils.getUsersFromDB();
    }

    public ArrayList<User> listUserTypes(String userType){
        return DBUtils.getUsersFromDB(userType);
    }

    public ArrayList<User> listAllCustomers(){
        return listUserTypes("customer");
    }

    public ArrayList<User> listAllConsultants(){
        return listUserTypes("consultant");
    }

    public void addProduct(String product){
        DBUtils.addProductInDB(product);
    }

    public void assignUsers(String consultantEmail, String customerEmail ){
        DBUtils.assignUsersInDB(DBUtils.getUserIDFromDB(consultantEmail), DBUtils.getUserIDFromDB(customerEmail));
    }


}
