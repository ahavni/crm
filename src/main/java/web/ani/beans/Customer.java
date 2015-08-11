package web.ani.beans;

import web.ani.utils.DBUtils;

public class Customer extends User{

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
}
