package com.vladnichifor.auctionhunter.controllers;

import com.vladnichifor.auctionhunter.models.Address;
import com.vladnichifor.auctionhunter.services.AddressService;
import com.vladnichifor.auctionhunter.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<ApiResponse> createAddress(@RequestBody Address address) {
        Address createdAddress = addressService.createAddress(address);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Address created successfully")
                .data(createdAddress)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Address retrieved successfully")
                .data(address)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("All addresses retrieved successfully")
                .data(addresses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address updatedAddress = addressService.updateAddress(id, address);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Address updated successfully")
                .data(updatedAddress)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Address deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}