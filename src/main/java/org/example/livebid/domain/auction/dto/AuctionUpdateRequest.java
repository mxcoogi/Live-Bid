package org.example.livebid.domain.auction.dto;

public record AuctionUpdateRequest(
        Long id,
        String title,
        String description,
        Long startingPrice
) {
}
