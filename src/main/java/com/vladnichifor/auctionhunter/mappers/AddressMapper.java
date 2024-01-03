package com.vladnichifor.auctionhunter.mappers;

import com.vladnichifor.auctionhunter.entities.AddressEntity;
import com.vladnichifor.auctionhunter.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements Mapper<Address, AddressEntity> {

    @Override
    public AddressEntity toEntity(Address dto) {
        return null;
    }

    @Override
    public Address toDto(AddressEntity entity) {
        return null;
    }
}
