package com.assignment.cashRich.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
public class UpdateUserRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2713105902195874701L;

    private String firstName;

    private String lastName;

    private String mobileNo;

    private String password;

    @AssertTrue(message = "Password should be valid")
    public boolean isPasswordValid() {
        if (Objects.nonNull(password)) {
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,15}$";
            return password.matches(regex);
        }
        return true;
    }

    @Override
    public String toString() {
        return "UpdateUserRequestDto{" + "mobileNo='" + mobileNo + '\'' + ", lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + '}';
    }
}
