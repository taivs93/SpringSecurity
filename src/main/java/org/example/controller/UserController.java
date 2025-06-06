package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.request.user.UserLoginRequestDTO;
import org.example.dto.request.user.UserRegisterRequestDTO;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.RegisterResponse;
import org.example.model.User;
import org.example.security.jwt.JWTUtil;
import org.example.security.service.UserDetailsImpl;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRegisterDTO) throws Exception {

        RegisterResponse registerResponse = new RegisterResponse();

        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getRetypePassword())){
            registerResponse.setMessage("Password not match");
            return ResponseEntity.badRequest().body(registerResponse);
        }

        try{
            User user = userService.createUser(userRegisterDTO);
            registerResponse.setMessage("Register successfully");
            registerResponse.build(user);
            return ResponseEntity.ok(registerResponse);
        } catch (Exception e)
        {
            registerResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(registerResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDTO userLoginDTO) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        try{
            Authentication authentication = userService.loginAndGetAuthentication(userLoginDTO);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtil.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(authority -> authority.startsWith("ROLE_"))
                    .collect(Collectors.toList());

            List<String> permissions = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(authority -> !authority.startsWith("ROLE_"))
                    .collect(Collectors.toList());

            loginResponse.build("Login successfully", jwt, userDetails.getUsername(), roles, permissions);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e){
            loginResponse.setMessage("Password or username is incorrect");
            return ResponseEntity.badRequest().body(loginResponse);
        } catch (Exception e){
            loginResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(loginResponse);
        }
    }
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    @GetMapping("/profile")
    public ResponseEntity<String> viewMyProfile() {
        return ResponseEntity.ok("Viewing your profile");
    }

    @PreAuthorize("hasAuthority('EDIT_PROFILE')")
    @PutMapping("/profile/edit")
    public ResponseEntity<String> editMyProfile() {
        return ResponseEntity.ok("Editing your profile");
    }
}
