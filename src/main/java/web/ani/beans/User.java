package web.ani.beans;

public class User implements IUser{
    // User attributes
    protected String fistName;
    protected String lastName;
    protected int age;
    protected String address;
    protected String email;
    private String sex;
    private String userType;
    private String password;

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
        buffer.append("User name is: ").append(this.fistName).
                append(" his email is ").append(this.email).
                append(" and he is: ").append(this.userType);

        return buffer.toString();
    }

    // override?
    public boolean equals(User userP) {
        boolean fistNameEqual = this.fistName.equals(userP.fistName);
        boolean lastNameEqual = this.lastName.equals(userP.lastName);
        boolean ageEqual = (this.age == userP.age);
        boolean addressEqual = this.address.equals(userP.address);

        return
                fistNameEqual && lastNameEqual && ageEqual && addressEqual;

    }

    // is it
    public void userDetails(){

    }

}
