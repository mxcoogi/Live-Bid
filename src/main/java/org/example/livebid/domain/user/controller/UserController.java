package org.example.livebid.domain.user.controller;

import org.example.livebid.domain.user.dto.UserLoginRequest;
import org.example.livebid.domain.user.dto.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

public interface UserController {

    ResponseEntity<?> registerUser(UserRegisterRequest request);
    ResponseEntity<?> loginUser(UserLoginRequest request);
    ResponseEntity<?> logoutUser();
    ResponseEntity<?> getUser();
    ResponseEntity<?> updateUser();
    ResponseEntity<?> deleteUser();
}
