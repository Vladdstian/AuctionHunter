package com.vladnichifor.auctionhunter.repositories;

import com.vladnichifor.auctionhunter.entities.AuctionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionCategoryRepository extends JpaRepository<AuctionCategoryEntity, Integer> {
    List<AuctionCategoryEntity> findAllByParentCategory_Id(Integer id);
    Optional<AuctionCategoryEntity> findByName(String name);
    List<AuctionCategoryEntity> findByNameContaining(String name);
}
