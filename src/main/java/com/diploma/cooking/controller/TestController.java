package com.diploma.cooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getUserInfo() {
        return new ResponseEntity<>("Hello user", HttpStatus.OK);
    }

    @GetMapping("/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminInfo() {
        return new ResponseEntity<>("Hello admin", HttpStatus.OK);
    }

    @GetMapping("/test/common")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> getCommonInfo() {
        return new ResponseEntity<>("Hello authorized client", HttpStatus.OK);
    }
}
