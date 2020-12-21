package com.ssg.auth.user.dto;

import lombok.Data;

@Data
public class UserJWT {
    String token;
    String refreshToken;

    public UserJWT(String token, String refreshToken){
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
