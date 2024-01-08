package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.entities.UserEntity;
import com.vladnichifor.auctionhunter.exceptions.PasswordMismatchException;
import com.vladnichifor.auctionhunter.models.LoginRequest;
import com.vladnichifor.auctionhunter.models.AuthenticationResponse;
import com.vladnichifor.auctionhunter.models.RegisterRequest;
import com.vladnichifor.auctionhunter.repositories.UserRepository;
import com.vladnichifor.auctionhunter.security.AuthUser;
import com.vladnichifor.auctionhunter.security.JwtService;
import com.vladnichifor.auctionhunter.utils.AccountRole;
import com.vladnichifor.auctionhunter.utils.AccountStatus;
import com.vladnichifor.auctionhunter.utils.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match.");
        }

        var user = new AuthUser(
                UserEntity.builder()
                        .firstName(request.getFirstName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .accountRole(AccountRole.USER)
                        .accountStatus(AccountStatus.ACTIVE)
                        .accountType(AccountType.NORMAL)
                        .build()
        );
        userRepository.save(user.userEntity());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = new AuthUser(userRepository.findByEmail(request.getEmail())
                .orElseThrow());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}