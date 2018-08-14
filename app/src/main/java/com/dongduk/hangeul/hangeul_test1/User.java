package com.dongduk.hangeul.hangeul_test1;

/**
 * Created by jiyoungwon on 2018. 8. 14..
 */

public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
