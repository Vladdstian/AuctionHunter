package com.vladnichifor.auctionhunter.repositories;

import com.vladnichifor.auctionhunter.entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {

}
