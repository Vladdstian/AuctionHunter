package com.vladnichifor.auctionhunter.mappers;

import com.vladnichifor.auctionhunter.entities.AuctionCategoryEntity;
import com.vladnichifor.auctionhunter.models.AuctionCategory;
import org.springframework.stereotype.Component;

@Component
public class AuctionCategoryMapper implements Mapper<AuctionCategory, AuctionCategoryEntity> {

    @Override
    public AuctionCategoryEntity toEntity(AuctionCategory dto) {
        if (dto == null) {
            return null;
        }

        return AuctionCategoryEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .parentCategory(this.toEntity(dto.getParentCategory()))
                .childrenCategories(dto.getChildrenCategories().stream().map(this::toEntity).toList())
                .build();
    }

    @Override
    public AuctionCategory toDto(AuctionCategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        return AuctionCategory.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .parentCategory(this.toDto(entity.getParentCategory()))
                .childrenCategories(entity.getChildrenCategories().stream().map(this::toDto).toList())
                .build();
    }
}
