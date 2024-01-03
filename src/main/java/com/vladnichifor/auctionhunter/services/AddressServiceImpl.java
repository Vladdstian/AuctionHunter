package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.entities.AddressEntity;
import com.vladnichifor.auctionhunter.mappers.AddressMapper;
import com.vladnichifor.auctionhunter.models.Address;
import com.vladnichifor.auctionhunter.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address createAddress(Address address) {
        AddressEntity entity = addressMapper.toEntity(address);
        AddressEntity savedEntity = addressRepository.save(entity);
        return addressMapper.toDto(savedEntity);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Address id: " + id + " not found."));
    }

    @Override
    public List<Address> getAllAddresses() {
        List<AddressEntity> entities = addressRepository.findAll();
        return entities.stream()
                .map(addressMapper::toDto)
                .toList();
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        AddressEntity existingEntity = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address id: " + id + " not found."));

        existingEntity.setCountry(address.getCountry());
        existingEntity.setProvince(address.getProvince());
        existingEntity.setCity(address.getCity());
        existingEntity.setAddress(address.getAddress());
        existingEntity.setPostCode(address.getPostCode());

        AddressEntity updatedEntity = addressRepository.save(existingEntity);
        return addressMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Address id: " + id + " not found.");
        }
        addressRepository.deleteById(id);
    }
}
