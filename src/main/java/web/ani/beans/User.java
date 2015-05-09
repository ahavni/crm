package web.ani.beans;

/**
 * Created by Anichka on 9.5.2015 г..
 */
public abstract class User implements IUser{
    // User attributes
    protected String fistName;
    protected String lastName;
    protected int age;
    protected String address;
    private String sex;

    // constructor
    User(){
        this.fistName = "";
        this.lastName = "";
        this.age = 0;
        this.address = "";
        this.sex = "";
    }

    User(String fistNameP, String lastNameP, int ageP, String addressP, String sexP){
        this.fistName = fistNameP;
        this.lastName = lastNameP;
        this.age = ageP;
        this.address = addressP;
        this.sex = sexP;
    }

    // getters and setter
    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean equals(User userP) {
        boolean fistNameEqual = this.fistName.equals(userP.fistName);
        boolean lastNameEqual = this.lastName.equals(userP.lastName);
        boolean ageEqual = (this.age == userP.age);
        boolean addressEqual = this.address.equals(userP.address);

        return
                fistNameEqual && lastNameEqual && ageEqual && addressEqual;

    }
}
