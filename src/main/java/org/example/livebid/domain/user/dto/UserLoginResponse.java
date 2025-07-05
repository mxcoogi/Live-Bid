package org.example.livebid.domain.user.dto;

public record UserLoginResponse(
        String accessToken
) {
    public static UserLoginResponse from(String accessToken) {
        return new UserLoginResponse(accessToken);
    }
}
