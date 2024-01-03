package com.vladnichifor.auctionhunter.mappers;


import com.vladnichifor.auctionhunter.entities.AuctionEntity;
import com.vladnichifor.auctionhunter.models.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionMapper implements Mapper<Auction, AuctionEntity> {

    private final PhotoMapper photoMapper;
    private final AuctionCategoryMapper auctionCategoryMapper;
    private final UserMapper userMapper;


    public AuctionEntity toEntity(Auction dto) {
        if (dto == null) {
            return null;
        }

        return AuctionEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .ownerEntity(userMapper.toEntity(dto.getOwner()))
                .photos(dto.getPhotos().stream().map(photoMapper::toEntity).toList())
                .category(auctionCategoryMapper.toEntity(dto.getCategory()))
                .reservedPrice(dto.getReservedPrice())
                .instantBuyPrice(dto.getInstantBuyPrice())
                .promoted(dto.getPromoted())
                .location(dto.getLocation())
                .startingDate(dto.getStartingDate())
                .endingDate(dto.getEndingDate())
                .startingTime(dto.getStartingTime())
                .endingTime(dto.getEndingTime())
                .visits(dto.getVisits())
                .build();
    }

    public Auction toDto(AuctionEntity entity) {
        if (entity == null) {
            return null;
        }

        return Auction.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .owner(userMapper.toDto(entity.getOwnerEntity()))
                .photos(entity.getPhotos().stream().map(photoMapper::toDto).toList())
                .category(auctionCategoryMapper.toDto(entity.getCategory()))
                .reservedPrice(entity.getReservedPrice())
                .instantBuyPrice(entity.getInstantBuyPrice())
                .promoted(entity.getPromoted())
                .location(entity.getLocation())
                .startingDate(entity.getStartingDate())
                .endingDate(entity.getEndingDate())
                .startingTime(entity.getStartingTime())
                .endingTime(entity.getEndingTime())
                .visits(entity.getVisits())
                .build();
    }
}
