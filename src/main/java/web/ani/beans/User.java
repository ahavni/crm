package web.ani.beans;

public class User {
    // User attributes
    protected String fistName;
    protected String lastName;
    protected int age;
    protected String address;
    protected String email;
    private String sex;

    // constructor
    public User(){
        this.fistName = "";
        this.lastName = "";
        this.age = 0;
        this.address = "";
        this.sex = "";
    }

    public User(String fistNameP, String lastNameP, int ageP, String addressP, String emailP, String sexP){
        this.fistName = fistNameP;
        this.lastName = lastNameP;
        this.age = ageP;
        this.address = addressP;
        this.email = emailP;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ??
    public boolean equals(User userP) {
        boolean fistNameEqual = this.fistName.equals(userP.fistName);
        boolean lastNameEqual = this.lastName.equals(userP.lastName);
        boolean ageEqual = (this.age == userP.age);
        boolean addressEqual = this.address.equals(userP.address);

        return
                fistNameEqual && lastNameEqual && ageEqual && addressEqual;

    }
}
