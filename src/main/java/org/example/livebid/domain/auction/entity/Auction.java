package org.example.livebid.domain.auction.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.livebid.domain.auction.dto.AuctionRequest;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;                       // 경매 제목
    @Column(nullable = false)
    private String description;                // 경매 설명
    @Column(nullable = false)
    private Long userId;                      // 경매 작성자 (User ID)
    @Column(nullable = false)
    private Long startingPrice;                 // 경매 최소가
    @Column(nullable = false)
    private LocalDateTime createdAt;           // 경매 생성일
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private LocalDateTime ended;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public void update(AuctionRequest request){
        this.title = request.title();
        this.description = request.description();
        this.startingPrice = request.startingPrice();
        this.updatedAt = LocalDateTime.now();
    }
    public void setEnded() {
        this.ended = LocalDateTime.now();
    }
}
