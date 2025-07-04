package org.example.livebid.global.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.example.livebid.domain.user.enums.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static final long TOKEN_TIME = 1000L * 60 * 15;
    private static final long REFRESH_TIME = 1000L * 60 * 60 * 12; // 12시간

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //토큰 생성
    public String createToken(Long userId, String email, UserRole userRole){
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + TOKEN_TIME);

        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .claim("role", userRole.name())
                .setExpiration(expireAt)
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm)
                .compact();
        return accessToken;
    }

    //토큰 추출 헤더로부터
    public String extractTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //토큰 검증
    public Claims validateTokenAndGetClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("만료된 JWT 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("지원하지 않는 JWT 토큰입니다.", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("잘못된 JWT 토큰입니다.", e);
        } catch (SignatureException e) {
            throw new RuntimeException("JWT 서명이 유효하지 않습니다.", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT 토큰이 비어있습니다.", e);
        }
    }
}
