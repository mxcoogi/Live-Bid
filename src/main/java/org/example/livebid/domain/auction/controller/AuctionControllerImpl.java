package org.example.livebid.domain.auction.controller;

import org.example.livebid.domain.auction.dto.AuctionRequest;
import org.example.livebid.domain.auction.dto.AuctionResponse;
import org.example.livebid.domain.auction.service.AuctionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auctions")
public class AuctionControllerImpl implements AuctionController{

    private final AuctionService auctionService;

    public AuctionControllerImpl(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @Override
    @GetMapping
    public ResponseEntity<?> getAuctions(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AuctionResponse> response = auctionService.getAuctions(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuction(@PathVariable Long id) {
        AuctionResponse response = auctionService.getAuction(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> createAuction(
            @RequestBody AuctionRequest auctionRequest, @AuthenticationPrincipal Long userId) {
        AuctionResponse response = auctionService.createAuction(auctionRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuction(
            @PathVariable Long id,
            @RequestBody AuctionRequest request,
            @AuthenticationPrincipal Long userId) {
        AuctionResponse response = auctionService.updateAuction(id, request, userId);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuction(
            @PathVariable Long id,
            @AuthenticationPrincipal Long userId) {
        AuctionResponse response = auctionService.deleteAuction(id, userId);
        return ResponseEntity.ok(response);
    }
}
