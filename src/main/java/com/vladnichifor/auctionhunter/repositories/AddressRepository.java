package com.vladnichifor.auctionhunter.repositories;

import com.vladnichifor.auctionhunter.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}