package com.vladnichifor.auctionhunter.mappers;

import com.vladnichifor.auctionhunter.entities.PhotoEntity;
import com.vladnichifor.auctionhunter.models.Photo;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper implements Mapper<Photo, PhotoEntity> {

    @Override
    public PhotoEntity toEntity(Photo dto) {
        if (dto == null) {
            return null;
        }

        return PhotoEntity.builder()
                .url(dto.getUrl())
                .build();
    }

    @Override
    public Photo toDto(PhotoEntity entity) {
        if (entity == null) {
            return null;
        }

        return Photo.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .build();
    }
}
