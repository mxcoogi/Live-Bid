package org.example.livebid.global.common;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.livebid.global.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = jwtUtil.extractTokenFromHeader(request);
        if (jwt != null) {
            try {
                Claims claims = jwtUtil.validateTokenAndGetClaims(jwt);

                Long userId = Long.parseLong(claims.getSubject());
                String email = claims.get("email", String.class);
                String role = claims.get("role", String.class);
                // 권한 부여 (Spring Security 형식으로 변환)
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                // 인증 객체 생성
                UserDetails userDetails = new CustomUserDetails(userId, email, java.util.List.of(authority));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, java.util.List.of(authority));

                // 시큐리티 컨텍스트에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // 토큰 유효성 실패 시 SecurityContext를 비워버리고 요청은 계속 흐르게 함
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
