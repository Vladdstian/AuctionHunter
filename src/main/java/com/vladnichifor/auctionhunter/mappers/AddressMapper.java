package com.vladnichifor.auctionhunter.mappers;

import com.vladnichifor.auctionhunter.entities.AddressEntity;
import com.vladnichifor.auctionhunter.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements Mapper<Address, AddressEntity> {

    public Address toDto(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        return Address.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .province(entity.getProvince())
                .city(entity.getCity())
                .address(entity.getAddress())
                .postCode(entity.getPostCode())
                .build();
    }

    public AddressEntity toEntity(Address model) {
        if (model == null) {
            return null;
        }
        return AddressEntity.builder()
                .id(model.getId())
                .country(model.getCountry())
                .province(model.getProvince())
                .city(model.getCity())
                .address(model.getAddress())
                .postCode(model.getPostCode())
                .build();
    }
}

