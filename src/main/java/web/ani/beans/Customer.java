package web.ani.beans;

public class Customer extends User{

    public Customer(){

    }
    public Customer(String fistNameP, String lastNameP, int ageP,
                      String addressP, String emailP, String sexP,
                      String passwordP) {
        super(fistNameP, lastNameP, ageP, addressP, emailP, sexP, "customer", passwordP);
    }
}
