package com.bawkertech.ceunixtack.models;
import lombok.Data;

@Data
public class User {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;

    public User(String username, String password, String email, String fullName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

}
