package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip")
public class VIPController {

    @PreAuthorize("hasAuthority('ACCESS_PREMIUM_CONTENT')")
    @GetMapping("/premium-content")
    public ResponseEntity<String> getPremiumContent() {
        return ResponseEntity.ok("Accessing premium content");
    }

    @PreAuthorize("hasAuthority('PRIORITY_SUPPORT')")
    @GetMapping("/support_line")
    public ResponseEntity<String> getPrioritySupport() {
        return ResponseEntity.ok("Connecting to priority support line");
    }
}