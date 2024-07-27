package com.assignment.cashRich.service;


import com.assignment.cashRich.dto.LoginUserRequestDto;
import com.assignment.cashRich.dto.UpdateUserRequestDto;
import com.assignment.cashRich.entity.User;
import com.assignment.cashRich.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService{


    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public int updateUser(UpdateUserRequestDto updateUserRequestDto, String username) {
      return userRepository.updateUser(updateUserRequestDto.getFirstName(), updateUserRequestDto.getLastName(),
                updateUserRequestDto.getMobileNo(), updateUserRequestDto.getPassword(), username);

    }


    @Override
    public User findUserByUsername(String username) {
        var userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }


    @Override
    public User findUserByEmailOrUsername(String email, String username) {
        var userOptional = userRepository.findByEmailOrUsername(email, username);
        return userOptional.orElse(null);
    }


    @Override
    public User validateUser(LoginUserRequestDto loginUserRequestDto) {
        var userOptional =  userRepository.findByUsernameAndPassword(loginUserRequestDto.getUsername(),loginUserRequestDto.getPassword());
        return userOptional.orElse(null);
    }
}
