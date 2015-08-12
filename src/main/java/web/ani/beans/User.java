package web.ani.beans;

import web.ani.utils.DBUtils;

import java.util.ArrayList;

public class User implements IUser{
    // User attributes
    protected String fistName;
    protected String lastName;
    protected int age;
    protected String address;
    protected String email;
    protected String sex;
    protected String userType;
    protected String password;

    // constructor
    public User(){
    }

    public User(String fistNameP, String lastNameP, int ageP,
                String addressP, String emailP, String sexP,
                String userTypeP, String passwordP){
        this.fistName = fistNameP;
        this.lastName = lastNameP;
        this.age = ageP;
        this.address = addressP;
        this.email = emailP;
        this.sex = sexP;
        this.userType = userTypeP;
        this.password = passwordP;
    }

    public User(String fistNameP, String lastNameP, int ageP,
                String addressP, String emailP, String sexP,
                String userTypeP){
        this.fistName = fistNameP;
        this.lastName = lastNameP;
        this.age = ageP;
        this.address = addressP;
        this.email = emailP;
        this.sex = sexP;
        this.userType = userTypeP;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Username is: ").append(this.fistName).
                append(" email is ").append(this.email).
                append(" user_type: ").append(this.userType);

        return buffer.toString();
    }

    public boolean equals(User userP) {
        return this.email.equals(userP.email);
    }

    public void updateUser(User newUserP) {
        DBUtils.updateUser(newUserP);
    }

    public void updateUserExceptPass(User newUserP) {
        DBUtils.updateUserExceptPass(newUserP);
    }

    public ArrayList<String> listAllProducts(){
        return DBUtils.getProductsFromDB();
    }
}
