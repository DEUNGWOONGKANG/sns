package com.java.sns.fixture;

import com.java.sns.model.entity.UserEntity;

public class UserEntityFixture {

    public static UserEntity get(String userName, String password) {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }
}
