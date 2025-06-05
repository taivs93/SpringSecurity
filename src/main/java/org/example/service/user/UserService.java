package org.example.service.user;

import org.example.dto.request.user.UserLoginRequestDTO;
import org.example.dto.request.user.UserRegisterRequestDTO;
import org.example.model.User;
import org.example.security.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;

public interface UserService {
    User createUser(UserRegisterRequestDTO userRegisterDTO) throws Exception;
    Authentication loginAndGetAuthentication(UserLoginRequestDTO userLoginDTO) throws Exception;
}
