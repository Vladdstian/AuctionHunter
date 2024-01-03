package com.vladnichifor.auctionhunter.services;

import com.vladnichifor.auctionhunter.entities.AuctionEntity;
import com.vladnichifor.auctionhunter.mappers.AuctionCategoryMapper;
import com.vladnichifor.auctionhunter.mappers.AuctionMapper;
import com.vladnichifor.auctionhunter.mappers.PhotoMapper;
import com.vladnichifor.auctionhunter.mappers.UserMapper;
import com.vladnichifor.auctionhunter.models.Auction;
import com.vladnichifor.auctionhunter.repositories.AuctionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;
    private final PhotoMapper photoMapper;
    private final UserMapper userMapper;
    private final AuctionCategoryMapper auctionCategoryMapper;

    @Override
    public Auction createAuction(Auction auction) {
        AuctionEntity entity = auctionMapper.toEntity(auction);
        AuctionEntity savedEntity = auctionRepository.save(entity);
        return auctionMapper.toDto(savedEntity);
    }

    @Override
    public Auction getAuctionById(Long id) {
        AuctionEntity entity = auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction id: " + id + " not found."));
        return auctionMapper.toDto(entity);
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll().stream()
                .map(auctionMapper::toDto)
                .toList();
    }

    @Override
    public Auction updateAuction(Long id, Auction auction) {
        AuctionEntity existingEntity = auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction id: " + id + " not found."));

        existingEntity.setTitle(auction.getTitle());
        existingEntity.setDescription(auction.getDescription());
        existingEntity.setOwnerEntity(userMapper.toEntity(auction.getOwner()));
        existingEntity.setPhotos(auction.getPhotos().stream().map(photoMapper::toEntity).toList());
        existingEntity.setCategory(auctionCategoryMapper.toEntity(auction.getCategory()));
        existingEntity.setReservedPrice(auction.getReservedPrice());
        existingEntity.setInstantBuyPrice(auction.getInstantBuyPrice());
        existingEntity.setPromoted(auction.getPromoted());
        existingEntity.setLocation(auction.getLocation());
        existingEntity.setStartingDate(auction.getStartingDate());
        existingEntity.setEndingDate(auction.getEndingDate());
        existingEntity.setStartingTime(auction.getStartingTime());
        existingEntity.setEndingTime(auction.getEndingTime());
        existingEntity.setVisits(auction.getVisits());

        AuctionEntity savedEntity = auctionRepository.save(existingEntity);
        return auctionMapper.toDto(savedEntity);
    }

    @Override
    public Auction promoteAuction(Long id) {
        AuctionEntity auctionEntity = auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction id: " + id + " not found."));

        auctionEntity.setPromoted(true);
        AuctionEntity updatedEntity = auctionRepository.save(auctionEntity);
        return auctionMapper.toDto(updatedEntity);
    }

    @Override
    public Auction incrementAuctionVisits(Long id) {
        AuctionEntity auctionEntity = auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction id: " + id + " not found."));

        int currentVisits = auctionEntity.getVisits() == null ? 0 : auctionEntity.getVisits();
        auctionEntity.setVisits(currentVisits + 1);
        AuctionEntity updatedEntity = auctionRepository.save(auctionEntity);
        return auctionMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteAuction(Long id) {
        if (!auctionRepository.existsById(id)) {
            throw new EntityNotFoundException("Auction id: " + id + " not found.");
        }
        auctionRepository.deleteById(id);
    }
}