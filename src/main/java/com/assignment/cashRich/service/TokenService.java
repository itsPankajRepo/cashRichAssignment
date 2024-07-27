package com.assignment.cashRich.service;

import com.assignment.cashRich.entity.UserToken;

public interface TokenService {

    UserToken getUserByToken(String token);

    void saveUserToken(UserToken userToken);

    boolean isTokenPresent(String token);
}
