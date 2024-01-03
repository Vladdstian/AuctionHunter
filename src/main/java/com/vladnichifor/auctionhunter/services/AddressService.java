package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Address createAddress(Address address);

    Address getAddressById(Long id);

    List<Address> getAllAddresses();

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);
}