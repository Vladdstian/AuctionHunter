package com.vladnichifor.auctionhunter.models;

import com.vladnichifor.auctionhunter.utils.AccountRole;
import com.vladnichifor.auctionhunter.utils.AccountStatus;
import com.vladnichifor.auctionhunter.utils.AccountType;
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
public class User {
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;

    private Address address;
    private AccountType accountType;
    private AccountRole accountRole;
    private AccountStatus accountStatus;
}
