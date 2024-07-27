package com.assignment.cashRich.service;


import com.assignment.cashRich.dto.LoginUserRequestDto;
import com.assignment.cashRich.dto.UpdateUserRequestDto;
import com.assignment.cashRich.entity.User;

public interface UserService {

    User createUser(User user);

    int updateUser(UpdateUserRequestDto updateUserRequestDto,String username);

    User findUserByUsername(String username);

    User findUserByEmailOrUsername(String email, String username);

     User validateUser(LoginUserRequestDto loginUserRequestDto);
}
