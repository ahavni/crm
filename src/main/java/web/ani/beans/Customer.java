package web.ani.beans;

import web.ani.utils.DBUtils;

import java.util.ArrayList;

public class Customer extends User{
    protected ArrayList<String> productsList;
    public Customer(){

    }
    public Customer(String fistNameP, String lastNameP, int ageP,
                    String addressP, String emailP, String sexP,
                    String passwordP) {
        super(fistNameP, lastNameP, ageP, addressP, emailP, sexP, "customer", passwordP);
    }

    public void setCustomersProductsList(Customer user){
        productsList = DBUtils.getCustomersProductsFromDB(DBUtils.getUserIDFromDB(user.getEmail()));
    }

    public ArrayList<String> getCustomersProductsList(Customer user){
        return productsList;
    }

    public void buyProducts(String customerEmail, String product){
        DBUtils.assignProductToUserInDB(DBUtils.getUserIDFromDB(customerEmail),
                DBUtils.getProductIDFromDB(product));
    }


}
