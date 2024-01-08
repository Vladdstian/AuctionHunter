package com.vladnichifor.auctionhunter.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

//    @NotBlank(message = "Last name is required")
//    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

//    @NotBlank(message = "Phone number is required")
//    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
//    private String phoneNumber;

    //TODO: remove lastname, phonenumber
    //TODO: add retype password to check if the password is valid

}