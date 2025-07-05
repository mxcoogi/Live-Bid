package org.example.livebid.domain.user.dto;

import org.example.livebid.global.security.UserRole;

public record UserRegisterRequest (

        String email,
        String password,
        UserRole userRole
){
}
