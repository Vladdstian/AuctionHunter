package com.vladnichifor.auctionhunter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private String country;
    private String province;
    private String city;
    private String address;
    private String postCode;
}
