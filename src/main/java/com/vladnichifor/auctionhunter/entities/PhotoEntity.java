package com.vladnichifor.auctionhunter.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "photo")
@Table(name = "photo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "url")
    private String url;
}
