package web.ani.beans;

import web.ani.utils.DBUtils;

import java.util.ArrayList;

public class Consultant extends User {

    public Consultant(){
        super();
    }

    public Consultant(String fistNameP, String lastNameP, int ageP,
                      String addressP, String emailP, String sexP,
                      String passwordP) {
        super(fistNameP, lastNameP, ageP, addressP, emailP, sexP, "consultant", passwordP);
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
        DBUtils.addProduct(product);
    }

    public void assignUsers(String consultantEmail, String customerEmail ){
        DBUtils.assignUsers(DBUtils.getUserID(consultantEmail), DBUtils.getUserID(customerEmail));
    }



}
