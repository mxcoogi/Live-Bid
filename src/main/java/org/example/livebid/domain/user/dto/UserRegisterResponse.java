package org.example.livebid.domain.user.dto;

import org.example.livebid.domain.user.entity.User;

public record UserRegisterResponse(
        Long id
) {

    public static UserRegisterResponse from(User user) {
        return new UserRegisterResponse(user.getId());
    }
}
