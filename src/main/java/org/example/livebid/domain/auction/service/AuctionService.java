package org.example.livebid.domain.auction.service;

import org.example.livebid.domain.auction.dto.AuctionRequest;
import org.example.livebid.domain.auction.dto.AuctionResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionService {
    AuctionResponse createAuction(AuctionRequest auctionRequest, Long userId);
    Page<AuctionResponse> getAuctions(Pageable pageable);
    AuctionResponse getAuction(Long id);
    AuctionResponse updateAuction(Long id ,AuctionRequest request, Long userId);
    AuctionResponse deleteAuction(Long id, Long userId);
}
