package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.entities.UserEntity;
import com.vladnichifor.auctionhunter.exceptions.EmailAlreadyExistsException;
import com.vladnichifor.auctionhunter.mappers.UserMapper;
import com.vladnichifor.auctionhunter.models.User;
import com.vladnichifor.auctionhunter.repositories.UserRepository;
import com.vladnichifor.auctionhunter.utils.AccountRole;
import com.vladnichifor.auctionhunter.utils.AccountStatus;
import com.vladnichifor.auctionhunter.utils.AccountType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email: " + email + " already exists.");
        }
        UserEntity entity = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDto(entity);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new
                        EntityNotFoundException("User id: "
                        + id + " not found."));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new
                        EntityNotFoundException("User email: "
                        + email + " not found."));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new
                        EntityNotFoundException("User id: " +
                        id + " not found."));

        if (!entity.getEmail().equals(user.getEmail()) && userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email: " + user.getEmail() + " already exists.");
        }

        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public User changePassword(Long id, String newPassword) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new
                        EntityNotFoundException("User id: " +
                        id + " not found."));
        entity.setPassword(newPassword);
        userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public User changeUserRole(Long id, String roleName) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new
                        EntityNotFoundException("User id: " +
                        id + " not found."));
        entity.setAccountRole(AccountRole.valueOf(roleName));
        userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public User updateAccountType(Long userId, String accountType) {
        UserEntity entity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User id: " + userId + " not found."));
        entity.setAccountType(AccountType.valueOf(accountType));
        userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public User updateAccountStatus(Long userId, String accountStatus) {
        UserEntity entity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User id: " + userId + " not found."));
        entity.setAccountStatus(AccountStatus.valueOf(accountStatus));
        userRepository.save(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //todo -a method to check if the user has filled all details and to change it's Role from User to validated user

}
