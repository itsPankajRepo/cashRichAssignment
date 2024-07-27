package com.assignment.cashRich.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
public class SignUpUserRequestDto implements Serializable {

   @Serial
   private static final long serialVersionUID = -2713105902195874701L;

   private String username;

   private String password;

   @NotNull @NotEmpty
   private String firstName;

   private String lastName;

   private String email;

   private String mobileNo;


   @AssertTrue(message = "Email should be valid")
   public boolean isEmailValid() {
      return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
   }


   @AssertTrue(message = "Username should be valid")
   public boolean isUsernameValid() {
      return username != null && username.matches("^[a-zA-Z0-9]{4,15}$");
   }


   @AssertTrue(message = "Password should be valid")
   public boolean isPasswordValid() {
      String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,15}$";
      return password != null && password.matches(regex);
   }

   @Override
   public String toString() {
      return "SignUpUserRequestDto{" + "mobileNo='" + mobileNo + '\'' + ", email='" + email + '\'' + ", lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + ", username='" + username + '\'' + '}';
   }
}
