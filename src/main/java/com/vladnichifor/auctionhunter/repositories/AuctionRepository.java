package com.vladnichifor.auctionhunter.repositories;

import com.vladnichifor.auctionhunter.entities.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {

}
