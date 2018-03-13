package com.example.user.loginsignup;
import java.io.Serializable;
/**
 * Created by user on 30-01-2018.
 */
public class User implements Serializable{
    protected String username, email, password;

    public User(String uesrname, String email, String password) {
        this.username = uesrname;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
}
