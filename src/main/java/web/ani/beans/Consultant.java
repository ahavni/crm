package web.ani.beans;

public class Consultant extends User {

    public Consultant(String fistNameP, String lastNameP, int ageP,
                      String addressP, String emailP, String sexP,
                      String passwordP) {
        super(fistNameP, lastNameP, ageP, addressP, emailP, sexP, "consultant", passwordP);
    }
}
