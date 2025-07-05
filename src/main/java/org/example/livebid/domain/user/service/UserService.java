package org.example.livebid.domain.user.service;

import org.example.livebid.domain.user.dto.*;


public interface UserService {

    UserRegisterResponse registerUser(UserRegisterRequest request);
    UserLoginResponse loginUser(UserLoginRequest request);
    UserLogoutResponse logoutUser();
    UserInfoResponse getUser();
    UserInfoResponse updateUser();
    UserInfoResponse deleteUser();
}
