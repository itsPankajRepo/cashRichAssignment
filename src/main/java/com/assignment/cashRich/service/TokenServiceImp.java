package com.assignment.cashRich.service;

import com.assignment.cashRich.entity.UserToken;
import com.assignment.cashRich.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImp implements TokenService{

   @Autowired
   private UserTokenRepository userTokenRepository;


    @Override
    public UserToken getUserByToken(String token) {
        var userTokenOptional = userTokenRepository.findByToken(token);
        return userTokenOptional.orElse(null);
    }

    @Override
    public void saveUserToken(UserToken userToken) {
        var userTokenOptional = userTokenRepository.findByUsername(userToken.getUsername());
        if(userTokenOptional.isPresent()){
            userTokenRepository.updateToken(userToken.getToken(), userToken.getUsername());
        }else
            userTokenRepository.save(userToken);
    }

    @Override
    public boolean isTokenPresent(String token) {
        return userTokenRepository.isTokenPresent(token) > 0;
    }
}
