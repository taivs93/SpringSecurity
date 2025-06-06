package org.example.service.user;
import org.example.dto.request.user.UserLoginRequestDTO;
import org.example.dto.request.user.UserRegisterRequestDTO;
import org.example.security.exception.DataNotFoundException;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public User createUser(UserRegisterRequestDTO userRegisterDTO) throws Exception {

        if(userRepository.existsByUserName(userRegisterDTO.getUserName())){
            throw new DataIntegrityViolationException("User Name existed");
        }
        if(userRepository.existsByTel(userRegisterDTO.getTel())){
            throw new DataIntegrityViolationException("Phone Number existed");
        }
        if(userRepository.existsByEmail(userRegisterDTO.getEmail())){
            throw new DataIntegrityViolationException("Email existed");
        }
        Set<Role> roles = new HashSet<>();
        Role existedRole = roleRepository.findByName(Role.NORMAL).orElseThrow(() -> new DataNotFoundException("Role not found"));
        roles.add(existedRole);
        User user = User.builder()
                .userName(userRegisterDTO.getUserName())
                .tel(userRegisterDTO.getTel())
                .email(userRegisterDTO.getEmail())
                .roles(roles)
                .password(encoder.encode(userRegisterDTO.getPassword()))
                .enabled((byte) 1)
                .build();
        userRepository.save(user);
        return user;
    }

     @Override
     public Authentication loginAndGetAuthentication(UserLoginRequestDTO userLoginDTO) throws AuthenticationException {
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         userLoginDTO.getUserName(),
                         userLoginDTO.getPassword()
                 )
         );
         return authentication;
     }

}
