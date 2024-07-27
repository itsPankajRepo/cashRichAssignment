package com.assignment.cashRich.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUserRequestDto {

    @NotNull @NotEmpty
    private String username;

    @NotNull @NotEmpty
    private String password;
}
