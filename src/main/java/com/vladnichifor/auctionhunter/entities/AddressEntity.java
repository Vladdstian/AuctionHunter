package com.vladnichifor.auctionhunter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String province;
    private String city;
    @Column(name = "local_address")
    private String address;
    @Column(name = "post_code")
    private String postCode;
}
