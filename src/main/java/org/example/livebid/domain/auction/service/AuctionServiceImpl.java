package org.example.livebid.domain.auction.service;

import lombok.RequiredArgsConstructor;
import org.example.livebid.domain.auction.dto.AuctionRequest;
import org.example.livebid.domain.auction.dto.AuctionResponse;
import org.example.livebid.domain.auction.entity.Auction;
import org.example.livebid.domain.auction.enums.AuctionException;
import org.example.livebid.domain.auction.repository.AuctionRepository;
import org.example.livebid.global.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService{

    private final AuctionRepository auctionRepository;

    @Override
    @Transactional
    public AuctionResponse createAuction(AuctionRequest auctionRequest, Long userId) {
        Auction auction = Auction
                .builder()
                .title(auctionRequest.title())
                .description(auctionRequest.description())
                .startingPrice(auctionRequest.startingPrice())
                .userId(userId)
                .build();
        Auction savedAuction = auctionRepository.save(auction);

        return AuctionResponse.from(savedAuction);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuctionResponse> getAuctions(Pageable pageable) {
        return auctionRepository.findAll(pageable)
                .map(AuctionResponse::from);
    }


    @Override
    @Transactional(readOnly = true)
    public AuctionResponse getAuction(Long id) {
        Auction findAuction = auctionRepository.findById(id)
                .orElseThrow(()-> new CustomException(AuctionException.AUCTION_NOT_FOUND));

        return AuctionResponse.from(findAuction);
    }

    @Override
    @Transactional
    public AuctionResponse updateAuction(Long id, AuctionRequest request, Long userId) {
        Auction findAuction = auctionRepository.findById(id)
                .orElseThrow(()-> new CustomException(AuctionException.AUCTION_NOT_FOUND));
        if(!findAuction.getUserId().equals(userId)){
            throw new CustomException(AuctionException.UNAUTHORIZED_ACCESS);
        }
        findAuction.update(request);
        return AuctionResponse.from(findAuction);
    }

    @Override
    @Transactional
    public AuctionResponse deleteAuction(Long id, Long userId) {
        Auction findAuction = auctionRepository.findById(id)
                .orElseThrow(()-> new CustomException(AuctionException.AUCTION_NOT_FOUND));
        if(!findAuction.getUserId().equals(userId)){
            throw new CustomException(AuctionException.UNAUTHORIZED_ACCESS);
        }
        findAuction.setEnded();
        return AuctionResponse.from(findAuction);
    }
}
