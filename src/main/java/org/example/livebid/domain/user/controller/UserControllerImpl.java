package org.example.livebid.domain.user.controller;

import jakarta.validation.Valid;
import org.example.livebid.domain.user.dto.UserLoginRequest;
import org.example.livebid.domain.user.dto.UserLoginResponse;
import org.example.livebid.domain.user.dto.UserRegisterRequest;
import org.example.livebid.domain.user.dto.UserRegisterResponse;
import org.example.livebid.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController{

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        UserRegisterResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse response = userService.loginUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @DeleteMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        userService.logoutUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> getUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUser() {
        return null;
    }
}
