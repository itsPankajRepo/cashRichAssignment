package com.assignment.cashRich.handler;

import com.assignment.cashRich.constant.CashRichExceptionCode;
import com.assignment.cashRich.constant.GenericConstant;
import com.assignment.cashRich.constant.MessageConstants;
import com.assignment.cashRich.dto.ApiResponse;
import com.assignment.cashRich.dto.LoginUserRequestDto;
import com.assignment.cashRich.dto.SignUpUserRequestDto;
import com.assignment.cashRich.dto.UpdateUserRequestDto;
import com.assignment.cashRich.entity.User;
import com.assignment.cashRich.entity.UserToken;
import com.assignment.cashRich.exception.CashRichException;
import com.assignment.cashRich.service.TokenService;
import com.assignment.cashRich.service.TokenServiceImp;
import com.assignment.cashRich.service.UserService;
import com.assignment.cashRich.service.UserServiceImp;
import com.assignment.cashRich.util.GenricUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class UserHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public ApiResponse<Object> signUpTheUser(SignUpUserRequestDto requestDto) {
        log.info("Inside UserHandler :: signUpTheUser");
        var userAlreadyPresent = userService.findUserByEmailOrUsername(requestDto.getEmail(), requestDto.getUsername());

        if (Objects.nonNull(userAlreadyPresent))
            throw new CashRichException(CashRichExceptionCode.USER_ALREADY_EXIST_WITH_GIVEN_MAIL);

        User newUser = new User(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail(), requestDto.getMobileNo(), requestDto.getUsername(), requestDto.getPassword());
        var newalyCreatedUser = userService.createUser(newUser);
        return new ApiResponse<>(200, MessageConstants.USER_CREATED_SUCCESSFULLY, newalyCreatedUser);

    }


    public ApiResponse<Object> validateLogin(LoginUserRequestDto loginUserRequestDto) {
        log.info("Inside UserHandler :: validateLogin");
        var isUserPresent = userService.validateUser(loginUserRequestDto);
        if(Objects.isNull(isUserPresent))
            throw new CashRichException(CashRichExceptionCode.INVALID_USERNAME_OR_PASSWORD);

        var token =  GenricUtil.generateToken(loginUserRequestDto.getUsername());
        tokenService.saveUserToken(new UserToken(loginUserRequestDto.getUsername(),token));
        Map<String, String> map = new HashMap<>(1);
        map.put("token",token);
        return new ApiResponse<>(200, MessageConstants.LOGIN_SUCCESSFULLY, map);

    }


    public ApiResponse<Object> updateUser(UpdateUserRequestDto updateUserRequestDto) {
        log.info("Inside UserHandler :: updateUser");
        var userName = SecurityContextHolder.getContext().getAuthentication() == null ?
                null : SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        if (Objects.isNull(userName))
            throw new CashRichException(CashRichExceptionCode.SOMETHING_WENT_WRONG);

        if (userService.updateUser(updateUserRequestDto, userName) > 0) {
            log.info("User updated successfully");
            return new ApiResponse<>(MessageConstants.USER_UPDATED_SUCCESSFULLY, 200, GenericConstant.STATUS_SUCCESS);
        }
        else {
            log.info("Not able to update user");
            return new ApiResponse<>(MessageConstants.NOT_ABLE_TO_MODIFY_USER, 400, GenericConstant.STATUS_FAILURE);
        }
    }


}
