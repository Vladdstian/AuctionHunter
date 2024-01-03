package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    User changePassword(Long id, String newPassword);

    User changeUserRole(Long id, String roleName);

    User updateAccountType(Long userId, String accountType);

    User updateAccountStatus(Long userId, String accountStatus);

    void deleteUserById(Long id);
}
