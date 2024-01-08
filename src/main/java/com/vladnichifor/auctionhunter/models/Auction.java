package com.vladnichifor.auctionhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auction {
    private Long id;
    private String title;
    private String description;
    private User owner;
    private List<Photo> photos;
    private AuctionCategory category;
    private Double reservedPrice;
    private Double instantBuyPrice;
    private Boolean promoted;
    private String location;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private Integer visits;

    //todo - remove owner from here
}
