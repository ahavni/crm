package web.ani.beans;

import web.ani.utils.DBUtils;

import java.util.ArrayList;

public class Customer extends User{
    protected ArrayList<String> productsist;
    public Customer(){

    }
    public Customer(String fistNameP, String lastNameP, int ageP,
                    String addressP, String emailP, String sexP,
                    String passwordP) {
        super(fistNameP, lastNameP, ageP, addressP, emailP, sexP, "customer", passwordP);
    }

    public void buyProducts(String customerEmail, String product){
        DBUtils.assignProductToUser(DBUtils.getUserID(customerEmail),
                                    DBUtils.getProductID(product));
    }

    public void setCustomersProducts(Customer user){
        productsist = DBUtils.getCustomersProductsFromDB(DBUtils.getUserID(user.getEmail()));
    }

    public ArrayList<String> getCustomersProducts(Customer user){
        return productsist;
    }
}
