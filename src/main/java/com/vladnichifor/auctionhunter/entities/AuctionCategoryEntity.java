package com.vladnichifor.auctionhunter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "auction_category")
@Table(name = "auction_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "parent_category", referencedColumnName = "id")
    private AuctionCategoryEntity parentCategory;
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<AuctionCategoryEntity> childrenCategories;
}
