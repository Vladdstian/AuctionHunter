package com.vladnichifor.auctionhunter.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "auction")
@Table(name = "auction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    @JsonManagedReference
//    private UserEntity ownerEntity;

    @OneToMany(targetEntity = PhotoEntity.class, cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;

    @ManyToOne
    @JoinColumn(name = "auction_category_id", referencedColumnName = "id")
    private AuctionCategoryEntity category;

    @Column(name = "reserved_price")
    private Double reservedPrice;

    @Column(name = "instant_buy_price")
    private Double instantBuyPrice;

    @Column(name = "promoted",nullable = false)
    private Boolean promoted;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Column(name = "ending_date")
    private LocalDate endingDate;

    @Column(name = "starting_time")
    private LocalTime startingTime;

    @Column(name = "ending_time")
    private LocalTime endingTime;

    @Column(name = "visits")
    private Integer visits;
}