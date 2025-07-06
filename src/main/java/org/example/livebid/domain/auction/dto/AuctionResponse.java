package org.example.livebid.domain.auction.dto;

import org.example.livebid.domain.auction.entity.Auction;

import java.time.LocalDateTime;

public record AuctionResponse(
        Long id,
        String title,
        String description,
        Long userId,
        Long startingPrice,
        LocalDateTime createdAt,
        LocalDateTime ended
){

    public static AuctionResponse from(Auction auction) {
        return new AuctionResponse(
                auction.getId(),
                auction.getTitle(),
                auction.getDescription(),
                auction.getUserId(),
                auction.getStartingPrice(),
                auction.getCreatedAt(),
                auction.getEnded()
        );
    }
}
