package com.assignment.cashRich.controller;

import com.assignment.cashRich.constant.RequestUriConstant;
import com.assignment.cashRich.dto.ApiResponse;
import com.assignment.cashRich.dto.LoginUserRequestDto;
import com.assignment.cashRich.dto.SignUpUserRequestDto;
import com.assignment.cashRich.dto.UpdateUserRequestDto;
import com.assignment.cashRich.handler.UserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RequestUriConstant.CASH_RICH_ROOT)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserHandler userHandler;


    @PostMapping(value = RequestUriConstant.USER_SIGN_UP)
    public ApiResponse<Object> userSignUp(@RequestBody @Valid SignUpUserRequestDto signUpUserRequestDto) {
        log.info("Inside UserController :: userSignUp  with request :: {}", signUpUserRequestDto);
        return userHandler.signUpTheUser(signUpUserRequestDto);
    }


    @PostMapping(value = RequestUriConstant.LOGIN_USER)
    public ApiResponse<Object> loginUser(@Validated @RequestBody LoginUserRequestDto loginUserRequestDto){
        return userHandler.validateLogin(loginUserRequestDto);

    }


    @PostMapping(value = RequestUriConstant.UPDATE_USER)
    public ApiResponse<Object> updateUser(@RequestBody @Valid UpdateUserRequestDto updateUserRequestDto) {
        log.info("Inside UserController :: updateUser  with request :: {}", updateUserRequestDto);
        return userHandler.updateUser(updateUserRequestDto);
    }


}