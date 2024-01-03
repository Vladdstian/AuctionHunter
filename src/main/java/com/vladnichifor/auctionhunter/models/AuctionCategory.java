package com.vladnichifor.auctionhunter.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionCategory {
    private Integer id;
    private String name;
    private String description;
    private AuctionCategory parentCategory;
    private List<AuctionCategory> childrenCategories;
}

