package org.example.livebid.domain.auction.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AuctionRequest(
        @NotBlank(message = "경매 제목은 필수입니다.")
        String title,

        @NotBlank(message = "경매 설명은 필수입니다.")
        String description,

        @NotNull(message = "경매 시작가는 필수입니다.")
        @Min(value = 1000, message = "경매 시작가는 1000원 이상이어야 합니다.")
        Long startingPrice
){
}
