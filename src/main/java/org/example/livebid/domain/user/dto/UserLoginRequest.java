package org.example.livebid.domain.user.dto;

public record UserLoginRequest(
        String email,
        String password
) {
}
