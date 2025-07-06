package org.example.livebid.domain.auction.dto;

public record AuctionRequest(
        String title,
        String description,
        Long startingPrice
){
}
