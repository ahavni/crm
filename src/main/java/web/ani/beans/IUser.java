package web.ani.beans;

import java.util.ArrayList;

public interface IUser {
    public String getFistName();

    public void setFistName(String fistName);

    public String getLastName();

    public void setLastName(String lastName);

    public int getAge();

    public void setAge(int age);

    public String getAddress();

    public void setAddress(String address);

    public String getSex();

    public void setSex(String sex);

    public String getEmail();

    public void setEmail(String email);

    public String getUserType();

    public void setUserType(String userType);

    public String getPassword();

    public void setPassword(String password);

    public void updateUser(User newUserP);

    public void updateUserExceptPass(User newUserP);

    public ArrayList<String> listAllProducts();
}
