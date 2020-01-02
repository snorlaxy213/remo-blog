package com.remo.entity;

import lombok.Data;

/**
 * JSON Web Token
 */
@Data
public class JWTToken {

    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    private String expireAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String expireAt) {
        this.token = token;
        this.expireAt = expireAt;
    }
}
