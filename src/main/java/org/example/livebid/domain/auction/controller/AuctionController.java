package org.example.livebid.domain.auction.controller;

import org.example.livebid.domain.auction.dto.AuctionRequest;
import org.example.livebid.domain.auction.dto.AuctionUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

public interface AuctionController {
    ResponseEntity<?> getAuctions(Pageable pageable);
    ResponseEntity<?> getAuction(Long id);
    ResponseEntity<?> createAuction(AuctionRequest auctionRequest, Long userId);
    ResponseEntity<?> updateAuction(Long id, AuctionRequest request, Long userId);
    ResponseEntity<?> deleteAuction(Long id, Long userId);
}
