package org.example.controller;

import org.example.dto.request.user.UserRegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody UserRegisterRequestDTO userData) {
        return ResponseEntity.ok("User created " + userData);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("User with ID " + id );
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id) {
        return ResponseEntity.ok("User with ID " + id );
    }

    @PreAuthorize("hasAuthority('VIEW_ALL_USERS')")
    @GetMapping("/users/all")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("Viewing all users");
    }

    @PreAuthorize("hasAuthority('VIEW_REPORTS')")
    @GetMapping("/view_reports")
    public ResponseEntity<String> getViewReports() {
        return ResponseEntity.ok("Viewing reports");
    }
}
