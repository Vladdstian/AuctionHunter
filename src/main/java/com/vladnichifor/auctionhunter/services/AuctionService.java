package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.models.Auction;

import java.util.List;

public interface AuctionService {

    Auction createAuction(Auction auction);

    Auction getAuctionById(Long id);

    List<Auction> getAllAuctions();

    Auction updateAuction(Long id, Auction auction);

    Auction promoteAuction(Long id);

    Auction incrementAuctionVisits(Long id);

    void deleteAuction(Long id);
}