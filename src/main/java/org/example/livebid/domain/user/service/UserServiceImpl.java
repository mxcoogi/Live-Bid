package org.example.livebid.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.livebid.domain.user.dto.*;
import org.example.livebid.domain.user.entity.User;
import org.example.livebid.domain.user.enums.UserException;
import org.example.livebid.global.security.UserRole;
import org.example.livebid.domain.user.repository.UserRepository;
import org.example.livebid.global.common.JwtUtil;
import org.example.livebid.global.common.PasswordEncoder;
import org.example.livebid.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Override
    @Transactional
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        String email = request.email();
        if(userRepository.findByEmail(email).isPresent()){
            throw new CustomException(UserException.EMAIL_ALREADY_EXISTS);
        }
        String password = passwordEncoder.encode(request.password());
        UserRole userRole = request.userRole();
        User user = User
                .builder()
                .email(email)
                .password(password)
                .userRole(userRole).build();
        User savedUser = userRepository.save(user);

        return UserRegisterResponse.from(savedUser);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest request) {
        String email = request.email();
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(()->new CustomException(UserException.USER_NOT_FOUND));

        if(!passwordEncoder.matches(request.password(), findUser.getPassword())){
            throw new CustomException(UserException.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createToken(findUser.getId(), email, findUser.getUserRole());
        return UserLoginResponse.from(accessToken);
    }

    @Override
    public UserLogoutResponse logoutUser() {
        return null;
    }

    @Override
    public UserInfoResponse getUser() {
        return null;
    }

    @Override
    public UserInfoResponse updateUser() {
        return null;
    }

    @Override
    public UserInfoResponse deleteUser() {
        return null;
    }
}
