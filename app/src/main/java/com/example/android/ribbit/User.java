package com.example.android.ribbit;

/**
 * Created by surya on 10-06-2016.
 */
public class User {

    String name,email,messages;

    public User(){}

    public User(String name,String email,String messages){
        this.name = name;
        this.email = email;
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
