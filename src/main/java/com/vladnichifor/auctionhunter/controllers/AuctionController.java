package com.vladnichifor.auctionhunter.controllers;

import com.vladnichifor.auctionhunter.models.Auction;
import com.vladnichifor.auctionhunter.services.AuctionService;
import com.vladnichifor.auctionhunter.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping
    public ResponseEntity<ApiResponse> createAuction(@RequestBody Auction auction) {
        Auction createdAuction = auctionService.createAuction(auction);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Auction created successfully")
                .data(createdAuction)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAuctionById(@PathVariable Long id) {
        Auction auction = auctionService.getAuctionById(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Auction retrieved successfully")
                .data(auction)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAuctions() {
        List<Auction> auctions = auctionService.getAllAuctions();
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("All auctions retrieved successfully")
                .data(auctions)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAuction(@PathVariable Long id, @RequestBody Auction auction) {
        Auction updatedAuction = auctionService.updateAuction(id, auction);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Auction updated successfully")
                .data(updatedAuction)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/promote")
    public ResponseEntity<ApiResponse> promoteAuction(@PathVariable Long id) {
        Auction promotedAuction = auctionService.promoteAuction(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Auction promoted successfully")
                .data(promotedAuction)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/increment-visits")
    public ResponseEntity<ApiResponse> incrementAuctionVisits(@PathVariable Long id) {
        Auction auctionWithIncreasedVisits = auctionService.incrementAuctionVisits(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Auction visits incremented successfully")
                .data(auctionWithIncreasedVisits)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Auction deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}