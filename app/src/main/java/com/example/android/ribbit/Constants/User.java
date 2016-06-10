package com.example.android.ribbit.Constants;

/**
 * Created by surya on 10-06-2016.
 */
public class User {

    String name,email;

    public User(){}

    public User(String name,String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
