package com.vladnichifor.auctionhunter.mappers;

import com.vladnichifor.auctionhunter.entities.UserEntity;
import com.vladnichifor.auctionhunter.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserEntity> {

    private final AddressMapper addressMapper;
    private final AuctionMapper auctionMapper;

    @Override
    public UserEntity toEntity(User dto) {
        if (dto == null) {
            return null;
        }

        return UserEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .addressEntity(addressMapper.toEntity(dto.getAddress()))
                .accountStatus(dto.getAccountStatus())
                .accountType(dto.getAccountType())
                .accountRole(dto.getAccountRole())
                .createdAuctions(dto
                        .getCreatedAuctions()
                        .stream()
                        .map(auctionMapper::toEntity)
                        .toList())
                .participatedAuctions(dto
                        .getParticipatedAuctions()
                        .stream()
                        .map(auctionMapper::toEntity)
                        .toList())
                .favouriteAuctions(dto
                        .getFavouriteAuctions()
                        .stream()
                        .map(auctionMapper::toEntity)
                        .toList())
                .build();
    }

    @Override
    public User toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(addressMapper.toDto(entity.getAddressEntity()))
                .accountStatus(entity.getAccountStatus())
                .accountType(entity.getAccountType())
                .accountRole(entity.getAccountRole())
                .createdAuctions(entity
                        .getCreatedAuctions()
                        .stream()
                        .map(auctionMapper::toDto)
                        .toList())
                .participatedAuctions(entity
                        .getParticipatedAuctions()
                        .stream()
                        .map(auctionMapper::toDto)
                        .toList())
                .favouriteAuctions(entity
                        .getFavouriteAuctions()
                        .stream()
                        .map(auctionMapper::toDto)
                        .toList())
                .build();
    }
}
