package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.entities.UserEntity;
import com.vladnichifor.auctionhunter.repositories.AuctionRepository;
import com.vladnichifor.auctionhunter.repositories.UserRepository;
import com.vladnichifor.auctionhunter.utils.AccountRole;
import com.vladnichifor.auctionhunter.utils.AccountStatus;
import com.vladnichifor.auctionhunter.utils.AccountType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminInitializationService {
    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuctionRepository auctionRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setFirstName("Admin");
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setAccountRole(AccountRole.ADMIN);
            adminUser.setAccountType(AccountType.PREMIUM);
            adminUser.setAccountStatus(AccountStatus.ACTIVE);
            adminUser.setCreatedAuctions(auctionRepository.findAll());
            userRepository.save(adminUser);
        }
    }
}
